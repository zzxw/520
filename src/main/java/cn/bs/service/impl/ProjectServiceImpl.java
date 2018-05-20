package cn.bs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cn.bs.dao.ProjectDao;
import cn.bs.entity.Project;
import cn.bs.service.NameException;
import cn.bs.service.ProjectService;
import cn.bs.tools.Tools;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	@Resource
	private ProjectDao projectDao;
	public Project add(Project project) {
		if(Tools.isEmpty(project.getpName() + project.getUnitName() + project.getContacts() + project.getcPhone())){
			throw new NameException("必填信息不能为空，请检查后重新输入提交");
		}
		String phoneReg = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
		if(!project.getcPhone().matches(phoneReg)){
			throw new NameException("手机号格式不正确，请检查后重新输入");
		}
		boolean isSuccess = projectDao.add(project);
		if(!isSuccess) {
			throw new NameException("创建项目失败，请稍后重新尝试");
		}
		return project;
	}

	public boolean update(Project project) {
		Project oldProject = projectDao.search(project.getPid());
		if(oldProject.getStatus() == 0 && project.getUid()==null){
			return true;
		}
		if(oldProject.getStatus() == 1 && project.getCheckId()==null){
			return true;
		}
		if(oldProject.getStatus() == 2 && project.getAuthorizedId()==null){
			return true;
		}
		boolean isSuccess = projectDao.update(project);
		if(!isSuccess) {
			throw new NameException("项目信息更新失败，请稍候重新尝试");
		}
		project.setStatus(oldProject.getStatus()+1);
		return true;
	}

	public boolean delete(int id) {
		if(id<0) {
			throw new NameException("发生了未知错误，请选择要删除的记录并重新尝试");
		}
		boolean isSuccess = projectDao.delete(id);
		if(!isSuccess) {
			throw new NameException("删除项目信息失败，请稍候重新尝试");
		}
		return true;
	}

	public Project search(int id) {
		if(id<0) {
			throw new NameException("发生了未知错误，请选择要删除的记录并重新尝试");
		}
		Project project = projectDao.search(id);
		if(project == null) {
			throw new NameException("该项目不存在");
		}
		return project;
	}

	public List<Project> findProjects() {
		List<Project> list = projectDao.findProjects();
		return list;
	}

	public List<Project> findProjectsByStatus(int status) {
		if(status < 0){
			status = 0;
		}
		List<Project> list = projectDao.findProjectsByStatus(status);
		return list;
	}
	
	public boolean uploadImg(HttpRequest request) {
		return true;
	}

	public String statistics(String iden,int id) {
		if(Tools.isEmpty(iden)){
			throw new NameException("用户身份为空，请重新登录后再尝试！");
		}
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> mapper = new HashMap<String, String>();
		mapper.put("0", "待设计");
		mapper.put("1", "待审核");
		mapper.put("2", "待审查");
		mapper.put("3", "已完成");
		if("admin".equals(iden)){
			list = projectDao.statistics();
		}else{
			list = projectDao.getTotal(id);
		}
		for (HashMap<String, String> hashMap : list) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("value",String.valueOf(hashMap.get("count(*)")));
			map.put("name",mapper.get(String.valueOf(hashMap.get("status"))));
			result.add(map);
		}
		Gson gson = new Gson();
		return gson.toJson(result);
	}
	
}

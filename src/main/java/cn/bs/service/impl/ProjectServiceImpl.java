package cn.bs.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cn.bs.dao.ProjectDao;
import cn.bs.entity.NUser;
import cn.bs.entity.Project;
import cn.bs.entity.Result;
import cn.bs.service.NUserService;
import cn.bs.service.NameException;
import cn.bs.service.ProjectService;
import cn.bs.tools.Tools;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	
	final static HashMap<Integer, String> MAPPER = new HashMap<Integer, String>();
	
	
	@Resource
	private ProjectDao projectDao;
	
	@Resource
	private NUserService ns;
	
	public Project add(Project project) {
		if(Tools.isEmpty(project.getpName() + project.getUnitName() + project.getContacts() + project.getcPhone())){
			throw new NameException("必填信息不能为空，请检查后重新输入提交");
		}
		String phoneReg = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
		if(!project.getcPhone().matches(phoneReg)){
			throw new NameException("手机号格式不正确，请检查后重新输入");
		}
		if(project.getUid() == null || project.getUid() ==-1) {
			project.setStatus(0);
		}
		setVariousName(project);
		project.setStatus(1);
		boolean isSuccess = projectDao.add(project);
		if(!isSuccess) {
			throw new NameException("创建项目失败，请稍后重新尝试");
		}
		return project;
	}
	
	public void onInit() {
		MAPPER.put(0, "待分配");
		MAPPER.put(1, "待设计");
		MAPPER.put(2, "待审核");
		MAPPER.put(3, "待审查");
		MAPPER.put(4, "已完成");
	}
	
	public String setName(Integer id,String errMsg) {
		if(id!=null && id!=-1) {
			NUser user = ns.findById(id);
			if(user == null) {
				throw new NameException(errMsg);
			}
			return user.getuName();
		}
		return null;
	}
	public void setVariousName(Project project) {
		project.setuName(setName(project.getUid(), "该设计人员不存在或者发生了未知的错误，请稍后重新尝试"));
		project.setCheckName(setName(project.getCheckId(), "该审图人员不存在或者发生了未知的错误，请稍后重新尝试"));
		project.setAuthorizedName(setName(project.getAuthorizedId(), "该审核人员不存在或者发生了未知的错误，请稍后重新尝试"));
	}
	public boolean update(Project project) {
		/*Project oldProject = projectDao.search(project.getPid());
		if(oldProject.getStatus() == 0 && project.getUid()==null){
			return true;
		}
		if(oldProject.getStatus() == 1 && project.getCheckId()==null){
			return true;
		}
		if(oldProject.getStatus() == 2 && project.getAuthorizedId()==null){
			return true;
		}*/
		setVariousName(project);
		if(project.getBlueprint()!=null && project.getBlueprint().trim()!=null) {
			project.setStatus(2);
		}
		if(project.getAdvise()!=null && project.getAdvise().trim()!=null) {
			project.setStatus(3);
		}
		if(project.getResult()!=null) {
			project.setStatus(4);
		}
		boolean isSuccess = projectDao.update(project);
		if(!isSuccess) {
			throw new NameException("项目信息更新失败，请稍候重新尝试");
		}
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

	public List<Project> findProjectsByStatus(int status,Integer id,String type) {
		if(id == null) {
			throw new NameException("用户不能为空，请清除缓存后重新登录尝试");
		}
		if(status < 0){
			status = 0;
		}
		if(type == null) {
			type = "design";
		}
		List<Project> list = projectDao.findProjectsByStatus(status);
		List<Project> result = new ArrayList<Project>();
		if("design".equals(type)) {
			for (Project project : list) {
				if(project.getUid() == id) {
					result.add(project);
				}
			}
		}else if("check".equals(type)){
			for (Project project : list) {
				if(project.getCheckId() == id) {
					result.add(project);
				}
			}
		}else {
			for (Project project : list) {
				if(project.getAuthorizedId() == id) {
					result.add(project);
				}
			}
		}
		return result;
	}
	
	public boolean uploadImg(HttpRequest request) {
		return true;
	}

	public String statistics(String iden,int id) {
		onInit();
		if(Tools.isEmpty(iden)){
			throw new NameException("用户身份为空，请重新登录后再尝试！");
		}
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
		if("admin".equals(iden)){
			list = projectDao.statistics();
		}else{
			list = projectDao.getTotal(id);
		}
		for (HashMap<String, String> hashMap : list) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("value",String.valueOf(hashMap.get("count(*)")));
			map.put("name",MAPPER.get(String.valueOf(hashMap.get("status"))));
			result.add(map);
		}
		Gson gson = new Gson();
		return gson.toJson(result);
	}

	public String statisticsForAdmin() {
		onInit();
		List<Result> list = projectDao.findProjectsForAdmin();
		List<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();
		for (Result result : list) {
			HashMap<String, String> map = new HashMap<String, String>();
			Integer status = result.getStatus();
			status = status==null?0:status;
			Integer count = result.getCount();
			count = count==null?0:count;
			map.put("name", MAPPER.get(status));
			map.put("value", String.valueOf(count));
			data.add(map);
		}
		Gson gson = new Gson();
		return gson.toJson(data);
	}

	public String statisticsForUser(Integer id) {
		onInit();
		List<Result> list = projectDao.findProjectsForUser(id);
		List<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();
		for (Result result : list) {
			HashMap<String, String> map = new HashMap<String, String>();
			Integer status = result.getStatus();
			status = status==null?0:status;
			Integer count = result.getCount();
			count = count==null?0:count;
			map.put("name", MAPPER.get(status));
			map.put("value", String.valueOf(count));
			data.add(map);
		}
		Gson gson = new Gson();
		return gson.toJson(data);
	}

	public String view(Integer id) {
		if(id == null || id==-1) {
			throw new NameException("发生了未知的错误，请重新登录后尝试");
		}
		Project project = projectDao.search(id);
		if(project == null) {
			throw new NameException("该项目不存在，请重新尝试");
		}
		String filePath = "d:" +File.separator + "fzTemp" +File.separator+ "test.bat";
		File file = new File(filePath);
		String path = project.getBlueprint();
		if(path == null || "null".equals(path)) {
			throw new NameException("图纸文件不存在!!!");
		}
		String content = "@echo off\nstart "+path;
		boolean isSuccess = true;
		try {
			isSuccess = Tools.writeTxtFile(content, file);
		} catch (Exception e) {
			throw new NameException("发生了未知的错误，请稍后重新尝试");
		}
		return "success";
	}

	public List<Project> viewProjects(Integer id, Integer userType) {
		List<Project> list = projectDao.viewProject(id);
		List<Project> result = new ArrayList<Project>();
		if(userType == 1) {
			for (Project project : list) {
				if(project.getUid()!=null &&project.getUid() == id) {
					result.add(project);
				}
			}
		}else if(userType == 2) {
			for (Project project : list) {
				if(project.getCheckId()!=null &&project.getCheckId() == id) {
					result.add(project);
				}
			}
		}else {
			for (Project project : list) {
				if(project.getAuthorizedId()!=null &&project.getAuthorizedId() == id) {
					result.add(project);
				}
			}
		}
		return result;
	}
	
}

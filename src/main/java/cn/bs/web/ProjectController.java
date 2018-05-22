package cn.bs.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.bs.entity.Project;
import cn.bs.service.ProjectService;
import cn.bs.util.JsonResult;

@Controller
@RequestMapping("/Project")
public class ProjectController extends BaseController{
	
	@Resource
	private ProjectService projectService;
	
	@RequestMapping("/add.do")
	@ResponseBody
	//返回值：{state:0,data:{id...}}
	//返回值：{state:1,message:"用户名..."}

	public JsonResult<Project> add(Integer uid,Integer checkId,Integer authorizedId,String majorType,String pName,
			Integer pType,String unitName,String contacts,String cPhone,Integer status,String blueprint){
		Project project = new Project();
		project.setAuthorizedId(authorizedId);
		project.setBlueprint(blueprint);
		project.setCheckId(checkId);
		project.setContacts(contacts);
		project.setcPhone(cPhone);
		project.setMajorType(majorType);
		project.setpName(pName);
		project.setpType(pType);
		project.setStatus(status);
		project.setUid(uid);
		project.setUnitName(unitName);
		projectService.add(project);
		return new JsonResult<Project>(project);
	}
	/**
	 * 注册控制器
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public JsonResult<Project> update(
			int uid,String name,int checkId,int authorizedId,String majorType,String pName,
			int pType,String unitName,String contacts,String cPhone,int status,String blueprint){
		Project project = new Project();
		project.setAuthorizedId(authorizedId);
		project.setBlueprint(blueprint);
		project.setCheckId(checkId);
		project.setContacts(contacts);
		project.setcPhone(cPhone);
		project.setMajorType(majorType);
		project.setpName(pName);
		project.setpType(pType);
		project.setStatus(status);
		project.setUid(uid);
		project.setUnitName(unitName);
		projectService.update(project);
		return new JsonResult<Project>(project);
	}
	@RequestMapping("/delete.do")
	@ResponseBody
	public JsonResult<Boolean> delete(Integer id){
		boolean isTrue = projectService.delete(id);
		return new JsonResult<Boolean>(isTrue);
	}
	
	@RequestMapping("/find.do")
	@ResponseBody
	public JsonResult<Project> find(Integer id){
		Project project = projectService.search(id);
		return new JsonResult<Project>(project);
	}
	
	@RequestMapping("/searchProjects.do")
	@ResponseBody
	public JsonResult<PageInfo> searchProjects(@RequestParam(value="pn", defaultValue="1")Integer pn){
		PageHelper.startPage(pn,5);
		List<Project> list = projectService.findProjects();
		PageInfo<List<Project>> page = new PageInfo(list,5);
		return new JsonResult<PageInfo>(page);
	}
	
	@RequestMapping("/searchProjectsByStatus.do")
	@ResponseBody
	public JsonResult<PageInfo> searchProjects(@RequestParam(value="pn", defaultValue="1")Integer pn,Integer status){
		PageHelper.startPage(pn,5);
		List<Project> list = projectService.findProjectsByStatus(status);
		PageInfo<List<Project>> page = new PageInfo(list,5);
	 	return new JsonResult<PageInfo>(page);
	}
	
	@RequestMapping("/statistics.do")
	@ResponseBody
	public JsonResult<String> statistics(String iden,Integer id){
		String result = projectService.statistics(iden,id);
		return new JsonResult<String>(result);
	}
	
	@RequestMapping("/upload.do")
	@ResponseBody
	public JsonResult<Boolean> uploadImg(HttpRequest request){
		//List<Project> list = projectService.findProjectsByStatus(status);
		return new JsonResult<Boolean>(true);
	}
	
	@RequestMapping("/test.do")
	@ResponseBody
	public JsonResult<PageInfo<List<Project>>> searchByPage(@RequestParam(value="pn", defaultValue="1")Integer pn){
		PageHelper.startPage(pn,5);
		List<Project> list = projectService.findProjects();
		PageInfo<List<Project>> page = new PageInfo(list,5);
		return new JsonResult<PageInfo<List<Project>>>(page);
	}
}

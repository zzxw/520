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

	public JsonResult<Project> add(Integer uid,Integer checkId,Integer authorizedId,Integer majorType,String pName,
			Integer pType,String unitName,String contacts,String cPhone,Integer status){
		Project project = new Project();
		project.setAuthorizedId(authorizedId);
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
			Integer uid,Integer checkId,Integer authorizedId,Integer pid,Integer majorType,String pName,
			Integer pType,String unitName,String contacts,String cPhone,Integer status,String blueprint,String advise,
			String checkName,String authName,Integer result,String uName){
		Project project = new Project();
		project.setPid(pid);
		project.setuName(uName);
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
		project.setAdvise(advise);
		project.setCheckName(checkName);
		project.setAuthorizedName(authName);
		project.setResult(result);
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
	public JsonResult<PageInfo> searchProjects(@RequestParam(value="pn", defaultValue="1")Integer pn,Integer status,Integer id,String type){
		PageHelper.startPage(pn,5);
		List<Project> list = null;
		if(status == null){
			list = projectService.findProjects();
		} else{
			list = projectService.findProjectsByStatus(status,id,type);
		}
		PageInfo<List<Project>> page = new PageInfo(list,5);
	 	return new JsonResult<PageInfo>(page);
	}
		
	@RequestMapping("/statistics.do")
	@ResponseBody
	public JsonResult<String> statistics(String iden,Integer id){		
		String result = projectService.statistics(iden,id);
		return new JsonResult<String>(result);
	}
	
	@RequestMapping("/adminStatistics.do")
	@ResponseBody
	public JsonResult<String> statisticsForAdmin(){		
		String result = projectService.statisticsForAdmin();
		return new JsonResult<String>(result);
	}
	
	@RequestMapping("/userStatistics.do")
	@ResponseBody
	public JsonResult<String> statisticsForUsers(Integer id){		
		String result = projectService.statisticsForUser(id);
		return new JsonResult<String>(result);
	}
	
	@RequestMapping("/upload.do")
	@ResponseBody
	public JsonResult<Boolean> uploadImg(HttpRequest request){
		//List<Project> list = projectService.findProjectsByStatus(status);
		return new JsonResult<Boolean>(true);
	}
	
	@RequestMapping("/Statistical.do")
	@ResponseBody
	public JsonResult<PageInfo<List<Project>>> searchByPage(@RequestParam(value="pn", defaultValue="1")Integer pn){
		PageHelper.startPage(pn,5);
		List<Project> list = projectService.findProjects();
		PageInfo<List<Project>> page = new PageInfo(list,5);
		return new JsonResult<PageInfo<List<Project>>>(page);
	}
	
	@RequestMapping("/view.do")
	@ResponseBody
	public JsonResult<String> view(Integer id){
		String result = projectService.view(id);
		return new JsonResult<String>(result);
	}
	
	@RequestMapping("/viewProjects.do")
	@ResponseBody
	public JsonResult<PageInfo<List<Project>>> viewProjects(@RequestParam(value="pn", defaultValue="1")Integer pn,Integer id,Integer userType){
		PageHelper.startPage(pn,5);
		List<Project> result = projectService.viewProjects(id, userType);
		PageInfo<List<Project>> page = new PageInfo(result,5);
		return new JsonResult<PageInfo<List<Project>>>(page);
	}
}

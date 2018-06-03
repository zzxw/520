package cn.bs.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bs.entity.NUser;
import cn.bs.service.NUserService;
import cn.bs.util.JsonResult;

@Controller
@RequestMapping("/nUser")
public class NUserController extends BaseController{
	@Resource
	private NUserService nService;
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public JsonResult<NUser> regist(String uName,String pwd,String phone,String mail,String address,Integer major,Integer userType){
		NUser nUser = new NUser();
		nUser.setAddress(address);
		nUser.setMail(mail);
		nUser.setUserType(userType);
		nUser.setMajor(major);
		nUser.setPhone(phone);
		nUser.setPwd(pwd);
		nUser.setuName(uName);
		NUser user=nService.regist(nUser);
		return new JsonResult<NUser>(user);
	}
	
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult<NUser> login(String uName,String pwd){
		NUser user=nService.login(uName, pwd);
		return new JsonResult<NUser>(user);
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public JsonResult<Boolean> update
	(String uName,String pwd,String phone,String mail,String address,Integer major,Integer userType){
		NUser nUser = new NUser();
		nUser.setAddress(address);
		nUser.setMajor(major);
		nUser.setMail(mail);
		nUser.setPhone(phone);
		nUser.setPwd(pwd);
		nUser.setUserType(userType);
		nUser.setuName(uName);
		boolean isSuccess=nService.updateInfo(nUser);
		return new JsonResult<Boolean>(isSuccess);
	}
	
	@RequestMapping("/changePwd.do")
	@ResponseBody
	public JsonResult<Boolean> changePwd
	(String uName,String pwd,String newPwd){
		boolean isSuccess=nService.changePwd(uName, pwd, newPwd);
		return new JsonResult<Boolean>(isSuccess);
	}
	
	@RequestMapping("/find.do")
	@ResponseBody
	public JsonResult<NUser> find(String name){
		NUser user=nService.findByName(name);
		return new JsonResult<NUser>(user);
	}
	
	@RequestMapping("/findUsersByMajor.do")
	@ResponseBody
	public JsonResult<List<HashMap<String, String>>> findUsersByMajor(Integer major,Integer userType){
		List<HashMap<String, String>> list = nService.findUsersByMajor(major,userType);
		return new JsonResult<List<HashMap<String, String>>>(list);
	}
	
	@RequestMapping("/findUsersByType.do")
	@ResponseBody
	public JsonResult<List<HashMap<String, String>>> findUsersByType(Integer userType){
		List<HashMap<String, String>> list = nService.findUsersByType(userType);
		return new JsonResult<List<HashMap<String, String>>>(list);
	}
}

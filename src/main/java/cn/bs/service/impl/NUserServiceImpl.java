package cn.bs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
//import org.springframework.util.DigestUtils;

import cn.bs.dao.NUserDao;
import cn.bs.entity.NUser;
import cn.bs.service.NUserService;
import cn.bs.service.NameException;
import cn.bs.service.PasswordException;
import cn.bs.tools.Tools;
@Service("nUserService")
public class NUserServiceImpl implements NUserService {
	@Resource 
	private NUserDao nUserDao;
	public NUser findByName(String name) {
		if(Tools.isEmpty(name)){
			throw new NameException("用户名不能为空");
		}
		NUser nUser = nUserDao.findByName(name);
		/*if(nUser ==  null){
			throw new NameException("该用户不存在");
		}*/
		return nUser;
	}

	public NUser login(String name, String pwd) {
		if(Tools.isEmpty(name+pwd)){
			throw new NameException("用户名和密码不能为空");
		}
		NUser user = nUserDao.findByName(name);
		if(user == null || !pwd.equals(user.getPwd())){
			throw new NameException("用户名或密码错误，请检查后重新输入");
		}
		return user;
	}

	public NUser regist(NUser user) {
		if(Tools.isEmpty(user.getuName() + user.getPwd())){
			throw new NameException("用户名或密码不能为空");
		}
		NUser nUser = findByName(user.getuName());
		if(nUser!=null){
			throw new NameException("该用户名已存在！！！请更换用户名重新注册");
		}
		checkInfo(user);
		//String pwd = DigestUtils.md5Digest(user.getPwd() + "Hello,World");
		int i = nUserDao.regist(user);
		if(i!=1){
			throw new NameException("注册失败，请重新尝试");
		}
		return user;
	}

	public boolean updateInfo(NUser user) {
		NUser nUser = findByName(user.getuName());
		user.setUid(nUser.getUid());
		checkInfo(user);
		int i = nUserDao.updateInfo(user);
		if(i!=1){
			throw new NameException("修改信息失败，请重新尝试");
		}
		return true;
	}
	public void checkInfo(NUser user){
		String passwordReg= "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
		if(!Tools.isEmpty(user.getPwd()) && !user.getPwd().matches(passwordReg)){
			throw new PasswordException("密码要在8-16字符之间，且必须为数字和字母的组合");
		}
		String phoneReg = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
		if(!Tools.isEmpty(user.getPhone()) && !user.getPhone().matches(phoneReg)){
			throw new NameException("手机号格式不正确，请仔细检查!!!");
		}
		String emailReg = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
		if(!Tools.isEmpty(user.getMail()) && !user.getMail().matches(emailReg)) {
			throw new NameException("邮箱格式不正确，请检查后重新输入!!!");
		}
	}

	public List<HashMap<String,String>> findUsersByMajor(String major) {
		if(Tools.isEmpty(major)) {
			major = "";
		}
		List<NUser> list = nUserDao.findUsersByMajor(major);
		List<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
		for (NUser nUser : list) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("name",nUser.getuName());
			map.put("id",String.valueOf(nUser.getUid()));
			result.add(map);
		}
		return result;
	}
	
	public List<HashMap<String,String>> findUsersByType(int userType) {
		if(userType < 0){
			userType = 0;
		}
		List<NUser> list = nUserDao.findUsersByType(userType);
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		for (NUser nUser : list) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("name",nUser.getuName());
			map.put("id",String.valueOf(nUser.getUid()));
			result.add(map);
		}
		return result;
	}

	public boolean changePwd(String userName,String pwd, String newPwd) {
		NUser user = findByName(userName);
		if(Tools.isEmpty(pwd) || !pwd.equals(user.getPwd())) {
			throw new NameException("密码不正确，请检查后重新输入");
		}
		if(Tools.isEmpty(newPwd)) {
			throw new NameException("新密码不能为空，请填写后重写尝试");
		}
		String passwordReg= "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
		if(!newPwd.matches(passwordReg)) {
			throw new PasswordException("密码要在8-16字符之间，且必须为数字和字母的组合");
		}
		return nUserDao.changePwd(userName,newPwd)==1;
	}
}

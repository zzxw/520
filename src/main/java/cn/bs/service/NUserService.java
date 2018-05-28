package cn.bs.service;

import java.util.HashMap;
import java.util.List;

import cn.bs.entity.NUser;

public interface NUserService {
	NUser findByName (String name);
	NUser findById (Integer id);
	NUser login(String name,String pwd);
	NUser regist(NUser user);
	boolean updateInfo(NUser nUser);
	List<HashMap<String,String>> findUsersByMajor(String major);
	List<HashMap<String,String>> findUsersByType(int userType);
	boolean changePwd(String userName,String pwd,String newPwd);
}

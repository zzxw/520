package cn.bs.dao;

import java.util.List;


import cn.bs.entity.NUser;

public interface NUserDao {
	int regist(NUser nUser);
	NUser findByName(String name);//findByName和login方法使用
	int updateInfo(NUser user);
	List<NUser> findUsersByMajor(String major);
	List<NUser> findUsersByType(int userType);
	int changePwd(String uName,String pwd);
}

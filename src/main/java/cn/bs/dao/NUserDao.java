package cn.bs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.bs.entity.NUser;

public interface NUserDao {
	int regist(NUser nUser);
	NUser findByName(String name);//findByName和login方法使用
	NUser findById(Integer id);
	int updateInfo(NUser user);
	List<NUser> findUsersByMajor(@Param("major")Integer major,@Param("userType")Integer userType);
	List<NUser> findUsersByType(int userType);
	int changePwd(@Param("uName")String uName,@Param("pwd")String pwd);
}

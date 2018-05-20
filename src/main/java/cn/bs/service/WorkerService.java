package cn.bs.service;

import java.util.List;

import cn.bs.entity.Worker;

public interface WorkerService {
	Worker findByName(String name);
	Worker login(String name,String pwd);
	Worker regist(Worker worker);
	boolean updateInfo(Worker worker);
	List<String> findUser();
}

package cn.bs.dao;


import java.util.List;

import cn.bs.entity.Worker;

public interface WorkerDao {
	int regist(Worker worker);
	Worker findByName(String name);
	int update(Worker worker);
	List<Worker> findUser();
}

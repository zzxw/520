package cn.bs.dao;


import java.util.HashMap;
import java.util.List;

import cn.bs.entity.Project;

public interface ProjectDao {
	boolean add(Project project);
	boolean update(Project project);
	Project search(int id);
	boolean delete(int id);
	List<Project> findProjects();
	List<Project> findProjectsByStatus(int status);
	List<HashMap<String, String>>statistics();
	List<HashMap<String, String>>getTotal(int id);
}

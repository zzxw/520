package cn.bs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bs.dao.WorkerDao;
import cn.bs.entity.Worker;
import cn.bs.service.NameException;
import cn.bs.service.PasswordException;
import cn.bs.service.WorkerService;
import cn.bs.tools.Tools;
@Service("workerService")
public class WorkerServiceImpl implements WorkerService {
	
	@Resource
	private WorkerDao workerDao;
	public Worker findByName(String name) {
		if(Tools.isEmpty(name)){
			throw new NameException("用户名不能为空");
		}
		Worker worker = workerDao.findByName(name);
		if(worker!=null){
			throw new NameException("该用户名已存在，请重新输入");
		}
		return worker;
	}

	public Worker login(String name, String pwd) {
		if(Tools.isEmpty(name + pwd)){
			throw new NameException("用户名和密码不能为空");
		}
		Worker worker = workerDao.findByName(name);
		if(worker==null || !pwd.equals(worker.getPwd())) {
			throw new NameException("用户名或密码错误，请检查后重新输入");
		}
		return worker;
	}

	public Worker regist(Worker worker) {
		if(Tools.isEmpty(worker.getuName() + worker.getPwd())) {
			throw new NameException("用户名和密码不能为空！！！");
		}
		if(workerDao.findByName(worker.getuName()) != null){
			throw new NameException("该用户名已经存在，请重新输入");
		}
		checkInfo(worker);
		if(workerDao.regist(worker)!=1){
			throw new NameException("抱歉，发生了未知错误，请稍后再试");
		}
 		return worker;
	}

	public boolean updateInfo(Worker worker) {
		checkInfo(worker);
		if(workerDao.update(worker)!=1){
			throw new NameException("抱歉，发生了未知错误，请稍候再试");
		}
		return true;
	}
	public List<String> findUser(){
		List<Worker> list = workerDao.findUser();
		List<String> result = new ArrayList<String>();
		for (Worker worker : list) {
			result.add(worker.getuName());
		}
		return result;
	}
	public void checkInfo(Worker worker) {
		String passwordReg= "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
		if(!Tools.isEmpty(worker.getPwd()) && !worker.getPwd().matches(passwordReg)){
			throw new PasswordException("密码要在8-16字符之间，且必须为数字和字母的组合，请检查后重新输入");
		}
		String phoneReg = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
		if(!Tools.isEmpty(worker.getPhone()) && !worker.getPhone().matches(phoneReg)){
			throw new NameException("手机号格式不正确，请检查后重新输入!!!");
		}
		String emailReg = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
		if(!Tools.isEmpty(worker.getMail()) && !worker.getMail().matches(emailReg)) {
			throw new NameException("邮箱格式不正确，请检查后重新输入!!!");
		}
	}

}

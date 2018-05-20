package cn.bs.entity;

public class Worker {
	private Integer uid;
	private String uName;
	private String pwd;
	private String phone;
	private String mail;
	public Worker() {
		super();
	}
	public Worker(Integer uid, String uName, String pwd, String phone, String mail) {
		super();
		this.uid = uid;
		this.uName = uName;
		this.pwd = pwd;
		this.phone = phone;
		this.mail = mail;
	}
	public Integer getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}

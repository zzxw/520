package cn.bs.entity;

public class NUser {
	private Integer uid;
	private String uName;
	private String pwd;
	private String phone;
	private String mail;
	private String address;
	private String major;
	private Integer userType;
	
	public NUser() {
		super();
	}
	
	public NUser(Integer uid, String uName, String pwd,String mail,Integer userType,String phone, String address, String major) {
		super();
		this.uid = uid;
		this.uName = uName;
		this.pwd = pwd;
		this.phone = phone;
		this.mail = mail;
		this.address = address;
		this.userType = userType;
		this.major = major;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
}

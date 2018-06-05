package cn.bs.entity;

public class Project {
	private Integer pid;
	private Integer uid;
	private String uName;
	private Integer checkId;
	private String checkName;
	private Integer authorizedId;
	private String authorizedName;
	private Integer majorType;
	private String pName;
	private Integer pType;
	private String unitName;
	private String contacts;
	private String cPhone;
	private Integer status;
	private String blueprint;
	private String advise;
	private Integer result;
	public Project() {
		super();
	}
	
	

	public Project(Integer pid, Integer uid, String uName, Integer checkId, String checkName, Integer authorizedId,
			String authorizedName, Integer majorType, String pName, Integer pType, String unitName, String contacts,
			String cPhone, Integer status, String blueprint, String advise,Integer result) {
		super();
		this.pid = pid;
		this.uid = uid;
		this.uName = uName;
		this.checkId = checkId;
		this.checkName = checkName;
		this.authorizedId = authorizedId;
		this.authorizedName = authorizedName;
		this.majorType = majorType;
		this.pName = pName;
		this.pType = pType;
		this.unitName = unitName;
		this.contacts = contacts;
		this.cPhone = cPhone;
		this.status = status;
		this.blueprint = blueprint;
		this.advise = advise;
		this.result = result;
	}



	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getMajorType() {
		return majorType;
	}

	public void setMajorType(Integer majorType) {
		this.majorType = majorType;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBlueprint() {
		return blueprint;
	}

	public void setBlueprint(String blueprint) {
		this.blueprint = blueprint;
	}
	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}


	public Integer getCheckId() {
		return checkId;
	}


	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}


	public Integer getAuthorizedId() {
		return authorizedId;
	}


	public void setAuthorizedId(Integer authorizedId) {
		this.authorizedId = authorizedId;
	}


	public Integer getpType() {
		return pType;
	}


	public void setpType(Integer pType) {
		this.pType = pType;
	}


	public String getcPhone() {
		return cPhone;
	}


	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}
	
	public String getAdvise() {
		return advise;
	}

	public void setAdvise(String advise) {
		this.advise = advise;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getAuthorizedName() {
		return authorizedName;
	}

	public void setAuthorizedName(String authorizedName) {
		this.authorizedName = authorizedName;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}

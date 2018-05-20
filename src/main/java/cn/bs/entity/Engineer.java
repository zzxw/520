package cn.bs.entity;

public class Engineer {
	private int pid;
	private int eId;
	private int uid;
	private int status;
	private String comment;
	private String info;
	public Engineer() {
		super();
	}
	public Engineer(int pid, int eId, int uid, int status, String comment, String info) {
		super();
		this.pid = pid;
		this.eId = eId;
		this.uid = uid;
		this.status = status;
		this.comment = comment;
		this.info = info;
	}
	@Override
	public String toString() {
		return "Engineer [pid=" + pid + ", eId=" + eId + ", uid=" + uid + ", status=" + status + ", comment=" + comment
				+ ", info=" + info + "]";
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int geteId() {
		return eId;
	}
	public void seteId(int eId) {
		this.eId = eId;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}

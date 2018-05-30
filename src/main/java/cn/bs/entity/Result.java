package cn.bs.entity;

public class Result {
	private Integer count;
	private Integer status;
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Result(Integer count, Integer status) {
		super();
		this.count = count;
		this.status = status;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}

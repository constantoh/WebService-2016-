package com.mocomsys.sangsoo.vo;

public class Daily {
	
	private String name;
	private Integer level;
	private Integer co_req_dept_UID;
	private String reg_DATE;
	private String note;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getCo_req_dept_UID() {
		return co_req_dept_UID;
	}

	public void setCo_req_dept_UID(Integer co_req_dept_UID) {
		this.co_req_dept_UID = co_req_dept_UID;
	}

	public String getReg_DATE() {
		return reg_DATE;
	}

	public void setReg_DATE(String reg_DATE) {
		this.reg_DATE = reg_DATE;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}

package com.mocomsys.sangsoo.vo;

public class Site {
	private Integer site_UID;
	private Integer customer_UID;
	private String site_Name;
	private String site_state_UID;
	private String charge_Dept;
	private String note;
	
	
	public Integer getSite_UID() {
		return site_UID;
	}
	public void setSite_UID(Integer site_UID) {
		this.site_UID = site_UID;
	}
	public Integer getCustomer_UID() {
		return customer_UID;
	}
	public void setCustomer_UID(Integer customer_UID) {
		this.customer_UID = customer_UID;
	}
	public String getSite_Name() {
		return site_Name;
	}
	public void setSite_Name(String site_Name) {
		this.site_Name = site_Name;
	}
	public String getSite_state_UID() {
		return site_state_UID;
	}
	public void setSite_state_UID(String site_state_UID) {
		this.site_state_UID = site_state_UID;
	}
	public String getCharge_Dept() {
		return charge_Dept;
	}
	public void setCharge_Dept(String charge_Dept) {
		this.charge_Dept = charge_Dept;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	

}

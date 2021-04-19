package com.mocomsys.sangsoo.vo;

public class Customer {
	
	private Integer customer_UID;
	private String customer_NAME;
	private String business_CATEGORY;
	private Integer grade;
	private String sales_Person;
	public Integer getCustomer_UID() {
		return customer_UID;
	}
	public void setCustomer_UID(Integer customer_UID) {
		this.customer_UID = customer_UID;
	}
	public String getCustomer_NAME() {
		return customer_NAME;
	}
	public void setCustomer_NAME(String customer_NAME) {
		this.customer_NAME = customer_NAME;
	}
	public String getBusiness_CATEGORY() {
		return business_CATEGORY;
	}
	public void setBusiness_CATEGORY(String business_CATEGORY) {
		this.business_CATEGORY = business_CATEGORY;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getSales_Person() {
		return sales_Person;
	}
	public void setSales_Person(String sales_Person) {
		this.sales_Person = sales_Person;
	}
	
	public Customer getCustomerByUID(Integer uid){
		Customer customer = new Customer();
		//customer.setBusiness_CATEGORY();
		return customer;
	}

}

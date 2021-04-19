package com.mocomsys.sangsoo.service;

import javax.jws.WebService;

import com.mocomsys.sangsoo.dao.GetCustomer;
import com.mocomsys.sangsoo.vo.Customer;

@WebService(endpointInterface = "com.mocomsys.sangsoo.service.CustomerCheck")
public class CustomerCheckImpl implements CustomerCheck {
	
	private int many=0;
	
	public int getMany() {
		return many;
	}

	public void setMany(int many) {
		this.many = many;
	}

	public int ManyCustomer(){
		GetCustomer getCustomer = new GetCustomer();
		
		setMany(getCustomer.doGetManyCustomer());
		
		return getMany();
	}

	public Customer[] AllCustomer() {
		
		Customer[] customers;
		customers = new Customer[getMany()];
		
		GetCustomer getCustomer = new GetCustomer();
		customers = getCustomer.doGetAllCustomerInfo(getMany());
		
		return customers;
	}
	
	

}

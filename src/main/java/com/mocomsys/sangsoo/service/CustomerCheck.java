package com.mocomsys.sangsoo.service;

import javax.jws.WebService;

import com.mocomsys.sangsoo.vo.Customer;

@WebService
public interface CustomerCheck {
	
	public int ManyCustomer();
	public Customer[] AllCustomer();

}

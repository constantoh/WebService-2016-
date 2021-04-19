package com.mocomsys.sangsoo.service;

import javax.jws.WebService;

import com.mocomsys.sangsoo.vo.Address;

@WebService
public interface AddressCheck {
	public int doManyAddress();
	public Address[] doGetAddress();
}

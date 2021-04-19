package com.mocomsys.sangsoo.service;

import com.mocomsys.sangsoo.dao.GetAddress;
import com.mocomsys.sangsoo.vo.Address;

public class AddressCheckImpl implements AddressCheck {

	int many=0;
	
	public int getMany() {
		return many;
	}

	public void setMany(int many) {
		this.many = many;
	}

	public Address[] doGetAddress() {
		Address[] addresses = null;
		GetAddress getAddress = new GetAddress();
		addresses = getAddress.doGetAddressAll(getMany());
		
		
		return addresses;
	}

	public int doManyAddress() {
		GetAddress getAddress = new GetAddress();
		setMany(getAddress.doManyAddress());
		
		return getMany();
	}

}

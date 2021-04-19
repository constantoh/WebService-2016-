package com.mocomsys.sangsoo.service;

import javax.jws.WebService;

import com.mocomsys.sangsoo.dao.GetAddress;
import com.mocomsys.sangsoo.dao.GetResource;
import com.mocomsys.sangsoo.vo.Address;
import com.mocomsys.sangsoo.vo.Resource;
import com.mocomsys.sangsoo.vo.Resource2;

@WebService(endpointInterface = "com.mocomsys.sangsoo.service.ResourceCheck")
public class ResourceCheckImpl implements ResourceCheck{

	private int many=0;
	
	
	public int getMany() {
		return many;
	}

	public void setMany(int many) {
		this.many = many;
	}

	public int doManyResource(int roomNo) {
		GetResource getResource = new GetResource();
		setMany(getResource.doGetManyResource(roomNo));
		
		return getMany();
	}
	
	public Resource[] doGetStateResource(int roomNo) {
		
		Resource[] resources;
		GetResource getResource = new GetResource();
		resources = getResource.doGetResource(roomNo, getMany());
		return resources;
	}
	
	
	
	
	public Resource2[] doGetStateAllResource(String startTime) {
		Resource2[] resources;
		GetResource getResource = new GetResource();
		resources = getResource.getAllStateResource(startTime);

		return resources;
	}

	public String doBookResource(int roomNo, String name,String title, String startDate, String endDate, String regDate){
		GetAddress ga = new GetAddress();
		int manyAddress = ga.doManyAddress();
		Address[] addresses = ga.doGetAddressAll(manyAddress);
		int i=0;
		while(true){
			if(addresses[i].getName().equals(name))
				break;
			i++;
		}
		System.out.println("i : " + i + ", name : " + addresses[i].getName());
		String result = null;
		GetResource getResource = new GetResource();
		result = getResource.doBookResource(addresses[i], roomNo, name, title, startDate, endDate, regDate);
		return result;
	}


}

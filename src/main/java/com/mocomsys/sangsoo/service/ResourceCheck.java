package com.mocomsys.sangsoo.service;

import javax.jws.WebService;

import com.mocomsys.sangsoo.vo.Resource;
import com.mocomsys.sangsoo.vo.Resource2;

@WebService
public interface ResourceCheck {

	public int doManyResource(int roomNo);
	public Resource[] doGetStateResource(int roomNo);
	public Resource2[] doGetStateAllResource(String startTime);
	public String doBookResource(int roomNo, String name, String title, String startDate, String endDate, String regDate);
	
}

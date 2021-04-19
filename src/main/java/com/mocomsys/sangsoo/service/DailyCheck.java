package com.mocomsys.sangsoo.service;

import javax.jws.WebService;

import com.mocomsys.sangsoo.vo.Daily;

@WebService
public interface DailyCheck {
	
	public int ManyDaily(String id, Integer level, Integer dept);
	public Daily[] AllDaily(String id, Integer level, Integer dept);
	

}

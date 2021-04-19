package com.mocomsys.sangsoo.service;

import javax.jws.WebService;

import com.mocomsys.sangsoo.dao.GetDaily;
import com.mocomsys.sangsoo.vo.Daily;

@WebService(endpointInterface = "com.mocomsys.sangsoo.service.DailyCheck")
public class DailyCheckImpl implements DailyCheck {

	int many=0;
	
	public int getMany() {
		return many;
	}

	public void setMany(int many) {
		this.many = many;
	}

	public int ManyDaily(String id, Integer level, Integer dept) {
		
		GetDaily getDaily = new GetDaily();
		
		setMany(getDaily.doGetManyDaily(id, level, dept));
		
		return getMany();
	}

	public Daily[] AllDaily(String id, Integer level, Integer dept) {
		
		GetDaily getDaily = new GetDaily();
		Daily[] dailes = getDaily.doGetDaily(getMany(), id, level, dept);
		
		return dailes;
	}

}

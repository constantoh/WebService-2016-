package com.mocomsys.sangsoo.service;

import javax.jws.WebService;

import com.mocomsys.sangsoo.dao.GetEmp;
import com.mocomsys.sangsoo.vo.Emp;

@WebService(endpointInterface = "com.mocomsys.sangsoo.service.EmpCheck")
public class EmpCheckImpl implements EmpCheck{
	
	private int many=0;
	private Emp[] emps = null;

	public int getMany() {
		return many;
	}

	public void setMany(int many) {
		this.many = many;
	}
	

	public Emp[] getEmps() {
		return emps;
	}

	public void setEmps(Emp[] emps) {
		this.emps = emps;
	}

	public int ManyEmp() {
		GetEmp getEmp = new GetEmp();
		
		setMany(getEmp.doGetManyEmp());
		
		return getMany();
	}

	public Emp[] AllEmp() {
		
		emps = new Emp[getMany()];
		
		GetEmp getEmp = new GetEmp();
		
		setEmps(getEmp.doEmpInfo(many));		
		return emps;
	}
}

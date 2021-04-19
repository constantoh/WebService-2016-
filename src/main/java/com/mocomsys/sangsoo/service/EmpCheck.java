package com.mocomsys.sangsoo.service;

import javax.jws.WebService;

import com.mocomsys.sangsoo.vo.Emp;

@WebService
public interface EmpCheck {
	
	public int ManyEmp();
	public Emp[] AllEmp();
	
}

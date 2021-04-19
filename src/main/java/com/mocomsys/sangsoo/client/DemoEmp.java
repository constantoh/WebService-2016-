package com.mocomsys.sangsoo.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.mocomsys.sangsoo.service.EmpCheck;
import com.mocomsys.sangsoo.vo.Emp;

public class DemoEmp {

	public static void main(String[] args) {

		int many;
		Emp[] emps = null;
		
		String serviceUrl = "http://10.10.1.115:8080/WebServiceThird/services/EmpWs";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(EmpCheck.class);
		factory.setAddress(serviceUrl);
		EmpCheck dailyCheck = (EmpCheck)factory.create();
		
		many = dailyCheck.ManyEmp();
		emps = dailyCheck.AllEmp();
		
		for(int i=0; i<many; i++){
			System.out.println(emps[i].getEmp_UID() + "," + emps[i].getEmp_name() + "," 
					+ emps[i].getEmp_level() + "," + emps[i].getDept_UID());
		}
	}
}

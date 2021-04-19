package com.mocomsys.sangsoo.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.mocomsys.sangsoo.service.CustomerCheck;
import com.mocomsys.sangsoo.vo.Customer;

public class DemoCustomer {

	public static void main(String[] args) {
		int many;
		Customer[] customers = null;
		String serviceUrl = "http://10.10.1.115:8080/WebServiceThird/services/CustomerWs";

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(CustomerCheck.class);
		factory.setAddress(serviceUrl);
		CustomerCheck customerCheck = (CustomerCheck)factory.create();
		
		many = customerCheck.ManyCustomer();
		customers = customerCheck.AllCustomer();
		
		for(int i=0; i<many; i++){
			System.out.println(i + ": " + customers[i].getCustomer_UID() + ", " + customers[i].getCustomer_NAME() + ", " 
					+ customers[i].getBusiness_CATEGORY() + ", " + customers[i].getGrade() + ", " + customers[i].getSales_Person());
			
		}
		
	}
}

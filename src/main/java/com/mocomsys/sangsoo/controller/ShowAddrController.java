package com.mocomsys.sangsoo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mocomsys.sangsoo.service.AddressCheck;
import com.mocomsys.sangsoo.vo.Address;

@Controller
public class ShowAddrController {
	
	@RequestMapping(value = "/ShowAddr.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public String ShowAddr(HttpServletRequest request, HttpServletResponse response, Model model){
		System.out.println("###ShowAddrController###");
		String jsonString="";
		
		String serviceUrl = "http://10.10.1.115:8080/WebServiceThird/services/AddrWs";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(AddressCheck.class);
		factory.setAddress(serviceUrl);
		AddressCheck addressCheck = (AddressCheck)factory.create();

		int manyAddr = addressCheck.doManyAddress();
		Address[]addresses = addressCheck.doGetAddress();
		
		jsonString+="{\"addresses\":[";
		for(int i=0; i<manyAddr; i++){
			jsonString+="{\"name\" : \"" + addresses[i].getName() + "\", " + "\"dept\" : \"" + addresses[i].getDept() + "\", \"position\" : \"" + addresses[i].getPosition()
					+ "\", " + "\"email\" : \"" + addresses[i].getMail() + "\", " + "\"phoneNo\" : \"" + addresses[i].getPhoneNumber()
					+ "\"}";
			if(i<manyAddr-1)
				jsonString+=",";
		}
		jsonString +="]}";
		System.out.println(jsonString);
		
		return jsonString;
	}
}

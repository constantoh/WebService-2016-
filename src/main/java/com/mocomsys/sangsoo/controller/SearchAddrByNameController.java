package com.mocomsys.sangsoo.controller;

import java.io.UnsupportedEncodingException;

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
public class SearchAddrByNameController {
	
	@RequestMapping(value = "/SearchAddrByName.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public String SearchAddrByName(HttpServletRequest request, HttpServletResponse response, Model model){
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("SearchAddrByNameController!!!");
	
		String name = (String) request.getParameter("name");
		System.out.println("name : " + name);
		String jsonString = "{\"name\" : \"none\"}";
		

		String serviceUrl = "http://10.10.1.115:8080/WebServiceThird/services/AddrWs";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(AddressCheck.class);
		factory.setAddress(serviceUrl);
		AddressCheck addressCheck = (AddressCheck)factory.create();

		int manyAddr = addressCheck.doManyAddress();
		Address[] addresses = addressCheck.doGetAddress();
		for(int i=0; i<manyAddr; i++){
			if(addresses[i].getName().equals(name)){
				System.out.println("find! dept : " + addresses[i].getDept_user());
				jsonString="{\"name\" : \"" + addresses[i].getName() + "\", " + "\"dept\" : \"" + addresses[i].getDept() + "\", \"position\" : \"" + addresses[i].getPosition()
						+ "\", " + "\"email\" : \"" + addresses[i].getMail() + "\", " + "\"phoneNo\" : \"" + addresses[i].getPhoneNumber() + "\"}";
				break;
			}
		}
		
		System.out.println("jsonString : " + jsonString);
		return jsonString;
	}
	
}

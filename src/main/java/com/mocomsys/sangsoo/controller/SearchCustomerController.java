package com.mocomsys.sangsoo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mocomsys.sangsoo.service.CustomerCheck;
import com.mocomsys.sangsoo.vo.Customer;

@Controller
public class SearchCustomerController {
	
	@RequestMapping(value = "/SearchCustomer.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public String MemberCheck(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		System.out.println("SearchCustomerController!!!!!!!!!!!!!!!!!!!");
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int many;
		Customer[] customers = null;
		String serviceUrl = "http://10.10.1.115:8080/WebServiceThird/services/CustomerWs";

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(CustomerCheck.class);
		factory.setAddress(serviceUrl);
		CustomerCheck customerCheck = (CustomerCheck)factory.create();
		
		many = customerCheck.ManyCustomer();
		customers = customerCheck.AllCustomer();
		
		HttpSession session = request.getSession(true);
		session.setAttribute("many", many);
		session.setAttribute("customers", customers);
		
		String jsonString = "{\"many\":" + many + ",\"customers\":[";
		for(int i=0; i<many; i++){
			jsonString+="{\"uid\":" + "\"" + customers[i].getCustomer_UID() + "\", \"name\":" + "\"" + customers[i].getCustomer_NAME() + "\"}";
			if(i<many-1)
				jsonString+=",";
		}
		jsonString+="]}";
		System.out.println("jsonString : " + jsonString);

		return jsonString;
	}
}
		

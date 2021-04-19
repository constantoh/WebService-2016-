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
public class SearchCustomerByNameController {

	@RequestMapping(value = "/SearchCustomerByName.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public String SearchCustomer(HttpServletRequest request, HttpServletResponse response, Model model) {
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("SearchCustomerByNameController!!!");
	
		String name = (String) request.getParameter("name");
		System.out.println("name : " + name);
		
		String jsonString = "{\"name\" : \"none\"}";
		

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
		
		for(int i=0; i<many; i++){
			if(customers[i].getCustomer_NAME().equals(name)){
				System.out.println("find! dept : " + customers[i].getCustomer_NAME());
				jsonString="{\"name\" : \"" + customers[i].getCustomer_NAME() + "\", " + "\"link\" : \"<a href='CustomInfo.do?cus_uid=" + customers[i].getCustomer_UID() + "'>\"}";
				break;
			}
		}
		
		System.out.println("jsonString : " + jsonString);
		return jsonString;
	}
}

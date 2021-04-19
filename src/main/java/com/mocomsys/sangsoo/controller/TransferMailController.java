package com.mocomsys.sangsoo.controller;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mocomsys.sangsoo.service.MailCheck;

@Controller
public class TransferMailController {
	
	@RequestMapping(value = "/TransferMail.do")
	public String transferMail(HttpServletRequest request, HttpServletResponse response, Model model){
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String result=null;
		
		System.out.println("transferMailController!!");
		String from = (String)request.getParameter("from");
		String to = (String) request.getParameter("to");
		String cc = (String) request.getParameter("cc");
		String subject = (String) request.getParameter("subject");
		String content = (String) request.getParameter("content");
		
		System.out.println("from : " + from + ", to : " + to + ", cc : " + cc
				+ ", subject : " + subject + ", content : " + content);
		
		String serviceURL = "http://10.10.1.115:8080/WebServiceThird/services/MailWs";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(MailCheck.class);
		factory.setAddress(serviceURL);
		MailCheck mailCheck = (MailCheck)factory.create();
		result = mailCheck.doSendMail(from, to, cc, subject, content);
		if(result.equals("success")){
			return "successTransferMail";
		}else{
			return "errorTransferMail";
		}
	}
	

}

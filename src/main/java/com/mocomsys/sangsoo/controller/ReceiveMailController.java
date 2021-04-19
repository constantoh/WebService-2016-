package com.mocomsys.sangsoo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mocomsys.sangsoo.service.MailCheck;
import com.mocomsys.sangsoo.vo.MyMailFlag;
import com.mocomsys.sangsoo.vo.User;

@Controller
public class ReceiveMailController {

	@RequestMapping(value = "/ReceiveMail.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public String ReceiveMail(HttpServletRequest request, HttpServletResponse response, Model model){
		
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");
		
		System.out.println("ReceiveMailController!!!");
		System.out.println("mail id : " + user.getEmail_id());
		String id = user.getEmail_id();
		String pwd = user.getEmail_pwd();
		System.out.println("mail pwd : " + user.getEmail_pwd());
		
		String serviceURL = "http://10.10.1.115:8080/WebServiceThird/services/MailWs";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(MailCheck.class);
		factory.setAddress(serviceURL);
		MailCheck mailCheck = (MailCheck)factory.create();
		MyMailFlag[] mails = mailCheck.receiveBoxMail(id, pwd);
		
		session.setAttribute("mail", mails);
		
		String jsonString = "{ \"mail\" : [";
		int index=0;
		for(int j=mails.length-1; j>=0; j--){
			System.out.println("mail's content : "+ j +", : "  + mails[j].getContent());
			jsonString+="{\"index\" : " + (index++) + ", \"newMail\" : \""+ mails[j].getNewMail() + "\", \"name\" : \"" + mails[j].getName() 
					+ "\", \"title\" : \"" + mails[j].getTitle() + "\", \"date\" : \"" + mails[j].getReceivedDate() 
					+ "\", \"attach\" : \"" + mails[j].getIsAttach() + "\"}";
			if(j>0)
				jsonString+=",";
		}
		jsonString +="]}";
		
		System.out.println("jsonString : " + jsonString);
		
		return jsonString;
	}

}

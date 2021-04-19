package com.mocomsys.sangsoo.service;

import javax.jws.WebService;

import com.mocomsys.sangsoo.vo.MyMailFlag;

@WebService
public interface MailCheck {
	
	public MyMailFlag[] receiveBoxMail(String id, String pwd);
	public MyMailFlag[] sendBoxMail();
	public String doSendMail(String from, String to, String cc, String subject, String content);
}
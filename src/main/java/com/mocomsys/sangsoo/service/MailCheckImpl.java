package com.mocomsys.sangsoo.service;

import com.mocomsys.sangsoo.dao.GetMail;
import com.mocomsys.sangsoo.vo.MyMailFlag;

public class MailCheckImpl implements MailCheck {

	public MyMailFlag[] receiveBoxMail(String id, String pwd) {
		MyMailFlag[] mails = null;
		
		GetMail getMail = new GetMail();
		mails = getMail.receiveMailBox(id, pwd);
		
		return mails;
	}

	public MyMailFlag[] sendBoxMail() {
		MyMailFlag[] mails = null;

		return mails;
	}

	public String doSendMail(String from, String to, String cc, String subject, String content) {
		String result=null;
		
		GetMail getMail = new GetMail();
		result = getMail.transferMail(from, to, cc, subject, content);
		return result;
	}

}

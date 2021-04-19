package com.mocomsys.sangsoo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MailShowPopUpController {

	@RequestMapping(value="/MailShowPopUp.do" , produces = "application/text; charset=utf8")
	public String mailShowPopUp(HttpServletRequest request, HttpServletResponse response, Model model){		
		System.out.println("mailShowPopUpController!!!");
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String mailNoS = (String)request.getParameter("mailNo");
		HttpSession session = request.getSession(true);
		session.setAttribute("mailNo", mailNoS);

		return "mailShowPopUp";
	}
}

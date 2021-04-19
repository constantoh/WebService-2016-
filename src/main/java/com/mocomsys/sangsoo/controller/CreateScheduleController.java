package com.mocomsys.sangsoo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CreateScheduleController {
	@RequestMapping(value = "/CreateSchedule.do")
	public String CreateSchedule(HttpServletRequest request, HttpServletResponse response, Model model){
		
		System.out.println("************ createScheduleController ***************");
		
		String token = (String)request.getParameter("token");
		HttpSession session = request.getSession(true);
		session.setAttribute("token", token);
		
		
		System.out.println("new token : " + token);
		return "createSchedule";
	}

}
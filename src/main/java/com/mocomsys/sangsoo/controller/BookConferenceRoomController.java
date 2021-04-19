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

import com.mocomsys.sangsoo.service.ResourceCheck;
import com.mocomsys.sangsoo.vo.User;

@Controller
public class BookConferenceRoomController {

	@RequestMapping(value = "/BookConferenceRoom.do")
	public String CreateSchedule(HttpServletRequest request, HttpServletResponse response, Model model){
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("BookConferenceRoomController@@@");
		
		HttpSession session = request.getSession();
		Integer roomNoS = (Integer)session.getAttribute("roomNo");
		int roomNo = roomNoS.intValue();
		User user = (User)session.getAttribute("user");
		String name = user.getName();
		System.out.println("BookConferenceRoom : roomNo : " + roomNo);
		System.out.println(", name : " + name);
		String title = (String)request.getParameter("title");
		String cause = (String)request.getParameter("cause");
		
		System.out.println("title : " + title);
		System.out.println("cause : " + cause);
		String month1 = (String)request.getParameter("month1");
		if(month1.equals("1") || month1.equals("2") || month1.equals("3") || month1.equals("4") || month1.equals("5")
				|| month1.equals("6") || month1.equals("7") || month1.equals("8") || month1.equals("9")){
			month1= "0".concat(month1);
		}
		String day1 = (String)request.getParameter("day1");
		if(day1.equals("1") || day1.equals("2") || day1.equals("3") || day1.equals("4") || day1.equals("5")
				|| day1.equals("6") || day1.equals("7") || day1.equals("8") || day1.equals("9")){
			day1= "0".concat(day1);
		}

		String hour1 = (String)request.getParameter("hour1");
		if(hour1.equals("1") || hour1.equals("2") || hour1.equals("3") || hour1.equals("4") || hour1.equals("5")
				|| hour1.equals("6") || hour1.equals("7") || hour1.equals("8") || hour1.equals("9")){
			hour1= "0".concat(hour1);
		}

		String minute1 = (String)request.getParameter("minute1");
		if(minute1.equals("1") || minute1.equals("2") || minute1.equals("3") || minute1.equals("4") || minute1.equals("5")
				|| minute1.equals("6") || minute1.equals("7") || minute1.equals("8") || minute1.equals("9")){
			minute1= "0".concat(minute1);
		}

		String month2 = (String)request.getParameter("month2");
		if(month2.equals("1") || month2.equals("2") || month2.equals("3") || month2.equals("4") || month2.equals("5")
				|| month2.equals("6") || month2.equals("7") || month2.equals("8") || month2.equals("9")){
			month2= "0".concat(month2);
		}

		String day2= (String)request.getParameter("day2");
		if(day2.equals("1") || day2.equals("2") || day2.equals("3") || day2.equals("4") || day2.equals("5")
				|| day2.equals("6") || day2.equals("7") || day2.equals("8") || day2.equals("9")){
			day2= "0".concat(day2);
		}
		String hour2 = (String)request.getParameter("hour2");
		if(hour2.equals("1") || hour2.equals("2") || hour2.equals("3") || hour2.equals("4") || hour2.equals("5")
				|| hour2.equals("6") || hour2.equals("7") || hour2.equals("8") || hour2.equals("9")){
			hour2= "0".concat(hour2);
		}

		String minute2 = (String)request.getParameter("minute2");
		if(minute2.equals("1") || minute2.equals("2") || minute2.equals("3") || minute2.equals("4") || minute2.equals("5")
				|| minute2.equals("6") || minute2.equals("7") || minute2.equals("8") || minute2.equals("9")){
			minute2= "0".concat(minute2);
		}

		String startTime = "16".concat(month1).concat(day1).concat(hour1).concat(minute1).concat("00");
		String endTime = "16".concat(month2).concat(day2).concat(hour2).concat(minute2).concat("00");
		System.out.println("startTime : " + startTime);
		System.out.println("endTime : " + endTime);

		String serviceURL = "http://10.10.1.115:8080/WebServiceThird/services/ResourceWs";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ResourceCheck.class);
		factory.setAddress(serviceURL);
		ResourceCheck resourceCheck = (ResourceCheck)factory.create();
		String result = resourceCheck.doBookResource(roomNo, name, title, startTime, endTime, "16/07/25");
		System.out.println("result : " + result);
		if(result.equals("success")){
			return "insertResourceSuccess";
		}else{
			return "errorBookRoom";
		}
	}
}

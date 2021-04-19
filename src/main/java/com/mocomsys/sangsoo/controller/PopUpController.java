package com.mocomsys.sangsoo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mocomsys.sangsoo.service.ResourceCheck;
import com.mocomsys.sangsoo.vo.User;

@Controller
public class PopUpController {

	@RequestMapping(value="/PopUp.do")
	public String popUp(HttpServletRequest request, HttpServletResponse response, Model model){
		System.out.println("popUpController!!!");
		
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String roomNoS = (String)request.getParameter("roomNo");
		int roomNo = Integer.parseInt(roomNoS);
		System.out.println("r : " + roomNo);
		HttpSession session = request.getSession();
		session.setAttribute("roomNo", roomNo);
		session.setAttribute("room", changeRoom(roomNo));
		
		
		return "popup";
	}
	
	public String changeRoom(int room){
		//System.out.println("room number : " + room);
		switch(room){
		case 4160:
			return "가변회의실1";
		case 4161:
			return "가변회의실2";
		case 4162:
			return "가변회의실";
		case 2645:
			return "소회의실1";
		case 2646:
			return "소회의실2";
		default:
			return "error";
		}
	}
	
}

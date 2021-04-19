package com.mocomsys.sangsoo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mocomsys.sangsoo.dao.DoIntegratedLogin;
import com.mocomsys.sangsoo.vo.User;

@Controller
public class MemberCheckController {


	@RequestMapping(value = "/MemberCheck.do", produces = "application/text; charset=utf8")
	public String MemberCheck(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		response.setContentType("text/html;charset=utf-8");
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession(true);
		
		User user=null;
		
		System.out.println("MemberCheckController!!!!!!!!!!!!!!!!!!!");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		if(id==null && pwd==null){
			System.out.println("null");
		}else{
			System.out.println("receive");
			session.setAttribute("id", id);
			session.setAttribute("pwd", pwd);
		}
		
		id=(String)session.getAttribute("id");
		pwd=(String)session.getAttribute("pwd");
		System.out.println("(1) id : " + id + ", pwd : " + pwd);
		DoIntegratedLogin login = new DoIntegratedLogin();
		user = login.doIntegratedLogin((String)session.getAttribute("id"), (String)session.getAttribute("pwd"));
		System.out.println("user.getID : " + user.getId() + ", id : " + id);
		if(user.getId().equals(id)){
			System.out.println("success");
			System.out.println("sessin user's name " + user.getName());
			System.out.println("sessin user's f1 " + user.getFavorite1());
			System.out.println("sessin user's f2 " + user.getFavorite2());
			System.out.println("sessin user's f3 " + user.getFavorite3());
			session.setAttribute("id", id);
			session.setAttribute("user", user);
			return "main";
		}else {
			System.out.println("fail");

			return "loginError";
		}
	}
}

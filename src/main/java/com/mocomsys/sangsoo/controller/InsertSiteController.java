package com.mocomsys.sangsoo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mocomsys.sangsoo.dao.InsertSite;

@Controller
public class InsertSiteController {

	@RequestMapping(value = "/InsertSite.do")
	public String InsertSite(HttpServletRequest request, HttpServletResponse response, Model model){
		
		System.out.println("InsertSiteController!!!!!!!!!!!!!!!!!!!!");
		
		HttpSession session = request.getSession(true);
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		int uid = (Integer) session.getAttribute("UID");
		//session.setAttribute("uid", uid);
		String name = (String) request.getParameter("siteName");
		String stateString = (String) request.getParameter("siteState");
		if(stateString.equals("")){
			System.out.println("insertController)error");
			return "error";
		}
		int state = Integer.parseInt(stateString);
		String note = (String)request.getParameter("siteNote");

		System.out.println("uid : " + uid + " name : " + name + " state : " + state + " note : " + note);
		
		InsertSite insertSite = new InsertSite();
		String result = insertSite.insertSiteMethod(uid, name, state, note);
		
		System.out.println("InsertSiteController result is " + result);
		
		return "insertSiteSuccess";
		
	}
}

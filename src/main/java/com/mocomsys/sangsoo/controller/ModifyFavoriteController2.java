package com.mocomsys.sangsoo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mocomsys.sangsoo.dao.DoIntegratedLogin;

@Controller
public class ModifyFavoriteController2 {

	@RequestMapping(value="ModifyFavorite2.do")
	public String modifyFavorite(HttpServletRequest request, HttpServletResponse response, Model model){
		System.out.println("modify2Controller!!!");
		String result="false";
		
		String f1 = (String)request.getParameter("f1");
		String f2 = (String)request.getParameter("f2");
		String f3 = (String)request.getParameter("f3");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		System.out.println("f1 : " + f1 + ", f2 : " + f2 + ", f3 : " + f3);
		System.out.println("id : " + id);
		DoIntegratedLogin doLogin = new DoIntegratedLogin();
		
		result = doLogin.modifyFavorite(f1, f2, f3, id);
		if(result.equals("success")){
			return "successModifyFavorite";
		}else{
			return "integratedError";
		}
	}
}

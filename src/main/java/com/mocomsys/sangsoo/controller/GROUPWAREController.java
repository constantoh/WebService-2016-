package com.mocomsys.sangsoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GROUPWAREController {
	
	@RequestMapping(value="GoGROUPWARE.do")
	public String groupwareController(){
		
		System.out.println("groupwareController");
		return "groupware";
	}

}

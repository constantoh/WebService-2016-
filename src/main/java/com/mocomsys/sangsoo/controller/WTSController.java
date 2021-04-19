package com.mocomsys.sangsoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WTSController {
	@RequestMapping(value = "/GoWTS.do")
	public String wtsController(){
		System.out.println("WTSController");
		
		return "wts";
	}

}

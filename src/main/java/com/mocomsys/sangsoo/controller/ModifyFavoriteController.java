package com.mocomsys.sangsoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ModifyFavoriteController {

	@RequestMapping(value="ModifyFavorite.do")
	public String modifyFavorite(){
		System.out.println("modify1Controller!!!");

		return "modifyFavorite";
	}
}

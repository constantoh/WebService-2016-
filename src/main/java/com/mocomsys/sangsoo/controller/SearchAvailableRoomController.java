package com.mocomsys.sangsoo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mocomsys.sangsoo.service.ResourceCheck;
import com.mocomsys.sangsoo.vo.Resource;
import com.mocomsys.sangsoo.vo.Resource2;

@Controller
public class SearchAvailableRoomController {
	
	@RequestMapping(value = "/SearchAvailableRoom.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public String searchAvailableRoom(HttpServletRequest request, HttpServletResponse response, Model model){
		System.out.println("!!!!!!!!!!!!!searchAvailableRoomController!!!!!!!!!!!!!!!!");
		response.setContentType("text/html;charset=utf-8");
		String jsonString="";
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String startTime = (String)request.getParameter("startTime");
		System.out.println("startTime : " + startTime);
		String serviceURL = "http://10.10.1.115:8080/WebServiceThird/services/ResourceWs";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ResourceCheck.class);
		factory.setAddress(serviceURL);
		ResourceCheck resourceCheck = (ResourceCheck)factory.create();
		Resource2[] resources = resourceCheck.doGetStateAllResource(startTime);
		jsonString += "{\"room\":[";
		for(int i=0; i<resources.length; i++){
				System.out.println("i : " + i + ", no : " + resources[i].getResourceNo());
				int roomNo = Integer.parseInt(resources[i].getResourceNo());
				
				jsonString+="{\"roomName\" : \"" + changeRoom(roomNo) + "\"}";
				if(i<resources.length-1){
					jsonString+=",";
				}
				
		}
		jsonString +="]}";
		
		System.out.println("jsonString : " + jsonString);
		return jsonString;
		
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

package com.mocomsys.sangsoo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mocomsys.sangsoo.service.ResourceCheck;
import com.mocomsys.sangsoo.vo.Resource;

@Controller
public class SearchConferenceRoomController {
	
	@RequestMapping(value = "/SearchConferenceRoom.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public String BookConferenceRoom(HttpServletRequest request, HttpServletResponse response, Model model){
		
		String roomS = (String) request.getParameter("room");
		int room = Integer.parseInt(roomS);
		System.out.println("room : " + room);
		String jsonString="";
		int manyResource;
		System.out.println("!!SearchConferenceRoomController!!");
		
		String serviceURL = "http://10.10.1.115:8080/WebServiceThird/services/ResourceWs";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ResourceCheck.class);
		factory.setAddress(serviceURL);
		ResourceCheck resourceCheck = (ResourceCheck)factory.create();
		manyResource = resourceCheck.doManyResource(room);
		System.out.println("manyResource : " + manyResource);
		
		Resource[] resources = resourceCheck.doGetStateResource(room);
		
		
		jsonString += "{\"resources\":[";
		for(int i=0; i<manyResource; i++){
			String date = resources[i].getStartTime().substring(8, 10);
			//System.out.println(date);
			resources[i].setDate(date);
			date = resources[i].getStartTime().substring(11, 16);
			resources[i].setStartTime(date);
			date = resources[i].getEndTime().substring(11, 16);
			resources[i].setEndTime(date);
			
			jsonString+="{\"room\" : \"" + changeRoom(resources[i].getResourceNo()) + "\", " + "\"title\" : \"" + resources[i].getTitle()
					+ "\", " + "\"reserver\" : \"" + resources[i].getReserver() + "\", " + "\"regDate\" : \"" + resources[i].getRegDate()
					+ "\", " + "\"startTime\" : \"" + resources[i].getStartTime() + "\", " + "\"endTime\" : \"" + resources[i].getEndTime()
					+ "\", " + "\"date\" : \"" + resources[i].getDate() + "\", " + "\"status\" : \"" + changeStatus(resources[i].getStatus()) + "\"}";
			/*
			System.out.println("resources" + i + " : " + resources[i].getResourceNo() + ", " + ", " + resources[i].getRegDate() + 
					", " + resources[i].getReserver() + ", " + resources[i].getStatus() + ", " + resources[i].getStartTime() + ", "
					+ resources[i].getEndTime() + resources[i].getTitle());
					*/
			if(i<manyResource-1)
				jsonString+=",";
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
	public String changeStatus(int status){
		//System.out.println("status number : " + status);
		switch(status){
		case 2:
			return "종료";
		case 1:
			return "진행";
		default:
			return "error";
		}
	}
}
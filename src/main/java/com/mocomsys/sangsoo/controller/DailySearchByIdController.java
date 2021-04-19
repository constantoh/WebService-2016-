package com.mocomsys.sangsoo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mocomsys.sangsoo.service.DailyCheck;
import com.mocomsys.sangsoo.service.EmpCheck;
import com.mocomsys.sangsoo.vo.Daily;
import com.mocomsys.sangsoo.vo.Emp;
import com.mocomsys.sangsoo.vo.User;

@Controller
public class DailySearchByIdController {
	
	@RequestMapping(value="/DailySearchById.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public String DailySearchByName(HttpServletRequest request, HttpServletResponse response, Model model){
		
		int manyEmp = 0;
		Emp[] emps = null;

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user.getWts_id().equals("null")){
			System.out.println("null!");
		}else{
			System.out.println("DailySearchByIdController!!! " + user.getWts_id());
		}
		
		
		String serviceURL = "http://10.10.1.115:8080/WebServiceThird/services/EmpWs";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(EmpCheck.class);
		factory.setAddress(serviceURL);
		EmpCheck empCheck = (EmpCheck)factory.create();
		manyEmp = empCheck.ManyEmp();
		emps = empCheck.AllEmp();
		
		serviceURL= "http://10.10.1.115:8080/WebServiceThird/services/DailyWs";
		factory.setServiceClass(DailyCheck.class);
		factory.setAddress(serviceURL);
		DailyCheck dailyCheck = (DailyCheck)factory.create();
		int empIndex=0;
		while(true){
			System.out.println("names : " + emps[empIndex].getEmp_name() + ", " + user.getName());
			if(emps[empIndex].getEmp_name().equals(user.getName())){
				break;
			}
			empIndex++;
		}
		System.out.println("find emp " + emps[empIndex].getEmp_ID());
		int manyDaily = dailyCheck.ManyDaily(emps[empIndex].getEmp_ID(), emps[empIndex].getEmp_level(), emps[empIndex].getDept_UID());
		Daily[] dailes = dailyCheck.AllDaily(emps[empIndex].getEmp_ID(), emps[empIndex].getEmp_level(), emps[empIndex].getDept_UID());
		
		
		for(int j=0; j<manyDaily; j++){
			System.out.println("i : " + j + " date : " + dailes[j].getReg_DATE() + " note : " + dailes[j].getNote());
		}
		
		String jsonString = "{ \"tr\" : [";
		
		for(int j=0; j<manyDaily; j++){
			String day = dailes[j].getReg_DATE();
			day = day.substring(6, 8);
			System.out.println("day is " + day);
			jsonString+="{\"date\" : \"" + dailes[j].getReg_DATE() + "\", \"day\" : \""+ day + "\", \"name\" : \"" + dailes[j].getName() + "\", \"req_dept\" : \"" + chageCo_REQ_DEPT(dailes[j].getCo_req_dept_UID()) + "\", \"note\" : \"" + dailes[j].getNote() + "\"}";
			if(j<manyDaily-1)
				jsonString+=",";
		}
		jsonString +="]}";
		
		System.out.println("DailySearch jsonString : " + jsonString);
		return jsonString;
		
	}
	public String chageCo_REQ_DEPT(int i){
		
		switch(i){
		case 1:
			return "[회사공통]";
		case 2:
			return "[본부-솔루션영업사업부]";
		case 3:
			return "[본부-서비스사업부]";
		case 4:
			return "[본부-MD사업부]";
		case 5:
			return "[타 본부]";
		case 6:
			return "[TFT(솔루션사업본부)]";
		default:
			return "";
		}
	}

}

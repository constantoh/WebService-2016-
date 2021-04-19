package com.mocomsys.sangsoo.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mocomsys.sangsoo.service.SiteCheck;
import com.mocomsys.sangsoo.vo.Site;

@Controller
public class CustomInfoController {
	
	@RequestMapping(value = "/CustomInfo.do")
	public String CustomInfo(HttpServletRequest request, HttpServletResponse response, Model model){
		
		String uidString = request.getParameter("cus_uid");
		int uid = Integer.parseInt(uidString);
		
		System.out.println("CustomInfoController!!!!!!!!!!!!!!!!!!!" + uid);
		HttpSession session = request.getSession(true);
		session.setAttribute("UID", uid);
		
		int manySite;
		Site[] sites = null;
		Site[] sitesByUID = null;
		
		String serviceUrl = "http://10.10.1.115:8080/WebServiceThird/services/SiteWs";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(SiteCheck.class);
		factory.setAddress(serviceUrl);
		SiteCheck siteCheck = (SiteCheck)factory.create();
		
		manySite = siteCheck.ManySite();
		sites = siteCheck.AllSite();
		
		int manySiteByUID=0;
		sitesByUID = new Site[manySite];
		
		
		for(int i=0; i<manySite; i++){
			if(sites[i].getCustomer_UID() == uid){
				//System.out.println("i : " + i + " manySite : " + manySite + " sites[i].uid : " + sites[i].getCustomer_UID() + " uid : " + uid);
				sitesByUID[manySiteByUID] = sites[i];
				manySiteByUID++;
			}			
		}
		
		session.setAttribute("manySiteByUID", manySiteByUID);
		session.setAttribute("siteByUID", sitesByUID);

		return "customInfo";
	}

}

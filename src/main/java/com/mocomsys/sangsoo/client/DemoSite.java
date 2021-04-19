package com.mocomsys.sangsoo.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.mocomsys.sangsoo.service.SiteCheck;
import com.mocomsys.sangsoo.vo.Site;

public class DemoSite {

	public static void main(String[] args) {

		int many;
		Site[] sites = null;
		
		String serviceUrl = "http://10.10.1.115:8080/WebServiceThird/services/SiteWs";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(SiteCheck.class);
		factory.setAddress(serviceUrl);
		SiteCheck siteCheck = (SiteCheck)factory.create();
		
		many = siteCheck.ManySite();
		sites = siteCheck.AllSite();
		
		for(int i=0; i<many; i++){
			System.out.println(sites[i].getSite_UID() + ", " + sites[i].getCustomer_UID()  + ", " 
					+ sites[i].getSite_Name() + ", " + sites[i].getSite_state_UID() + ", " + sites[i].getCharge_Dept()
					+ ", " + sites[i].getNote());
		}
	}
}

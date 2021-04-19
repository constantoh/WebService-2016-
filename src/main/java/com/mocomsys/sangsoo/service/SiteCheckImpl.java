package com.mocomsys.sangsoo.service;

import javax.jws.WebService;

import com.mocomsys.sangsoo.dao.GetSite;
import com.mocomsys.sangsoo.vo.Site;

@WebService(endpointInterface = "com.mocomsys.sangsoo.service.SiteCheck")
public class SiteCheckImpl implements SiteCheck{

	private int many=0;
	
	
	public int getMany() {
		return many;
	}

	public void setMany(int many) {
		this.many = many;
	}

	public int ManySite() {
		
		GetSite getSite = new GetSite();
		setMany(getSite.doGetManySite());
		
		return getMany();
	}

	public Site[] AllSite() {
		
		Site[] sites = new Site[getMany()];
		GetSite getSite = new GetSite();
		sites = getSite.doGetAllSiteInfo(many);
		
		return sites;
	}
	
	

}

package com.mocomsys.sangsoo.service;

import javax.jws.WebService;
import com.mocomsys.sangsoo.vo.Site;

@WebService
public interface SiteCheck {

	public int ManySite();
	public Site[] AllSite();
}

package com.aaw.sys.service;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.ParametresGeneralSite;

public interface IParamtresGeneralSiteService extends
		IBaseService<ParametresGeneralSite> {

	ParametresGeneralSite findByPN(String string);

}

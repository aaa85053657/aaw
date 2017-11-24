package com.aaw.sys.dao;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.ParametresGeneralSite;

public interface IParametresGeneralSiteDAO extends
		IBaseDAO<ParametresGeneralSite> {

	ParametresGeneralSite findByPN(String string);

}

package com.aaw.sys.service.impl;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.ParametresGeneralSite;
import com.aaw.sys.dao.IParametresGeneralSiteDAO;
import com.aaw.sys.service.IParamtresGeneralSiteService;

@Service
public class ParamtresGeneralSiteService extends
		BaseService<ParametresGeneralSite> implements
		IParamtresGeneralSiteService {
	@Override
	protected IParametresGeneralSiteDAO getDAO() {
		return (IParametresGeneralSiteDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("parametresGeneralSiteDAO") IDAO<ParametresGeneralSite> dao) {
		super.setDAO(dao);
	}

	@Override
	public ParametresGeneralSite findByPN(String string) {
		return getDAO().findByPN(string);
	}

}

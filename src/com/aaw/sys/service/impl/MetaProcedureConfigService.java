package com.aaw.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import molos.plugins.smvc.dao.IDAO;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.MetaProcedureConfig;
import com.aaw.bean.Profile;
import com.aaw.sys.dao.IMetaProcedureConfigDAO;
import com.aaw.sys.service.IMetaProcedureConfigService;

@Service
public class MetaProcedureConfigService extends
		BaseService<MetaProcedureConfig> implements IMetaProcedureConfigService {

	@Override
	protected IMetaProcedureConfigDAO getDAO() {
		return (IMetaProcedureConfigDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("metaProcedureConfigDAO") IDAO<MetaProcedureConfig> dao) {
		super.setDAO(dao);
	}

	@Override
	public List<Profile> listProfileByMetaID(Integer id) {
		return getDAO().listProfileByMetaID(id);
	}

}
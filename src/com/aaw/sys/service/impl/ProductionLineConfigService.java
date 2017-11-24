package com.aaw.sys.service.impl;

import java.util.List;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.MetaProcedure;
import com.aaw.bean.ProductionLineConfig;
import com.aaw.sys.dao.IProductionLineConfigDAO;
import com.aaw.sys.service.IProductionLineConfigService;

@Service
public class ProductionLineConfigService extends
		BaseService<ProductionLineConfig> implements
		IProductionLineConfigService {

	@Override
	protected IProductionLineConfigDAO getDAO() {
		return (IProductionLineConfigDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("productionLineConfigDAO") IDAO<ProductionLineConfig> dao) {
		super.setDAO(dao);
	}

	@Override
	public List<MetaProcedure> queryMetaPListByLineID(Integer id) {
		return getDAO().queryMetaPListByLineID(id);
	}

}
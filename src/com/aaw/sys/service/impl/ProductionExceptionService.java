package com.aaw.sys.service.impl;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.ProductionException;
import com.aaw.sys.dao.IProductionExceptionDAO;
import com.aaw.sys.service.IProductionExceptionService;

@Service
public class ProductionExceptionService extends
		BaseService<ProductionException> implements IProductionExceptionService {
	@Override
	protected IProductionExceptionDAO getDAO() {
		return (IProductionExceptionDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("productionExceptionDAO") IDAO<ProductionException> dao) {
		super.setDAO(dao);
	}

	@Override
	public boolean deleteAndCheck(int id) {
		delete(id);
		return true;
	}

}

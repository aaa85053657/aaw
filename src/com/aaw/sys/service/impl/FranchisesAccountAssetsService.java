package com.aaw.sys.service.impl;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.FranchisesAccountAssets;
import com.aaw.sys.dao.IFranchisesAccountAssetsDAO;
import com.aaw.sys.service.IFranchisesAccountAssetsService;

@Service
public class FranchisesAccountAssetsService extends
		BaseService<FranchisesAccountAssets> implements
		IFranchisesAccountAssetsService {

	@Override
	protected IFranchisesAccountAssetsDAO getDAO() {
		return (IFranchisesAccountAssetsDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("franchisesAccountAssetsDAO") IDAO<FranchisesAccountAssets> dao) {
		super.setDAO(dao);
	}

}
package com.aaw.sys.service.impl;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.FranchisesCommandeRelation;
import com.aaw.sys.dao.IFranchisesCommandeDAO;
import com.aaw.sys.service.IFranchisesCommandeService;

@Service
public class FranchisesCommandeService extends
		BaseService<FranchisesCommandeRelation> implements
		IFranchisesCommandeService {

	@Override
	protected IFranchisesCommandeDAO getDAO() {
		return (IFranchisesCommandeDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("franchisesCommandeDAO") IDAO<FranchisesCommandeRelation> dao) {
		super.setDAO(dao);
	}

	@Override
	public FranchisesCommandeRelation findByCommande(Integer id) {
		return getDAO().findByCommande(id);
	}

}
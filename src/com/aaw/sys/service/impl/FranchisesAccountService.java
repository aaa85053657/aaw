package com.aaw.sys.service.impl;

import java.util.List;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.Franchises;
import com.aaw.bean.FranchisesAccount;
import com.aaw.bean.UpmsAccount;
import com.aaw.sys.dao.IFranchisesAccountDAO;
import com.aaw.sys.service.IFranchisesAccountService;

@Service
public class FranchisesAccountService extends BaseService<FranchisesAccount>
		implements IFranchisesAccountService {

	@Override
	protected IFranchisesAccountDAO getDAO() {
		return (IFranchisesAccountDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("franchisesAccountDAO") IDAO<FranchisesAccount> dao) {
		super.setDAO(dao);
	}

	@Override
	public List<FranchisesAccount> query4Login(UpmsAccount account) {
		return getDAO().query4Login(account);
	}

	@Override
	public FranchisesAccount findByFra(Franchises franchises) {
		return getDAO().findByFra(franchises);
	}

}
package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.Franchises;
import com.aaw.bean.FranchisesAccount;
import com.aaw.bean.UpmsAccount;

public interface IFranchisesAccountDAO extends IBaseDAO<FranchisesAccount> {

	List<FranchisesAccount> query4Login(UpmsAccount account);

	FranchisesAccount findByFra(Franchises franchises);

}
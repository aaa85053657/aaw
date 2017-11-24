package com.aaw.sys.dao;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.FranchisesCommandeRelation;

public interface IFranchisesCommandeDAO extends
		IBaseDAO<FranchisesCommandeRelation> {

	FranchisesCommandeRelation findByCommande(Integer id);

}
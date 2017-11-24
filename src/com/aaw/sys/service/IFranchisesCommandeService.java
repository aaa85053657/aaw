package com.aaw.sys.service;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.FranchisesCommandeRelation;

public interface IFranchisesCommandeService extends
		IBaseService<FranchisesCommandeRelation> {

	FranchisesCommandeRelation findByCommande(Integer id);

}
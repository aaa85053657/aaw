package com.aaw.sys.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.FranchisesCommandeRelation;
import com.aaw.sys.dao.IFranchisesCommandeDAO;

@Repository
@SuppressWarnings("unchecked")
public class FranchisesCommandeDAO extends BaseDAO<FranchisesCommandeRelation>
		implements IFranchisesCommandeDAO {

	@Override
	public FranchisesCommandeRelation findByCommande(Integer id) {
		Criteria c = currentSession().createCriteria(
				FranchisesCommandeRelation.class);
		List<FranchisesCommandeRelation> list = c.add(
				Restrictions.eq("commande.id", id)).list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
package com.aaw.sys.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.Franchises;
import com.aaw.bean.FranchisesAccount;
import com.aaw.bean.UpmsAccount;
import com.aaw.sys.dao.IFranchisesAccountDAO;

@Repository
@SuppressWarnings("unchecked")
public class FranchisesAccountDAO extends BaseDAO<FranchisesAccount> implements
		IFranchisesAccountDAO {

	@Override
	public List<FranchisesAccount> query4Login(UpmsAccount account) {
		Criteria c = currentSession().createCriteria(FranchisesAccount.class);
		c.add(Restrictions.eq("account", account.getName()))
				.add(Restrictions.eq("pazzword", account.getPazzwd()))
				.add(Restrictions.eq("status", 2));
		return c.list();
	}

	@Override
	public FranchisesAccount findByFra(Franchises franchises) {
		Criteria c = currentSession().createCriteria(FranchisesAccount.class);
		List<FranchisesAccount> list = c.add(
				Restrictions.eq("franchises.id", franchises.getId())).list();
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}
package com.aaw.sys.dao.impl;

import java.util.List;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.UpmsAccount;
import com.aaw.sys.dao.IUpmsAccountDAO;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class UpmsAccountDAO extends BaseDAO<UpmsAccount> implements
		IUpmsAccountDAO {

	@Override
	public List<UpmsAccount> query4Login(UpmsAccount account) {
		Criteria c = currentSession().createCriteria(UpmsAccount.class);
		c.add(Restrictions.eq("name", account.getName())).add(
				Restrictions.eq("pazzwd", account.getPazzwd()));
		return c.list();
	}
}
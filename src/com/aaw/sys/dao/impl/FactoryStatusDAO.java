package com.aaw.sys.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.FactoryStatus;
import com.aaw.sys.dao.IFactoryStatusDAO;
import com.aaw.sys.form.FactoryCondition;

@SuppressWarnings("unchecked")
@Repository
public class FactoryStatusDAO extends BaseDAO<FactoryStatus> implements
		IFactoryStatusDAO {

	@Override
	public List<FactoryStatus> findByCondition(FactoryCondition condition) {
		Criteria c = currentSession().createCriteria(FactoryStatus.class);
		if (condition.getStatus() != null) {
			c.add(Restrictions.eq("status", condition.getStatus()));
		}
		return c.list();
	}

	@Override
	public FactoryStatus findByWorksheet(Integer worksheetId) {
		Criteria c = currentSession().createCriteria(FactoryStatus.class);
		c.add(Restrictions.eq("worksheet.id", worksheetId));
		List<FactoryStatus> list = c.list();
		if (npv.isNull(list)) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public List<FactoryStatus> findByWorksheet2(Integer worksheetId) {
		Criteria c = currentSession().createCriteria(FactoryStatus.class);
		c.add(Restrictions.eq("worksheet.id", worksheetId));
		c.addOrder(Order.asc("modificationTime"));
		return c.list();
	}

	@Override
	public FactoryStatus findBySlave(int sid) {
		Criteria c = currentSession().createCriteria(FactoryStatus.class);
		c.add(Restrictions.eq("slaveCommande.id", sid));
		List<FactoryStatus> list = c.list();
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}

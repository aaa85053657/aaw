package com.aaw.sys.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.SlaveCommandeFreeConfig;
import com.aaw.sys.dao.ISlaveCommandeFreeConfigDAO;

@Repository
public class SlaveCommandeFreeConfigDAO extends
		BaseDAO<SlaveCommandeFreeConfig> implements ISlaveCommandeFreeConfigDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<SlaveCommandeFreeConfig> queryListByCommandeId(Integer id) {
		Criteria c = currentSession().createCriteria(
				SlaveCommandeFreeConfig.class);
		c.add(Restrictions.eq("slaveCommande.id", id));
		return c.list();
	}
}
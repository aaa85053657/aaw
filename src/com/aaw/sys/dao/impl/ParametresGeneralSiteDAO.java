package com.aaw.sys.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.ParametresGeneralSite;
import com.aaw.sys.dao.IParametresGeneralSiteDAO;

@SuppressWarnings("unchecked")
@Repository
public class ParametresGeneralSiteDAO extends BaseDAO<ParametresGeneralSite>
		implements IParametresGeneralSiteDAO {

	@Override
	public ParametresGeneralSite findByPN(String string) {
		Criteria c = currentSession().createCriteria(
				ParametresGeneralSite.class);
		List<ParametresGeneralSite> list = c.add(
				Restrictions.eq("parameterName", string)).list();
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}

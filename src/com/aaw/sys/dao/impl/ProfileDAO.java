package com.aaw.sys.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.MetaProcedureConfig;
import com.aaw.bean.Profile;
import com.aaw.sys.dao.IProfileDAO;

@Repository
@SuppressWarnings("unchecked")
public class ProfileDAO extends BaseDAO<Profile> implements IProfileDAO {

	@Override
	public boolean deleteAndCheck(int id) {
		String hql = "select a.id from MetaProcedureConfig as a where a.profile.id=:id";
		List<MetaProcedureConfig> list = currentSession().createQuery(hql)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (npv.isNull(list)) {
			this.delete(id);
			return true;
		}
		return false;
	}

	@Override
	public List<Profile> combobox(int id) {
		String hql = "select a.profile.id from MetaProcedureConfig as a where a.procedure.id=:id";
		List<Integer> list = currentSession().createQuery(hql)
				.setParameter("id", id).list();
		Criteria c = currentSession().createCriteria(Profile.class);
		// c.setProjection(Projections.projectionList()
		// .add(Projections.property("id"))
		// .add(Projections.property("name")));
		if (!npv.isNull(list)) {
			c.add(Restrictions.not(Restrictions.in("id", list)));
		}
		return c.list();
	}
}
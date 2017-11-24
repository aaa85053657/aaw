package com.aaw.sys.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.MetaProcedure;
import com.aaw.bean.MetaProcedureConfig;
import com.aaw.bean.ProductionLineConfig;
import com.aaw.bean.Profile;
import com.aaw.sys.dao.IMetaProcedureDAO;

@Repository
@SuppressWarnings("unchecked")
public class MetaProcedureDAO extends BaseDAO<MetaProcedure> implements
		IMetaProcedureDAO {

	@Override
	public boolean deleteAndCheck(int id) {

		String hql = "select a.id from ProductionLineConfig as a where a.procedure.id=:id";

		List<ProductionLineConfig> list = currentSession().createQuery(hql)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (!npv.isNull(list)) {
			return false;
		}
		String hql2 = "select a.id from MetaProcedureConfig as a where a.procedure.id=:id";
		List<MetaProcedureConfig> list2 = currentSession().createQuery(hql2)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (!npv.isNull(list2)) {
			return false;
		}
		this.delete(id);
		return true;

	}

	@Override
	public List<Profile> listProfile(int id) {
		String hql = "select new Profile(r.id,a.codeId,a.name,a.comments,a.valueDefault,a.valueMin,a.valueMax,a.type,r.priority) from Profile a,MetaProcedureConfig r where a.id=r.profile.id and r.procedure.id=:id order by r.priority";
		return currentSession().createQuery(hql).setParameter("id", id).list();
	}

	@Override
	public List<MetaProcedure> combobox(int id) {
		String hql = "select a.procedure.id from ProductionLineConfig as a where a.line.id=:id";
		List<Integer> list = currentSession().createQuery(hql)
				.setParameter("id", id).list();
		Criteria c = currentSession().createCriteria(MetaProcedure.class);
		if (!npv.isNull(list)) {
			c.add(Restrictions.not(Restrictions.in("id", list)));
		}
		return c.list();
	}

	@Override
	public List<MetaProcedure> all() {
		return currentSession().createCriteria(MetaProcedure.class).list();
	}

	@Override
	public List<MetaProcedure> all(List<Integer> integers) {
		Criteria c = currentSession().createCriteria(MetaProcedure.class);
		if (integers != null && !integers.isEmpty()) {
			for (Integer integer : integers) {
				c.add(Restrictions.ne("id", integer));
			}
		}
		return c.list();
	}

}
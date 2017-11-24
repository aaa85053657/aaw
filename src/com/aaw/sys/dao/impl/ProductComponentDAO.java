package com.aaw.sys.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.ProductComponent;
import com.aaw.bean.ProductModelConfig;
import com.aaw.bean.SlaveCommandeConfig;
import com.aaw.sys.dao.IProductComponentDAO;

@Repository
@SuppressWarnings("unchecked")
public class ProductComponentDAO extends BaseDAO<ProductComponent> implements
		IProductComponentDAO {

	@Override
	public boolean deleteAndCheck(int id) {
		String hql = "select a.id from ProductModelConfig as a where a.component.id=:id";
		List<ProductModelConfig> list = currentSession().createQuery(hql)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (!npv.isNull(list)) {
			return false;
		}
		String hql2 = "select a.id from SlaveCommandeConfig as a where a.component.id=:id";
		List<SlaveCommandeConfig> list2 = currentSession().createQuery(hql2)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (!npv.isNull(list2)) {
			return false;
		}
		this.delete(id);
		return true;
	}

	@Override
	public List<ProductComponent> combobox(int id) {

		String hql = "select a.component.id from ProductModelConfig as a where a.model.id=:id";
		List<Integer> list = currentSession().createQuery(hql)
				.setParameter("id", id).list();
		Criteria c = currentSession().createCriteria(ProductComponent.class);
		// c.setProjection(Projections.projectionList()
		// .add(Projections.property("id"))
		// .add(Projections.property("name")));
		if (!npv.isNull(list)) {
			c.add(Restrictions.not(Restrictions.in("id", list)));
		}
		return c.list();

	}
}
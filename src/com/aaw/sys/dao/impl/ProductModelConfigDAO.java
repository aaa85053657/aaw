package com.aaw.sys.dao.impl;

import java.util.List;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.ProductModelConfig;
import com.aaw.sys.dao.IProductModelConfigDAO;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class ProductModelConfigDAO extends BaseDAO<ProductModelConfig>
		implements IProductModelConfigDAO {

	@Override
	public List<ProductModelConfig> cfgList(int id) {
		String hql = " from ProductModelConfig a where a.model.id=:id order by a.component.id";
		return currentSession().createQuery(hql).setParameter("id", id).list();
	}

	@Override
	public void deleteConfig(int comId, int modelId) {
		String hql = "select p.id from ProductElement p where p.attribute.id="
				+ comId;
		List<Integer> ids = currentSession().createQuery(hql).list();
		String eids = "";
		for (int i = 0; i < ids.size(); i++) {
			if (i < (ids.size() - 1)) {
				eids += ids.get(i) + ",";
			} else {
				eids += ids.get(i);
			}
		}
		if (!eids.equals("")) {
			String hql2 = "delete from ProductModelConfig p where p.model.id="
					+ modelId + " and p.element.id in (" + eids + ")";
			currentSession().createQuery(hql2).executeUpdate();
		}
	}
}
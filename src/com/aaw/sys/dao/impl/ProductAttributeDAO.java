package com.aaw.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.ProductAttribute;
import com.aaw.bean.ProductElement;
import com.aaw.sys.dao.IProductAttributeDAO;

@Repository
@SuppressWarnings("unchecked")
public class ProductAttributeDAO extends BaseDAO<ProductAttribute> implements
		IProductAttributeDAO {
	@Override
	public boolean deleteAndCheck(int id) {
		String hql = "select a.id from ProductElement as a where a.attribute.id=:id";
		List<ProductElement> list = currentSession().createQuery(hql)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (npv.isNull(list)) {
			this.delete(id);
			return true;
		}
		return false;
	}

	@Override
	public List<ProductElement> listElement(int id) {
		String hql = "select new ProductElement(a.id,a.codeId,a.name,a.comments,a.samplepath,a.pixel,a.attribute) from ProductElement a where a.attribute.id=:id";
		return currentSession().createQuery(hql).setParameter("id", id).list();
	}

	@Override
	public List<ProductAttribute> findByComponentId(Integer id) {
		String hql = "from ProductAttribute where component.id=" + id;
		return currentSession().createQuery(hql).list();
	}
}
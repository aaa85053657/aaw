package com.aaw.sys.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.ProductModel;
import com.aaw.bean.ProductModelConfig;
import com.aaw.bean.SlaveCommande;
import com.aaw.sys.dao.IProductModelDAO;
import com.aaw.sys.form.ModelCfgDetail;

@Repository
@SuppressWarnings("unchecked")
public class ProductModelDAO extends BaseDAO<ProductModel> implements
		IProductModelDAO {

	@Override
	public boolean deleteAndCheck(int id) {
		String hql = "select a.id from SlaveCommande as a where a.model.id=:id";
		List<SlaveCommande> list = currentSession().createQuery(hql)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (npv.isNull(list)) {
			this.delete(id);
			return true;
		}
		return false;
	}

	@Override
	public List<ModelCfgDetail> cfgList(int id) {
		// ModelCfgDetail mcd = new ModelCfgDetail(componentID, elementID,
		// attributeID, position, cfgID, componentName, elementName,
		// attributeName);

		String hql = "select new com.aaw.sys.form.ModelCfgDetail(c.id,e.id,e.attribute.id,r.position,r.id,c.name,e.name,e.attribute.name,r.model.id) from "
				+ "ProductElement e ,ProductComponent c,ProductModelConfig r "
				+ "where r.model.id=:id and r.component.id=c.id and r.element.id=e.id order by c.name,e.attribute.name";
		List<ModelCfgDetail> list = currentSession().createQuery(hql)
				.setParameter("id", id).list();
		return list;
	}

	@Override
	public void delete(int id, int cid) {
		String hql = "delete ProductModelConfig r where  r.model.id=:id and r.component.id=:cid";
		currentSession().createQuery(hql).setParameter("id", id)
				.setParameter("cid", cid).executeUpdate();
	}

	@Override
	public List<ProductModelConfig> findByModel(int id) {
		Criteria c = currentSession().createCriteria(ProductModelConfig.class);
		return c.add(Restrictions.eq("model.id", id)).list();
	}

	@Override
	public List<ProductModel> findByProduct() {
		String hql = "from ProductModel where modelStatus=2";
		return currentSession().createQuery(hql).list();
	}
}
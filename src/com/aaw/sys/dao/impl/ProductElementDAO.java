package com.aaw.sys.dao.impl;

import java.io.File;
import java.util.List;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.ProductElement;
import com.aaw.bean.ProductModelConfig;
import com.aaw.bean.SlaveCommandeConfig;
import com.aaw.sys.dao.IProductElementDAO;

import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class ProductElementDAO extends BaseDAO<ProductElement> implements
		IProductElementDAO {

	@Override
	public boolean deleteAndCheck(int id) {

		String hql = "select a.id from ProductModelConfig as a where a.element.id=:id";
		List<ProductModelConfig> list = currentSession().createQuery(hql)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (!npv.isNull(list)) {
			return false;
		}
		String hql2 = "select a.id from SlaveCommandeConfig as a where a.elementLeft.id=:idL or a.elementRight.id=:idR";
		List<SlaveCommandeConfig> list2 = currentSession().createQuery(hql2)
				.setParameter("idL", id).setParameter("idR", id)
				.setFirstResult(0).setMaxResults(1).list();
		if (!npv.isNull(list2)) {
			return false;
		}
		ProductElement pe = this.query(id);
		if (pe.getSamplepath() != null && !pe.getSamplepath().equals("")
				&& new File(pe.getSamplepath()).exists()) {
			File file = new File(pe.getSamplepath());
			file.delete();
		}
		this.delete(id);
		return true;

	}

	@Override
	public List<ProductElement> findByAttrId(int cid) {
		String hql = "from ProductElement where attribute.id=" + cid;
		return currentSession().createQuery(hql).list();
	}

	@Override
	public void updatePath(Integer id, String path) {

	}

	@Override
	public List<ProductElement> findByPath(String path) {
		String hql = "from ProductElement where samplepath=:samplepath";
		return currentSession().createQuery(hql)
				.setParameter("samplepath", path).list();

	}

	@Override
	public List<ProductElement> getPicNull() {
		String hql = "from ProductElement where samplepath is null";
		return currentSession().createQuery(hql).list();
	}
}
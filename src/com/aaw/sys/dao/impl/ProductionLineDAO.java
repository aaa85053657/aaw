package com.aaw.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.MetaProcedure;
import com.aaw.bean.ProductModel;
import com.aaw.bean.ProductionLine;
import com.aaw.sys.dao.IProductionLineDAO;

@Repository
@SuppressWarnings("unchecked")
public class ProductionLineDAO extends BaseDAO<ProductionLine> implements
		IProductionLineDAO {

	@Override
	public boolean deleteAndCheck(int id) {
		String hql = "select a.id from ProductModel as a where a.line.id=:id";
		List<ProductModel> list = currentSession().createQuery(hql)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (npv.isNull(list)) {
			this.delete(id);
			return true;
		}
		return false;
	}

	@Override
	public List<MetaProcedure> listProfile(int id) {
		String hql = "select new MetaProcedure(r.id,a.codeId,a.name,a.comments,r.sequenceNum) from MetaProcedure a,ProductionLineConfig r where a.id=r.procedure.id and r.line.id=:id order by r.sequenceNum";
		return currentSession().createQuery(hql).setParameter("id", id).list();
	}

}
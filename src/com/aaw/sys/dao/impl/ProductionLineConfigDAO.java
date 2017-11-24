package com.aaw.sys.dao.impl;

import java.util.List;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.MetaProcedure;
import com.aaw.bean.ProductionLineConfig;
import com.aaw.sys.dao.IProductionLineConfigDAO;

import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class ProductionLineConfigDAO extends BaseDAO<ProductionLineConfig>
		implements IProductionLineConfigDAO {

	@Override
	public List<MetaProcedure> queryMetaPListByLineID(Integer id) {
		String hql = "select new MetaProcedure(a.procedure.id,a.procedure.codeId,a.procedure.name,a.procedure.comments,a.sequenceNum) from ProductionLineConfig a where a.line.id=:id";
		return currentSession().createQuery(hql).setParameter("id", id).list();
	}
}
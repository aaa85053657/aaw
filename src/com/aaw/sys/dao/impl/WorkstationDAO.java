package com.aaw.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.MetaProcedure;
import com.aaw.bean.Workstation;
import com.aaw.sys.dao.IWorkstationDAO;

@Repository
@SuppressWarnings("unchecked")
public class WorkstationDAO extends BaseDAO<Workstation> implements
		IWorkstationDAO {

	@Override
	public List<MetaProcedure> listCfg(int id) {
		String hql = "select b.procedure from WorkstationProcedure b where b.workstation.id=:id";
		return currentSession().createQuery(hql).setParameter("id", id).list();
	}
}

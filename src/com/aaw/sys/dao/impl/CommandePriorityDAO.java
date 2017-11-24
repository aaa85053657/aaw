package com.aaw.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.CommandePriority;
import com.aaw.bean.MainCommande;
import com.aaw.sys.dao.ICommandePriorityDAO;

@Repository
@SuppressWarnings("unchecked")
public class CommandePriorityDAO extends BaseDAO<CommandePriority> implements
		ICommandePriorityDAO {

	@Override
	public boolean deleteAndCheck(int id) {
		String hql = "select a.id from MainCommande as a where a.priority.id=:id";
		List<MainCommande> list = currentSession().createQuery(hql)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (npv.isNull(list)) {
			this.delete(id);
			return true;
		}
		return false;
	}
}
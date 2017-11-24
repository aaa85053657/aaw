package com.aaw.sys.dao.impl;

import java.util.List;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.CommandeType;
import com.aaw.bean.MainCommande;
import com.aaw.sys.dao.ICommandeTypeDAO;

import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class CommandeTypeDAO extends BaseDAO<CommandeType> implements
		ICommandeTypeDAO {

	@Override
	public boolean deleteAndCheck(int id) {
		String hql = "select a.id from MainCommande as a where a.type.id=:id";
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
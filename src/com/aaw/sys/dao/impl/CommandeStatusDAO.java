package com.aaw.sys.dao.impl;

import java.util.List;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.CommandeStatus;
import com.aaw.sys.dao.ICommandeStatusDAO;

import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class CommandeStatusDAO extends BaseDAO<CommandeStatus> implements
		ICommandeStatusDAO {

	@Override
	public List<CommandeStatus> queryListByMID(int id) {
		String hql = "from CommandeStatus a where a.commande.id=:id";
		return currentSession().createQuery(hql).setParameter("id", id).list();
	}

	@Override
	public void initSt(int id) {
		currentSession()
				.createQuery(
						"update CommandeStatus a set a.status=0 where a.commande.id=:id")
				.setParameter("id", id).executeUpdate();
	}
}
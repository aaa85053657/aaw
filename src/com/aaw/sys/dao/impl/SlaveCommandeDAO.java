package com.aaw.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.SlaveCommande;
import com.aaw.bean.Worksheet;
import com.aaw.sys.dao.ISlaveCommandeDAO;

@Repository
@SuppressWarnings("unchecked")
public class SlaveCommandeDAO extends BaseDAO<SlaveCommande> implements
		ISlaveCommandeDAO {
	@Override
	public void update(SlaveCommande t) {
		String hql = "update SlaveCommande a set a.codeId=:codeID,a.comments=:comments where a.id=:id";
		currentSession().createQuery(hql).setParameter("codeID", t.getCodeId())
				.setParameter("comments", t.getComments())
				.setParameter("id", t.getId()).executeUpdate();
	}

	@Override
	public List<SlaveCommande> list(int id) {
		String hql = "from SlaveCommande a where a.mainCommande.id=:id";
		List<SlaveCommande> list = currentSession().createQuery(hql)
				.setParameter("id", id).list();
		return reList(list);

	}

	public List<SlaveCommande> reList(List<SlaveCommande> list) {
		List<SlaveCommande> temp = list;
		if (list != null && !list.isEmpty()) {
			String hql = "from Worksheet w where w.slaveCommande.id=:sid and w.timeDelete is NULL";
			for (SlaveCommande commande : list) {
				List<Worksheet> list2 = currentSession().createQuery(hql)
						.setParameter("sid", commande.getId()).list();
				if (list2 != null && !list2.isEmpty()) {
					temp.remove(commande);
				}
			}
		}
		return temp;

	}

	@Override
	public boolean delCheck(int id) {
		String hql = "from Worksheet w where w.slaveCommande.id=:sid and w.timeDelete is NULL";
		List<Worksheet> list = currentSession().createQuery(hql)
				.setParameter("sid", id).list();
		if (list != null && list.size() != 0) {
			return false;
		}
		return true;
	}
}
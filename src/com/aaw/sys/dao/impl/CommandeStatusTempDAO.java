package com.aaw.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.CommandeStatusTemp;
import com.aaw.bean.MainCommande;
import com.aaw.sys.dao.ICommandeStatusTempDAO;

@Repository
@SuppressWarnings("unchecked")
public class CommandeStatusTempDAO extends BaseDAO<CommandeStatusTemp>
		implements ICommandeStatusTempDAO {

	@Override
	public boolean hasStartNode() {
		String hql = "select a.id from CommandeStatusTemp as a where a.parentId=0 and a.previousId=0";
		List<MainCommande> list = currentSession().createQuery(hql)
				.setFirstResult(0).setMaxResults(1).list();
		if (npv.isNull(list)) {
			return false;
		}
		return true;
	}

	@Override
	public void update(CommandeStatusTemp t) {
		String hql = "update CommandeStatusTemp a set a.name=:name,a.isSendMail=:ism,a.comments=:cc where a.id=:id";
		currentSession().createQuery(hql).setParameter("id", t.getId())
				.setParameter("name", t.getName())
				.setParameter("cc", t.getComments())
				.setParameter("ism", t.getIsSendMail()).executeUpdate();
	}

	@Override
	public void updatePreNode(Integer previousId, int id) {
		CommandeStatusTemp t = query(previousId);
		if (t.getNextId() > 0) {
			String hql1 = "update CommandeStatusTemp a set a.previousId=:previousId where a.id=:id";
			currentSession().createQuery(hql1)
					.setParameter("id", t.getNextId())
					.setParameter("previousId", id).executeUpdate();
			String hql2 = "update CommandeStatusTemp a set a.nextId=:nextId where a.id=:id";
			currentSession().createQuery(hql2).setParameter("id", id)
					.setParameter("nextId", t.getNextId()).executeUpdate();
		}
		String hql3 = "update CommandeStatusTemp a set a.nextId=:next where a.id=:id";
		currentSession().createQuery(hql3).setParameter("id", previousId)
				.setParameter("next", id).executeUpdate();
	}

	@Override
	public void updateChidNum(Integer parentId) {
		String hql = "update CommandeStatusTemp a set a.hasChildren=a.hasChildren+1 where a.id=:id";
		currentSession().createQuery(hql).setParameter("id", parentId)
				.executeUpdate();
	}

	@Override
	public void delChildren(int id, int cNum) {
		if (cNum > 0) {
			List<CommandeStatusTemp> list = currentSession()
					.createQuery(
							"from CommandeStatusTemp a where a.parentId=:id")
					.setParameter("id", id).list();
			List<CommandeStatusTemp> container = new ArrayList<CommandeStatusTemp>();
			children(list, container);
			String hql = "delete CommandeStatusTemp a where a.id in :id";
			List<Integer> ids = new ArrayList<Integer>();
			for (CommandeStatusTemp c1 : container) {
				if (!ids.contains(c1.getId())) {
					ids.add(c1.getId());
				}
			}
			currentSession().createQuery(hql).setParameterList("id", ids)
					.executeUpdate();
		}
		CommandeStatusTemp ct = query(id);
		Integer pre = ct.getPreviousId();
		Integer next = ct.getNextId();
		if (pre > 0) {
			String h1 = "update CommandeStatusTemp a set a.nextId=:next where a.id=:id";
			currentSession().createQuery(h1).setParameter("id", pre)
					.setParameter("next", next).executeUpdate();
		}
		if (next > 0) {
			String h2 = "update CommandeStatusTemp a set a.previousId=:next where a.id=:id";
			currentSession().createQuery(h2).setParameter("id", next)
					.setParameter("next", pre).executeUpdate();
		}
		delete(id);
	}

	private void children(List<CommandeStatusTemp> list,
			List<CommandeStatusTemp> container) {
		if (!npv.isNull(list)) {
			for (CommandeStatusTemp c : list) {
				container.add(c);
				if (c.getHasChildren() > 0) {
					list = currentSession()
							.createQuery(
									"from CommandeStatusTemp a where a.parentId=:id")
							.setParameter("id", c.getId()).list();
					container.addAll(list);
					children(list, container);
				}
			}
		}
	}

	@Override
	public List<CommandeStatusTemp> findByGID(Integer gid) {
		Criteria c = currentSession().createCriteria(CommandeStatusTemp.class);
		return c.add(Restrictions.eq("groupId", gid)).list();
	}

	@Override
	public boolean uniqueByGID(String propName, String propVal, int id, int gid) {
		Criteria c = currentSession().createCriteria(CommandeStatusTemp.class);
		List<CommandeStatusTemp> list = c
				.add(Restrictions.eq(propName, propVal))
				.add(Restrictions.eq("groupId", gid))
				.add(Restrictions.ne("id", id)).list();
		if (list == null || list.isEmpty()) {
			return true;
		}
		return false;
	}
}

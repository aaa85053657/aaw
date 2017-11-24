package com.aaw.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.ProductModelConfig;
import com.aaw.bean.SlaveCommandeConfig;
import com.aaw.bean.SlaveCommandeFreeConfig;
import com.aaw.sys.dao.ISlaveCommandeConfigDAO;

@Repository
@SuppressWarnings("unchecked")
public class SlaveCommandeConfigDAO extends BaseDAO<SlaveCommandeConfig>
		implements ISlaveCommandeConfigDAO {

	@Override
	public List<SlaveCommandeConfig> queryBySlaveID(int id) {
		String hql = "from SlaveCommandeConfig a where a.slaveCommande.id=:id";
		return currentSession().createQuery(hql).setParameter("id", id).list();
	}

	@Override
	public List<SlaveCommandeFreeConfig> queryBySlaveID2(int id) {
		String hql = "from SlaveCommandeFreeConfig a where a.slaveCommande.id=:id";
		return currentSession().createQuery(hql).setParameter("id", id).list();
	}

	@Override
	public List<ProductModelConfig> queryModelCfgByModel(int id, int cid) {
		String hql = "from ProductModelConfig a where a.component.id=:cid and a.model.id=:id";
		return currentSession().createQuery(hql).setParameter("id", id)
				.setParameter("cid", cid).list();
	}

	@Override
	public void update(SlaveCommandeConfig t) {
		String hql = "update SlaveCommandeConfig a set  ";
		String s1 = null, s2 = null;
		if (t.getElementLeft() != null && t.getElementLeft().getId() != null
				&& t.getElementLeft().getId() > 0) {
			s1 = " a.elementLeft.id=:lid ";
		}
		if (t.getElementRight() != null && t.getElementRight().getId() != null
				&& t.getElementRight().getId() > 0) {
			s2 = " a.elementRight.id=:rid ";
		}
		if (!npv.isNull(s1) && !npv.isNull(s2)) {
			hql += s1 + "," + s2 + " where a.id=:id";
			currentSession().createQuery(hql).setParameter("id", t.getId())
					.setParameter("lid", t.getElementLeft().getId())
					.setParameter("rid", t.getElementRight().getId())
					.executeUpdate();
		} else if (!npv.isNull(s1) && npv.isNull(s2)) {
			hql += s1 + "  where a.id=:id";
			currentSession().createQuery(hql).setParameter("id", t.getId())
					.setParameter("lid", t.getElementLeft().getId())
					.executeUpdate();
		} else {
			hql += s2 + "  where a.id=:id";
			currentSession().createQuery(hql).setParameter("id", t.getId())
					.setParameter("rid", t.getElementRight().getId())
					.executeUpdate();
		}
	}

}
package com.aaw.sys.dao.impl;

import java.util.List;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.ProductElement;
import com.aaw.bean.ProfileType;
import com.aaw.sys.dao.IProfileTypeDAO;

import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class ProfileTypeDAO extends BaseDAO<ProfileType> implements
		IProfileTypeDAO {

	@Override
	public boolean deleteAndCheck(int id) {
		String hql = "select a.id from Profile as a where a.type.id=:id";

		List<ProductElement> list = currentSession().createQuery(hql)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (npv.isNull(list)) {
			this.delete(id);
			return true;
		}
		return false;
	}
}
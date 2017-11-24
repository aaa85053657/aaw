package com.aaw.sys.dao.impl;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.RoleAssetsConfig;
import com.aaw.sys.dao.IRoleAssetsConfigDAO;

import org.springframework.stereotype.Repository;

@Repository
public class RoleAssetsConfigDAO extends BaseDAO<RoleAssetsConfig> implements
		IRoleAssetsConfigDAO {
	@Override
	public void unbind(int id) {
		String hql = "delete from RoleAssetsConfig where upmsRoleId=:id";
		currentSession().createQuery(hql).setParameter("id", id)
				.executeUpdate();
	}

	@Override
	public void bind(int id, Integer[] rids) {
		unbind(id);
		if (!npv.isNull(rids)) {
			for (Integer i : rids) {
				save(new RoleAssetsConfig(i, id));
			}

		}
	}
}
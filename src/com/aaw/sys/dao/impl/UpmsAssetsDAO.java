package com.aaw.sys.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.UpmsAssets;
import com.aaw.sys.dao.IUpmsAssetsDAO;

@SuppressWarnings("unchecked")
@Repository
public class UpmsAssetsDAO extends BaseDAO<UpmsAssets> implements
		IUpmsAssetsDAO {

	@Override
	public List<UpmsAssets> listByAccountID(Integer id) {
		return null;
	}

	@Override
	public List<Integer> listByRoleID(Integer rid) {
		String sql = "select `upms_assets_id` from `role_assets_config` where  `upms_role_id`=:roleID ";
		SQLQuery query = currentSession().createSQLQuery(sql);
		return query.setParameter("roleID", rid).list();
	}

	@Override
	public List<UpmsAssets> listByRID(Integer id) {
		return currentSession()
				.createQuery(
						"select a from RoleAssetsConfig b, UpmsAssets a where a.id=b.upmsAssetsId and b.upmsRoleId=:id")
				.setParameter("id", id).list();
	}

	@Override
	public List<Integer> findBindIDs(int roleID) {
		String hql = "select new list(r.upmsAssetsId) from RoleAssetsConfig r where r.upmsRoleId=:roleID";
		return currentSession().createQuery(hql).setParameter("roleID", roleID)
				.list();
	}

	@Override
	public List<UpmsAssets> listByFran() {
		Criteria c = currentSession().createCriteria(UpmsAssets.class);
		return c.add(Restrictions.ge("id", 34)).list();
	}
}
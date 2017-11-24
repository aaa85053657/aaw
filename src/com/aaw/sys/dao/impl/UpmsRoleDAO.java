package com.aaw.sys.dao.impl;

import java.util.List;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.UpmsRole;
import com.aaw.sys.dao.IUpmsRoleDAO;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public class UpmsRoleDAO extends BaseDAO<UpmsRole> implements IUpmsRoleDAO {

	@Override
	public void saveRelation(int id, Integer[] rids) {
		if (rids[0] != 0) {
			unbind(id);
			String sql = "insert into `role_assets_config` (`upms_role_id`, `upms_assets_id`) values (:roleID, :resourceID)";
			SQLQuery query = currentSession().createSQLQuery(sql);
			for (Integer i : rids) {
				query.setParameter("roleID", id).setParameter("resourceID", i)
						.executeUpdate();
			}
		} else {
			unbind(id);
		}

	}

	@Override
	public List<Integer> relations(int id) {
		String sql = "select `id` from `role_assets_config` where  `upms_role_id`=:roleID";
		SQLQuery query = currentSession().createSQLQuery(sql);
		return query.setParameter("roleID", id).list();
	}

	@Override
	public List<Integer> resourcesId(int id) {
		String sql = "select `upms_assets_id` from `role_assets_config` where  `upms_role_id`=:roleID ";
		SQLQuery query = currentSession().createSQLQuery(sql);
		return query.setParameter("roleID", id).list();
	}

	@Override
	public void unbind(int id) {
		String sql = "delete from `role_assets_config` where (`upms_role_id`=:id)";
		SQLQuery query = currentSession().createSQLQuery(sql);
		query.setParameter("id", id).executeUpdate();
	}
}
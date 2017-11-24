package com.aaw.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.MetaProcedureConfig;
import com.aaw.bean.Profile;
import com.aaw.sys.dao.IMetaProcedureConfigDAO;

@Repository
@SuppressWarnings("unchecked")
public class MetaProcedureConfigDAO extends BaseDAO<MetaProcedureConfig>
		implements IMetaProcedureConfigDAO {

	@Override
	public List<Profile> listProfileByMetaID(Integer id) {
		String hql = "select new Profile(a.profile.id, a.profile.codeId, a.profile.name, a.profile.comments,a.profile.valueDefault, a.profile.valueMin, a.profile.valueMax,a.profile.type, a.priority) from MetaProcedureConfig a where a.procedure.id=:id";
		return currentSession().createQuery(hql).setParameter("id", id).list();
	}

}
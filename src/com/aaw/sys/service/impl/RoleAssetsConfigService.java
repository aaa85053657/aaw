package com.aaw.sys.service.impl;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.RoleAssetsConfig;
import com.aaw.sys.dao.IRoleAssetsConfigDAO;
import com.aaw.sys.service.IRoleAssetsConfigService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import molos.plugins.smvc.dao.IDAO;

@Service
public class RoleAssetsConfigService extends BaseService<RoleAssetsConfig>
		implements IRoleAssetsConfigService {

	@Override
	protected IRoleAssetsConfigDAO getDAO() {
		return (IRoleAssetsConfigDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("roleAssetsConfigDAO") IDAO<RoleAssetsConfig> dao) {
		super.setDAO(dao);
	}

	@Override
	public void unbind(int id) {
		getDAO().unbind(id);
	}

	@Override
	public void bind(int id, Integer[] rids) {
		getDAO().bind(id, rids);
	}
}
package com.aaw.sys.service.impl;

import javax.annotation.Resource;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.UpmsRole;
import com.aaw.sys.dao.IUpmsRoleDAO;
import com.aaw.sys.service.IRoleAssetsConfigService;
import com.aaw.sys.service.IUpmsRoleService;

@Service
public class UpmsRoleService extends BaseService<UpmsRole> implements
		IUpmsRoleService {

	@Override
	protected IUpmsRoleDAO getDAO() {
		return (IUpmsRoleDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("upmsRoleDAO") IDAO<UpmsRole> dao) {
		super.setDAO(dao);
	}

	@Override
	public void saveRelation(int id, Integer[] rids) {
		getDAO().saveRelation(id, rids);

	}

	@Override
	public Object findBind(int id) {
		return getDAO().resourcesId(id);
	}

	@Override
	public void bind(int id, Integer[] rids) {

		roleAssetsConfigService.bind(id, rids);
	}

	@Resource
	private IRoleAssetsConfigService roleAssetsConfigService;

}
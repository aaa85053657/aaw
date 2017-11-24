package com.aaw.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import molos.plugins.smvc.dao.IDAO;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.Profile;
import com.aaw.sys.dao.IProfileDAO;
import com.aaw.sys.service.IMetaProcedureConfigService;
import com.aaw.sys.service.IProfileService;

@Service
public class ProfileService extends BaseService<Profile> implements
		IProfileService {

	@Override
	protected IProfileDAO getDAO() {
		return (IProfileDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("profileDAO") IDAO<Profile> dao) {
		super.setDAO(dao);
	}

	@Override
	public boolean deleteAndCheck(int id) {
		return getDAO().deleteAndCheck(id);
	}

	@Override
	public List<Map<String, Object>> combobox(int id) {

		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<Profile> list = new ArrayList<Profile>();
		if (id < 1) {
			list = list();
		} else {
			list = getDAO().combobox(id);
		}
		for (Profile a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getName());
			reList.add(map);
		}
		return reList;

	}

	@Override
	public List<Profile> listByMetaID(Integer id) {
		return metaProcedureConfigService.listProfileByMetaID(id);
	}

	@Resource
	private IMetaProcedureConfigService metaProcedureConfigService;
}
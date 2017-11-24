package com.aaw.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import molos.plugins.smvc.dao.IDAO;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.ProfileType;
import com.aaw.sys.dao.IProfileTypeDAO;
import com.aaw.sys.service.IProfileTypeService;

@Service
public class ProfileTypeService extends BaseService<ProfileType> implements
		IProfileTypeService {

	@Override
	protected IProfileTypeDAO getDAO() {
		return (IProfileTypeDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("profileTypeDAO") IDAO<ProfileType> dao) {
		super.setDAO(dao);
	}

	@Override
	public List<Map<String, Object>> combobox() {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<ProfileType> list = list();
		for (ProfileType a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getName());
			reList.add(map);
		}
		return reList;
	}

	@Override
	public boolean deleteAndCheck(int id) {
		return getDAO().deleteAndCheck(id);
	}
}
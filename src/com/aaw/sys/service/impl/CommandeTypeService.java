package com.aaw.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.CommandeType;
import com.aaw.sys.dao.ICommandeTypeDAO;
import com.aaw.sys.service.ICommandeTypeService;

@Service
public class CommandeTypeService extends BaseService<CommandeType> implements
		ICommandeTypeService {

	@Override
	protected ICommandeTypeDAO getDAO() {
		return (ICommandeTypeDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("commandeTypeDAO") IDAO<CommandeType> dao) {
		super.setDAO(dao);
	}

	@Override
	public boolean deleteAndCheck(int id) {
		return getDAO().deleteAndCheck(id);
	}

	@Override
	public List<Map<String, Object>> combobox() {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<CommandeType> list = list();
		for (CommandeType a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getName());
			reList.add(map);
		}
		return reList;
	}

	@Override
	public List<Map<String, Object>> combobox2(int type) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<CommandeType> list = list();
		if (type == 2) {
			list.remove(0);
		}
		for (CommandeType a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getName());
			reList.add(map);
		}
		return reList;
	}
}
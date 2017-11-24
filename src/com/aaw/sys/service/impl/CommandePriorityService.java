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
import com.aaw.bean.CommandePriority;
import com.aaw.sys.dao.ICommandePriorityDAO;
import com.aaw.sys.service.ICommandePriorityService;

@Service
public class CommandePriorityService extends BaseService<CommandePriority>
		implements ICommandePriorityService {

	@Override
	protected ICommandePriorityDAO getDAO() {
		return (ICommandePriorityDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("commandePriorityDAO") IDAO<CommandePriority> dao) {
		super.setDAO(dao);
	}

	@Override
	public boolean deleteAndCheck(int id) {
		return getDAO().deleteAndCheck(id);
	}

	@Override
	public List<Map<String, Object>> combobox() {

		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<CommandePriority> list = list();
		for (CommandePriority a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getName());
			reList.add(map);
		}
		return reList;

	}
}
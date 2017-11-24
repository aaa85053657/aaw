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
import com.aaw.bean.ProductComponent;
import com.aaw.sys.dao.IProductComponentDAO;
import com.aaw.sys.service.IProductComponentService;

@Service
public class ProductComponentService extends BaseService<ProductComponent>
		implements IProductComponentService {

	@Override
	protected IProductComponentDAO getDAO() {
		return (IProductComponentDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("productComponentDAO") IDAO<ProductComponent> dao) {
		super.setDAO(dao);
	}

	@Override
	public boolean deleteAndCheck(int id) {
		return getDAO().deleteAndCheck(id);
	}

	@Override
	public List<Map<String, Object>> combobox(int id) {

		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<ProductComponent> list = new ArrayList<ProductComponent>();
		if (id < 1) {
			list = list();
		} else {
			list = getDAO().combobox(id);
		}
		for (ProductComponent a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getName());
			reList.add(map);
		}
		return reList;
	}
}
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
import com.aaw.bean.ProductElement;
import com.aaw.sys.dao.IProductElementDAO;
import com.aaw.sys.service.IProductElementService;

@Service
public class ProductElementService extends BaseService<ProductElement>
		implements IProductElementService {

	@Override
	protected IProductElementDAO getDAO() {
		return (IProductElementDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("productElementDAO") IDAO<ProductElement> dao) {
		super.setDAO(dao);
	}

	@Override
	public boolean deleteAndCheck(int id) {
		return getDAO().deleteAndCheck(id);
	}

	@Override
	public List<Map<String, Object>> combobox() {

		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<ProductElement> list = list();
		for (ProductElement a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getName());
			map.put("group", a.getAttribute().getName());
			reList.add(map);
		}
		return reList;

	}

	@Override
	public List<ProductElement> findByAttrId(int cid) {
		return getDAO().findByAttrId(cid);
	}

	@Override
	public void updatePath(Integer id, String path) {
		getDAO().updatePath(id, path);

	}

	@Override
	public List<ProductElement> findByPath(String path) {
		return getDAO().findByPath(path);

	}

	@Override
	public List<ProductElement> getPicNull() {
		return getDAO().getPicNull();
	}

	@Override
	public ArrayList<List<Object>> getList(List<ProductElement> temp) {
		ArrayList<List<Object>> list = new ArrayList<List<Object>>();
		for (ProductElement p : temp) {
			ArrayList<Object> objs = new ArrayList<Object>();
			objs.add(p.getCodeId());
			objs.add(p.getName());
			objs.add(p.getAttribute().getName());
			list.add(objs);
		}
		return list;
	}
}
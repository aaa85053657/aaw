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
import com.aaw.bean.ProductAttribute;
import com.aaw.bean.ProductElement;
import com.aaw.sys.dao.IProductAttributeDAO;
import com.aaw.sys.service.IProductAttributeService;

@Service
public class ProductAttributeService extends BaseService<ProductAttribute>
		implements IProductAttributeService {

	@Override
	protected IProductAttributeDAO getDAO() {
		return (IProductAttributeDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("productAttributeDAO") IDAO<ProductAttribute> dao) {
		super.setDAO(dao);
	}

	@Override
	public boolean deleteAndCheck(int id) {
		return getDAO().deleteAndCheck(id);
	}

	@Override
	public List<Map<String, Object>> combobox() {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<ProductAttribute> list = list();
		for (ProductAttribute a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getName());
			reList.add(map);
		}
		return reList;
	}

	@Override
	public Map<String, Object> listElement(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ProductElement> list = getDAO().listElement(id);
		map.put("rows", list);
		map.put("total", npv.isNull(list) ? 0 : list.size());
		return map;
	}

	@Override
	public List<ProductAttribute> findByComponentId(Integer id) {
		return getDAO().findByComponentId(id);
	}
}
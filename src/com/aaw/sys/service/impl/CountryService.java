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
import com.aaw.bean.Country;
import com.aaw.sys.dao.ICountryDAO;
import com.aaw.sys.service.ICountryService;

@Service
public class CountryService extends BaseService<Country> implements
		ICountryService {

	@Override
	protected ICountryDAO getDAO() {
		return (ICountryDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("countryDAO") IDAO<Country> dao) {
		super.setDAO(dao);
	}

	@Override
	public boolean deleteAndCheck(int id) {
		return getDAO().deleteAndCheck(id);
	}

	@Override
	public List<Map<String, Object>> combobox() {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<Country> list = list();
		for (Country a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getName());
			reList.add(map);
		}
		return reList;

	}
}
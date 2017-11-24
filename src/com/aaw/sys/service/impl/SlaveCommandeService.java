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
import com.aaw.bean.SlaveCommande;
import com.aaw.sys.dao.ISlaveCommandeDAO;
import com.aaw.sys.service.ISlaveCommandeService;

@Service
public class SlaveCommandeService extends BaseService<SlaveCommande> implements
		ISlaveCommandeService {

	@Override
	protected ISlaveCommandeDAO getDAO() {
		return (ISlaveCommandeDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("slaveCommandeDAO") IDAO<SlaveCommande> dao) {
		super.setDAO(dao);
	}

	@Override
	public List<Map<String, Object>> combobox(int id) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<SlaveCommande> list = getDAO().list(id);
		if (list != null) {
			for (SlaveCommande a : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", a.getId());
				map.put("text", a.getCodeId());
				reList.add(map);
			}
		}
		return reList;
	}

	@Override
	public boolean delCheck(int id) {
		return getDAO().delCheck(id);
	}

}
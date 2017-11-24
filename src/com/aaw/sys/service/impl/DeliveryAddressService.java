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
import com.aaw.bean.DeliveryAddress;
import com.aaw.sys.dao.IDeliveryAddressDAO;
import com.aaw.sys.service.IDeliveryAddressService;

@Service
public class DeliveryAddressService extends BaseService<DeliveryAddress>
		implements IDeliveryAddressService {

	@Override
	protected IDeliveryAddressDAO getDAO() {
		return (IDeliveryAddressDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("deliveryAddressDAO") IDAO<DeliveryAddress> dao) {
		super.setDAO(dao);
	}

	@Override
	public List<Map<String, Object>> combobox(int id) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<DeliveryAddress> list = getDAO().combobox(id);
		for (DeliveryAddress a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getAddress());
			reList.add(map);
		}
		return reList;
	}
}
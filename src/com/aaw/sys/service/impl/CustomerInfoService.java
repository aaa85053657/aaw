package com.aaw.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.CustomerInfo;
import com.aaw.bean.Franchises;
import com.aaw.sys.dao.ICustomerInfoDAO;
import com.aaw.sys.service.ICustomerInfoService;
import com.aaw.sys.service.IFranchisesService;

@Service
public class CustomerInfoService extends BaseService<CustomerInfo> implements
		ICustomerInfoService {

	@Override
	public int save(CustomerInfo t) {
		t.setCodeId(getDAO().codeId());
		return super.save(t);
	}

	@Override
	protected ICustomerInfoDAO getDAO() {
		return (ICustomerInfoDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("customerInfoDAO") IDAO<CustomerInfo> dao) {
		super.setDAO(dao);
	}

	@Override
	public List<Map<String, Object>> combobox(int i) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<CustomerInfo> list = getDAO().list(i);
		for (CustomerInfo a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getFirstName() + " " + a.getMiddleName() + " "
					+ a.getLastName());
			reList.add(map);
		}
		return reList;
	}

	@Override
	public boolean deleteAndCheck(int id) {
		return getDAO().deleteAndCheck(id);
	}

	@Override
	public void updateRF(String codeId) {
		getDAO().updateRF(codeId);

	}

	@Override
	public Object map2(int page, int rows, Franchises franchises) {
		int i = 0;
		if (franchises != null) {
			i = franchises.getId();
		}
		List<CustomerInfo> list = getDAO().list(i);
		for (CustomerInfo c : list) {
			if (c.getCustomerType() != null && c.getCustomerType() == 1) {
				c.setCtName("总店客户");
			} else if (c.getCustomerType() != null && c.getCustomerType() == 2) {
				c.setCtName("加盟商客户");
			} else {
				c.setCtName("");
			}
			if (c.getInputChannel() != null && c.getInputChannel() == 0) {
				c.setIcName("总部");
			} else if (c.getInputChannel() != null && c.getInputChannel() != 0) {
				Franchises f = franchisesService.query(c.getInputChannel());
				if (f != null) {
					c.setIcName(f.getName());
				} else {
					c.setIcName("");
				}
			} else {
				c.setIcName("");
			}

		}
		// reList.put("total", list.size());
		// reList.put("rows", list);
		return list;
	}

	@Resource
	private IFranchisesService franchisesService;
}
package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.CustomerInfo;
import com.aaw.bean.Franchises;

public interface ICustomerInfoService extends IBaseService<CustomerInfo>{

	List<Map<String, Object>> combobox(int i);

	boolean deleteAndCheck(int id);

	void updateRF(String codeId);

	Object map2(int page, int rows, Franchises franchises);
}
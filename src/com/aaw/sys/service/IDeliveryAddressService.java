package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.DeliveryAddress;

public interface IDeliveryAddressService extends IBaseService<DeliveryAddress>{

	List<Map<String, Object>> combobox(int id);
}
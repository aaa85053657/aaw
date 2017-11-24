package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.DeliveryAddress;

public interface IDeliveryAddressDAO extends IBaseDAO<DeliveryAddress> {

	List<DeliveryAddress> combobox(int id);
}
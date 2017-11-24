package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.CustomerInfo;

public interface ICustomerInfoDAO extends IBaseDAO<CustomerInfo> {

	boolean deleteAndCheck(int id);

	void updateRF(String codeId);

	String codeId();

	List<CustomerInfo> list(int i);
}
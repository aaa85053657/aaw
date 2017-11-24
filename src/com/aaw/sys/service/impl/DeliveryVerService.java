package com.aaw.sys.service.impl;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.DelliveryVer;
import com.aaw.sys.dao.IDeliveryVerDAO;
import com.aaw.sys.service.IDeliveryVerService;

@Service
public class DeliveryVerService extends BaseService<DelliveryVer> implements
		IDeliveryVerService {

	@Override
	protected IDeliveryVerDAO getDAO() {
		return (IDeliveryVerDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("deliveryVerDAO") IDAO<DelliveryVer> dao) {
		super.setDAO(dao);
	}

}
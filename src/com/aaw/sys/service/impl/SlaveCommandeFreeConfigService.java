package com.aaw.sys.service.impl;

import java.util.List;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.SlaveCommandeFreeConfig;
import com.aaw.sys.dao.ISlaveCommandeFreeConfigDAO;
import com.aaw.sys.service.ISlaveCommandeFreeConfigService;

@Service
public class SlaveCommandeFreeConfigService extends
		BaseService<SlaveCommandeFreeConfig> implements
		ISlaveCommandeFreeConfigService {

	@Override
	protected ISlaveCommandeFreeConfigDAO getDAO() {
		return (ISlaveCommandeFreeConfigDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("slaveCommandeFreeConfigDAO") IDAO<SlaveCommandeFreeConfig> dao) {
		super.setDAO(dao);
	}

	@Override
	public List<SlaveCommandeFreeConfig> queryListByCommandeId(Integer id) {
		return getDAO().queryListByCommandeId(id);
	}

}
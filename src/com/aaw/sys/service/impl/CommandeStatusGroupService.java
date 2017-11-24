package com.aaw.sys.service.impl;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.CommandeStatusGroup;
import com.aaw.sys.dao.ICommandeStatusGroupDAO;
import com.aaw.sys.service.ICommandeStatusGroupService;

@Service
public class CommandeStatusGroupService extends
		BaseService<CommandeStatusGroup> implements ICommandeStatusGroupService {

	@Override
	protected ICommandeStatusGroupDAO getDAO() {
		return (ICommandeStatusGroupDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("commandeStatusGroupDAO") IDAO<CommandeStatusGroup> dao) {
		super.setDAO(dao);
	}

}
package com.aaw.sys.service.impl;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.CommandeFlow;
import com.aaw.sys.dao.ICommandeFlowDAO;
import com.aaw.sys.service.ICommandeFlowService;

@Service
public class CommandeFlowService extends BaseService<CommandeFlow> implements
		ICommandeFlowService {

	@Override
	protected ICommandeFlowDAO getDAO() {
		return (ICommandeFlowDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("commandeFlowDAO") IDAO<CommandeFlow> dao) {
		super.setDAO(dao);
	}
}
package com.aaw.sys.service.impl;

import java.util.List;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.CommandeStatus;
import com.aaw.sys.dao.ICommandeStatusDAO;
import com.aaw.sys.service.ICommandeStatusService;

@Service
public class CommandeStatusService extends BaseService<CommandeStatus>
		implements ICommandeStatusService {

	@Override
	protected ICommandeStatusDAO getDAO() {
		return (ICommandeStatusDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("commandeStatusDAO") IDAO<CommandeStatus> dao) {
		super.setDAO(dao);
	}

	@Override
	public List<CommandeStatus> queryListByMID(int id) {

		return getDAO().queryListByMID(id);
	}

	@Override
	public void initSt(int id) {
		getDAO().initSt(id);
	}
}
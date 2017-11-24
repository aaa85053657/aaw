package com.aaw.sys.service.impl;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.WorkstationProcedure;
import com.aaw.sys.dao.IWorkstationProcedureDAO;
import com.aaw.sys.service.IWorkstationProcedureService;

@Service
public class WorkstationProcedureService extends
		BaseService<WorkstationProcedure> implements
		IWorkstationProcedureService {

	@Override
	protected IWorkstationProcedureDAO getDAO() {
		return (IWorkstationProcedureDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("workstationProcedureDAO") IDAO<WorkstationProcedure> dao) {
		super.setDAO(dao);
	}

}

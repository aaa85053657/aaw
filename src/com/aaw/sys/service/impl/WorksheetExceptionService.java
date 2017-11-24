package com.aaw.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import molos.plugins.smvc.dao.IDAO;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.WorksheetException;
import com.aaw.sys.dao.IWorksheetExceptionDAO;
import com.aaw.sys.service.IWorksheetExceptionService;

@Service
public class WorksheetExceptionService extends BaseService<WorksheetException>
		implements IWorksheetExceptionService {
	@Override
	protected IWorksheetExceptionDAO getDAO() {
		return (IWorksheetExceptionDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("worksheetExceptionDAO") IDAO<WorksheetException> dao) {
		super.setDAO(dao);
	}

}

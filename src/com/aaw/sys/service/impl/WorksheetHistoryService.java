package com.aaw.sys.service.impl;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.WorksheetHistory;
import com.aaw.sys.dao.IWorksheetHistoryDAO;
import com.aaw.sys.service.IWorksheetHistoryService;

@Service
public class WorksheetHistoryService extends BaseService<WorksheetHistory>
		implements IWorksheetHistoryService {
	@Override
	protected IWorksheetHistoryDAO getDAO() {
		return (IWorksheetHistoryDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("worksheetHistoryDAO") IDAO<WorksheetHistory> dao) {
		super.setDAO(dao);
	}

}

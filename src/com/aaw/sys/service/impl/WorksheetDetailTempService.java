package com.aaw.sys.service.impl;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.WorksheetDetailTemp;
import com.aaw.sys.dao.IWorksheetDetailTempDAO;
import com.aaw.sys.service.IWorksheetDetailTempService;

@Service
public class WorksheetDetailTempService extends
		BaseService<WorksheetDetailTemp> implements IWorksheetDetailTempService {
	@Override
	protected IWorksheetDetailTempDAO getDAO() {
		return (IWorksheetDetailTempDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("worksheetDetailTempDAO") IDAO<WorksheetDetailTemp> dao) {
		super.setDAO(dao);
	}

	@Override
	public WorksheetDetailTemp findByDetail(Integer id) {
		return getDAO().findByDetail(id);
	}

	@Override
	public void deleteByWDID(Integer id) {
		 getDAO().deleteByWDID(id);

	}

}

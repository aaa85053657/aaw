package com.aaw.sys.service.impl;

import java.util.List;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.FactoryStatus;
import com.aaw.sys.dao.IFactoryStatusDAO;
import com.aaw.sys.form.FactoryCondition;
import com.aaw.sys.service.IFactoryStatusService;

@Service
public class FactoryStatusService extends BaseService<FactoryStatus> implements
		IFactoryStatusService {
	@Override
	protected IFactoryStatusDAO getDAO() {
		return (IFactoryStatusDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("factoryStatusDAO") IDAO<FactoryStatus> dao) {
		super.setDAO(dao);
	}

	@Override
	public List<FactoryStatus> findByCondition(FactoryCondition condition) {
		return getDAO().findByCondition(condition);
	}

	@Override
	public FactoryStatus findByWorksheet(Integer worksheetId) {
		return getDAO().findByWorksheet(worksheetId);
	}

	@Override
	public List<FactoryStatus> findByWorksheet2(Integer worksheetId) {
		return getDAO().findByWorksheet2(worksheetId);
	}

	@Override
	public FactoryStatus findBySlave(int sid) {
		return getDAO().findBySlave(sid);
	}
}

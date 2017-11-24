package com.aaw.sys.service.impl;

import java.util.List;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.FactoryStatusDetail;
import com.aaw.sys.dao.IFactoryStatusDetailDAO;
import com.aaw.sys.service.IFactoryStatusDetailService;

@Service
public class FactoryStatusDetailService extends
		BaseService<FactoryStatusDetail> implements IFactoryStatusDetailService {
	@Override
	protected IFactoryStatusDetailDAO getDAO() {
		return (IFactoryStatusDetailDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("factoryStatusDetailDAO") IDAO<FactoryStatusDetail> dao) {
		super.setDAO(dao);
	}

	@Override
	public List<FactoryStatusDetail> findByFSID(int id) {
		return getDAO().findByFSID(id);
	}

	@Override
	public FactoryStatusDetail findByWDID(int did) {
		return getDAO().findByWDID(did);
	}

}

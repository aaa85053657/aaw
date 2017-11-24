package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.FactoryStatusDetail;

public interface IFactoryStatusDetailDAO extends IBaseDAO<FactoryStatusDetail> {

	public List<FactoryStatusDetail> findByFSID(int id);

	public FactoryStatusDetail findByWDID(int did);

}

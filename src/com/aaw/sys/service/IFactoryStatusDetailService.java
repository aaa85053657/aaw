package com.aaw.sys.service;

import java.util.List;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.FactoryStatusDetail;

public interface IFactoryStatusDetailService extends
		IBaseService<FactoryStatusDetail> {

	/**
	 * 根据factorystatus id查找
	 * @param id
	 * @return
	 */
	public List<FactoryStatusDetail> findByFSID(int id);

	/**
	 * 根据worksheetdetail id查找
	 * @param id
	 * @return
	 */
	public FactoryStatusDetail findByWDID(int did);

}

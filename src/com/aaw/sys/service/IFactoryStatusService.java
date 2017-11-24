package com.aaw.sys.service;

import java.util.List;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.FactoryStatus;
import com.aaw.sys.form.FactoryCondition;

public interface IFactoryStatusService extends IBaseService<FactoryStatus> {

	public List<FactoryStatus> findByCondition(FactoryCondition condition);

	public FactoryStatus findByWorksheet(Integer worksheetId);

	public List<FactoryStatus> findByWorksheet2(Integer worksheetId);

	public FactoryStatus findBySlave(int sid);
}

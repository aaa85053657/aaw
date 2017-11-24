package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.FactoryStatus;
import com.aaw.sys.form.FactoryCondition;

public interface IFactoryStatusDAO extends IBaseDAO<FactoryStatus> {

	List<FactoryStatus> findByCondition(FactoryCondition condition);

	FactoryStatus findByWorksheet(Integer worksheetId);

	List<FactoryStatus> findByWorksheet2(Integer worksheetId);

	FactoryStatus findBySlave(int sid);


}

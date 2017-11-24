package com.aaw.sys.dao;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.WorksheetDetailTemp;

public interface IWorksheetDetailTempDAO extends IBaseDAO<WorksheetDetailTemp> {

	WorksheetDetailTemp findByDetail(Integer id);

	void deleteByWDID(Integer id);

}

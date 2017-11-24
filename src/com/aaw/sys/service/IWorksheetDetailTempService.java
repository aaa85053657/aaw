package com.aaw.sys.service;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.WorksheetDetailTemp;

public interface IWorksheetDetailTempService extends
		IBaseService<WorksheetDetailTemp> {

	/**
	 * 根据加工单详情查找信息
	 * @param id
	 * @return
	 */
	WorksheetDetailTemp findByDetail(Integer id);

	/**
	 * 删除信息
	 * @param id
	 */
	void deleteByWDID(Integer id);

}

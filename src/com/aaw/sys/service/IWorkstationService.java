package com.aaw.sys.service;

import java.util.List;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.MetaProcedure;
import com.aaw.bean.Workstation;

public interface IWorkstationService extends IBaseService<Workstation> {

	void saveCfg(int[] sequenceNum, int[] procedure, int id);

	/**
	 * 查询已经配置过的工序
	 * 
	 * @param id
	 *            工作台ID
	 * @return 工序列表
	 */
	List<MetaProcedure> listCfg(int id);

}

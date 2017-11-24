package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.ProductionLine;

public interface IProductionLineService extends IBaseService<ProductionLine> {

	boolean deleteAndCheck(int id);

	List<Map<String, Object>> combobox();

	void saveCfg(int[] sequenceNum, int[] procedure, int id);

	Map<String, Object> listMetaProcedure(int id);

	int checkProcCout(int id);
}
package com.aaw.sys.service;

import java.util.List;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.MetaProcedure;
import com.aaw.bean.ProductionLineConfig;

public interface IProductionLineConfigService extends
		IBaseService<ProductionLineConfig> {

	List<MetaProcedure> queryMetaPListByLineID(Integer id);
}
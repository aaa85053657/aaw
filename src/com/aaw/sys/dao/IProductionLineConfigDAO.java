package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.MetaProcedure;
import com.aaw.bean.ProductionLineConfig;

public interface IProductionLineConfigDAO extends
		IBaseDAO<ProductionLineConfig> {

	List<MetaProcedure> queryMetaPListByLineID(Integer id);
}
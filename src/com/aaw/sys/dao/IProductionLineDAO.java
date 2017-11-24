package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.MetaProcedure;
import com.aaw.bean.ProductionLine;

public interface IProductionLineDAO extends IBaseDAO<ProductionLine> {

	boolean deleteAndCheck(int id);

	List<MetaProcedure> listProfile(int id);
}
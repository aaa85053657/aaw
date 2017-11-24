package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.ProductModelConfig;

public interface IProductModelConfigDAO extends IBaseDAO<ProductModelConfig> {

	List<ProductModelConfig> cfgList(int id);

	void deleteConfig(int comId, int modelId);
}
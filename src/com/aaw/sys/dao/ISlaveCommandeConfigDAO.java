package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.ProductModelConfig;
import com.aaw.bean.SlaveCommandeConfig;
import com.aaw.bean.SlaveCommandeFreeConfig;

public interface ISlaveCommandeConfigDAO extends IBaseDAO<SlaveCommandeConfig> {

	List<SlaveCommandeConfig> queryBySlaveID(int id);

	List<ProductModelConfig> queryModelCfgByModel(int id, int cid);

	List<SlaveCommandeFreeConfig> queryBySlaveID2(int id);

}
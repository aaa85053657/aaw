package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.SlaveCommandeFreeConfig;

public interface ISlaveCommandeFreeConfigDAO extends IBaseDAO<SlaveCommandeFreeConfig> {

	List<SlaveCommandeFreeConfig> queryListByCommandeId(Integer id);



}
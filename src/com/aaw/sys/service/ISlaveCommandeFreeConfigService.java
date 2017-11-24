package com.aaw.sys.service;

import java.util.List;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.SlaveCommandeFreeConfig;

public interface ISlaveCommandeFreeConfigService extends
		IBaseService<SlaveCommandeFreeConfig> {

	List<SlaveCommandeFreeConfig> queryListByCommandeId(Integer id);

}
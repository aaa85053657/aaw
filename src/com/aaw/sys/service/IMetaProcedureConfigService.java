package com.aaw.sys.service;

import java.util.List;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.MetaProcedureConfig;
import com.aaw.bean.Profile;

public interface IMetaProcedureConfigService extends
		IBaseService<MetaProcedureConfig> {

	List<Profile> listProfileByMetaID(Integer id);

}
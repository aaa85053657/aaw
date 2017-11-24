package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.MetaProcedureConfig;
import com.aaw.bean.Profile;

public interface IMetaProcedureConfigDAO extends IBaseDAO<MetaProcedureConfig> {

	List<Profile> listProfileByMetaID(Integer id);

}
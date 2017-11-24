package com.aaw.sys.dao;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.RoleAssetsConfig;

public interface IRoleAssetsConfigDAO extends IBaseDAO<RoleAssetsConfig> {

	void unbind(int id);

	void bind(int id, Integer[] rids);
}
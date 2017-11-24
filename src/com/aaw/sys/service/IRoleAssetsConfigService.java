package com.aaw.sys.service;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.RoleAssetsConfig;

public interface IRoleAssetsConfigService extends
		IBaseService<RoleAssetsConfig> {

	/**
	 * @description 删除已经绑定记录
	 * @param id
	 *            角色ID
	 */
	void unbind(int id);

	/**
	 * @description 绑定角色资源信息
	 * @param id
	 *            角色ID
	 * @param rids
	 *            资源ID
	 */
	void bind(int id, Integer[] rids);
}
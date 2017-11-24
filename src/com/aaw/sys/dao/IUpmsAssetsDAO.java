package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.UpmsAssets;

public interface IUpmsAssetsDAO extends IBaseDAO<UpmsAssets> {

	/**
	 * 根据账号ID获取所有资源信息
	 * 
	 * @param id
	 *            账号ID
	 * @return
	 */
	List<UpmsAssets> listByAccountID(Integer id);

	List<Integer> listByRoleID(Integer rid);

	List<UpmsAssets> listByRID(Integer id);

	List<Integer> findBindIDs(int roleID);

	/**
	 * 获取加盟商权限
	 * @return
	 */
	List<UpmsAssets> listByFran();
}
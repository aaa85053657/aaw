package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.UpmsAssets;

public interface IUpmsAssetsService extends IBaseService<UpmsAssets> {

	/**
	 * 根据登录用户（或者开发模式）获取资源菜单内容
	 * 
	 * @param se
	 * @return
	 */
	List<Map<String, Object>> getMenus(HttpServletRequest se);

	List<Map<String, Object>> assetsList(List<UpmsAssets> list,
			HttpServletRequest request);

	List<Map<String, Object>> assetsList2(List<UpmsAssets> list,
			List<Integer> list2, HttpServletRequest request);

	void init();

	/**
	 * @description 获取资源信息列表，并进行绑定过的资源处理为选中
	 * @param roleID
	 *            角色ID
	 * @return
	 */
	List<Map<String, Object>> list4Bind(int roleID);

	/**
	 * @param request
	 * @description 获取资源信息
	 * @return
	 */
	List<Map<String, Object>> listAll(HttpServletRequest request);

	/**
	 * @description 获取已经绑定的资源ID集合
	 * @param roleID
	 *            角色ID
	 * @return
	 */
	List<Integer> list4BindIDs(int roleID);

}
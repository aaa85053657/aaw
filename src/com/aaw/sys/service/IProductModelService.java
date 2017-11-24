package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.ProductModel;

public interface IProductModelService extends IBaseService<ProductModel> {

	boolean deleteAndCheck(int id);

	/**
	 * @param componentID
	 *            所配置的组件ID
	 * @param position
	 *            位置
	 * @param element
	 *            所有相关的候选值
	 * @param id
	 *            型号ID
	 */
	void saveCfg(int componentID, int position, int[] element, int id);

	Map<String, Object> cfgList(int id);

	/**
	 * 根据组件删除整个配置
	 * 
	 * @param id
	 *            型号ID
	 * @param cid
	 *            组件ID
	 */
	void delete(int id, int cid);

	List<Map<String, Object>> combobox(int id);

	Object createTree(int id);

	Object createTree2(int id, int cid);

	Object createTree3(int id, int cid);
}
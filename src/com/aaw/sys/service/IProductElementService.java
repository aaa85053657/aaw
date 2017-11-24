package com.aaw.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.ProductElement;

public interface IProductElementService extends IBaseService<ProductElement> {
	/**
	 * 检查引用，无引用删除数据
	 * 
	 * @param id
	 *            根据ID删除数据
	 * @return
	 */
	boolean deleteAndCheck(int id);

	List<Map<String, Object>> combobox();

	List<ProductElement> findByAttrId(int cid);

	void updatePath(Integer id, String path);

	/**
	 * 根据实例图片查找
	 * 
	 * @param string
	 * @return
	 */
	List<ProductElement> findByPath(String string);

	/**
	 * 获取实例图片为空的属性
	 * 
	 * @return
	 */
	List<ProductElement> getPicNull();

	ArrayList<List<Object>> getList(List<ProductElement> temp);

}
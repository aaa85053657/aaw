package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.ProductAttribute;

public interface IProductAttributeService extends
		IBaseService<ProductAttribute> {

	/**
	 * 删除并做关联性检查
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteAndCheck(int id);

	/**
	 * 提供combobox的下拉选择
	 * 
	 * @return
	 */
	List<Map<String, Object>> combobox();

	Map<String, Object> listElement(int id);

	/**
	 * 根据组件获取属性
	 * @param id    组件id
	 * @return
	 */
	List<ProductAttribute> findByComponentId(Integer id);
}
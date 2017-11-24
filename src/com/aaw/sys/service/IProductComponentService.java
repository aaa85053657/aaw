package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.ProductComponent;

public interface IProductComponentService extends
		IBaseService<ProductComponent> {

	/**
	 * 检查引用，无引用删除数据
	 * 
	 * @param id
	 *            根据ID删除数据
	 * @return
	 */
	boolean deleteAndCheck(int id);

	List<Map<String, Object>> combobox(int id);
}
package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.ProductComponent;

public interface IProductComponentDAO extends IBaseDAO<ProductComponent> {
	boolean deleteAndCheck(int id);

	List<ProductComponent> combobox(int id);
}
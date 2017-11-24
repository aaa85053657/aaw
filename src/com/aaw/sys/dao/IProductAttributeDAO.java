package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.ProductAttribute;
import com.aaw.bean.ProductElement;

public interface IProductAttributeDAO extends IBaseDAO<ProductAttribute> {

	boolean deleteAndCheck(int id);

	List<ProductElement> listElement(int id);

	List<ProductAttribute> findByComponentId(Integer id);
}
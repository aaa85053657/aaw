package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.ProductElement;

public interface IProductElementDAO extends IBaseDAO<ProductElement> {

	boolean deleteAndCheck(int id);

	List<ProductElement> findByAttrId(int cid);

	void updatePath(Integer id, String path);

	List<ProductElement> findByPath(String path);

	List<ProductElement> getPicNull();
}
package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.ProductModel;
import com.aaw.bean.ProductModelConfig;
import com.aaw.sys.form.ModelCfgDetail;

public interface IProductModelDAO extends IBaseDAO<ProductModel> {

	boolean deleteAndCheck(int id);

	List<ModelCfgDetail> cfgList(int id);

	void delete(int id, int cid);

	List<ProductModelConfig> findByModel(int id);

	List<ProductModel> findByProduct();
}
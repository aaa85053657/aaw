package com.aaw.sys.service;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.ProductionException;

public interface IProductionExceptionService extends
		IBaseService<ProductionException> {

	boolean deleteAndCheck(int id);

}

package com.aaw.sys.service;

import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.ProductModelConfig;

public interface IProductModelConfigService extends
		IBaseService<ProductModelConfig> {

	Map<String, Object> combobox(int id);

	Map<String, Object> combobox2(int id);

	void deleteConfig(int comId, int modelId);
}
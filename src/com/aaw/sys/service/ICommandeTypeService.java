package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.CommandeType;

public interface ICommandeTypeService extends IBaseService<CommandeType>{

	boolean deleteAndCheck(int id);

	List<Map<String, Object>> combobox();

	List<Map<String, Object>> combobox2(int type);
}
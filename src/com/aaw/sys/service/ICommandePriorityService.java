package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.CommandePriority;

public interface ICommandePriorityService extends IBaseService<CommandePriority>{

	boolean deleteAndCheck(int id);

	List<Map<String, Object>> combobox();
}
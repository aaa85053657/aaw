package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.ProfileType;

public interface IProfileTypeService extends IBaseService<ProfileType>{

	List<Map<String, Object>> combobox();

	boolean deleteAndCheck(int id);
}
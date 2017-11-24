package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.Profile;

public interface IProfileService extends IBaseService<Profile> {

	boolean deleteAndCheck(int id);

	List<Map<String, Object>> combobox(int id);

	List<Profile> listByMetaID(Integer id);
}
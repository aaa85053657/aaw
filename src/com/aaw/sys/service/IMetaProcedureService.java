package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.MetaProcedure;

public interface IMetaProcedureService extends IBaseService<MetaProcedure> {

	boolean deleteAndCheck(int id);

	Map<String, Object> listProfile(int id);

	void saveCfg(int[] priority, int[] profile, int id);

	List<Map<String, Object>> combobox(int id);

	List<MetaProcedure> listByLineID(Integer id);

	List<MetaProcedure> all(List<Integer> integers);
}
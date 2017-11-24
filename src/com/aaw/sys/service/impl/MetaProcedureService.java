package com.aaw.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import molos.plugins.smvc.dao.IDAO;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.MetaProcedure;
import com.aaw.bean.MetaProcedureConfig;
import com.aaw.bean.Profile;
import com.aaw.sys.dao.IMetaProcedureDAO;
import com.aaw.sys.service.IMetaProcedureConfigService;
import com.aaw.sys.service.IMetaProcedureService;
import com.aaw.sys.service.IProductionLineConfigService;

@Service
public class MetaProcedureService extends BaseService<MetaProcedure> implements
		IMetaProcedureService {

	@Override
	protected IMetaProcedureDAO getDAO() {
		return (IMetaProcedureDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("metaProcedureDAO") IDAO<MetaProcedure> dao) {
		super.setDAO(dao);
	}

	@Override
	public boolean deleteAndCheck(int id) {
		return getDAO().deleteAndCheck(id);
	}

	@Override
	public Map<String, Object> listProfile(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Profile> list = getDAO().listProfile(id);
		map.put("rows", list);
		map.put("total", npv.isNull(list) ? 0 : list.size());
		return map;
	}

	@Override
	public void saveCfg(int[] priority, int[] profile, int id) {
		for (int i = 0; i < profile.length; i++) {
			metaProcedureConfigService.save(new MetaProcedureConfig(profile[i],
					id, priority[i]));
		}
	}

	@Override
	public List<Map<String, Object>> combobox(int id) {

		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<MetaProcedure> list = new ArrayList<MetaProcedure>();
		if (id < 1) {
			list = list();
		} else {
			list = getDAO().combobox(id);
		}
		for (MetaProcedure a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getName());
			reList.add(map);
		}
		return reList;
	}

	@Override
	public List<MetaProcedure> all(List<Integer> integers) {
		return getDAO().all(integers);
	}

	@Override
	public List<MetaProcedure> listByLineID(Integer id) {
		return productionLineConfigService.queryMetaPListByLineID(id);
	}

	@Resource
	private IMetaProcedureConfigService metaProcedureConfigService;
	@Resource
	private IProductionLineConfigService productionLineConfigService;

}
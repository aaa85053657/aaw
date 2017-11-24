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
import com.aaw.bean.ProductionLine;
import com.aaw.bean.ProductionLineConfig;
import com.aaw.sys.dao.IProductionLineDAO;
import com.aaw.sys.service.IMetaProcedureService;
import com.aaw.sys.service.IProductionLineConfigService;
import com.aaw.sys.service.IProductionLineService;

@Service
public class ProductionLineService extends BaseService<ProductionLine>
		implements IProductionLineService {

	@Override
	protected IProductionLineDAO getDAO() {
		return (IProductionLineDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("productionLineDAO") IDAO<ProductionLine> dao) {
		super.setDAO(dao);
	}

	@Override
	public boolean deleteAndCheck(int id) {
		return getDAO().deleteAndCheck(id);
	}

	@Override
	public List<Map<String, Object>> combobox() {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<ProductionLine> list = list();
		for (ProductionLine a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getName());
			reList.add(map);
		}
		return reList;

	}

	@Override
	public void saveCfg(int[] sequenceNum, int[] procedure, int id) {
		for (int i = 0; i < procedure.length; i++) {
			productionLineConfigService.save(new ProductionLineConfig(
					procedure[i], id, sequenceNum[i]));
		}
	}

	@Resource
	private IProductionLineConfigService productionLineConfigService;

	@Override
	public Map<String, Object> listMetaProcedure(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<MetaProcedure> list = getDAO().listProfile(id);
		map.put("rows", list);
		map.put("total", npv.isNull(list) ? 0 : list.size());
		return map;
	}

	@Override
	public int checkProcCout(int id) {
		List<MetaProcedure> metaList = metaProcedureService.listByLineID(id);
		return npv.isNull(metaList) ? 0 : metaList.size();
	}

	@Resource
	private IMetaProcedureService metaProcedureService;
}
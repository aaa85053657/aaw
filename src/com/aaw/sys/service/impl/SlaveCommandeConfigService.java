package com.aaw.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import molos.plugins.smvc.dao.IDAO;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.ProductModelConfig;
import com.aaw.bean.SlaveCommandeConfig;
import com.aaw.bean.SlaveCommandeFreeConfig;
import com.aaw.sys.dao.ISlaveCommandeConfigDAO;
import com.aaw.sys.form.SlaveCfgDetail;
import com.aaw.sys.service.ISlaveCommandeConfigService;

@Service
public class SlaveCommandeConfigService extends
		BaseService<SlaveCommandeConfig> implements ISlaveCommandeConfigService {

	@Override
	protected ISlaveCommandeConfigDAO getDAO() {
		return (ISlaveCommandeConfigDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("slaveCommandeConfigDAO") IDAO<SlaveCommandeConfig> dao) {
		super.setDAO(dao);
	}

	@Override
	public Map<String, Object> slaveDetail(int id) {
		List<SlaveCommandeConfig> list = getDAO().queryBySlaveID(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", npv.isNull(list) ? 0 : list.size());
		return map;
	}

	@Override
	public Map<String, Object> slaveDetail2(int id) {
		List<SlaveCommandeFreeConfig> list = getDAO().queryBySlaveID2(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", npv.isNull(list) ? 0 : list.size());
		return map;
	}

	@Override
	public Map<String, Object> modelElems(int id, int cid, int aid) {
		List<ProductModelConfig> list = getDAO().queryModelCfgByModel(id, cid);

		Map<String, Object> m = new HashMap<String, Object>();
		List<Map<String, Object>> eL = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> eR = new ArrayList<Map<String, Object>>();
		for (ProductModelConfig p : list) {
			if (p.getElement().getAttribute().getId() == aid) {
				if (p.getPosition() == 1 || p.getPosition() == 3) {
					Map<String, Object> m1 = new HashMap<String, Object>();
					m1.put("id", p.getElement().getId());
					m1.put("text", p.getElement().getName());
					eL.add(m1);
				}
				if (p.getPosition() == 2 || p.getPosition() == 3) {
					Map<String, Object> m2 = new HashMap<String, Object>();
					m2.put("id", p.getElement().getId());
					m2.put("text", p.getElement().getName());
					eR.add(m2);
				}
			}
		}
		m.put("left", eL);
		m.put("right", eR);
		return m;
	}

	@Override
	public List<SlaveCfgDetail> queryListByCommandeId(int id) {
		List<SlaveCommandeConfig> configs = getDAO().queryBySlaveID(id);
		List<Integer> had = new ArrayList<Integer>();
		List<Integer> hadc = new ArrayList<Integer>();
		List<SlaveCfgDetail> list = new ArrayList<SlaveCfgDetail>();
		// 组件分组
		for (SlaveCommandeConfig c : configs) {
			int cid = c.getComponent().getId();
			if (!hadc.contains(cid)) {
				hadc.add(cid);
			}
		}
		// 获取组件相关
		for (int cc : hadc) {
			for (SlaveCommandeConfig c : configs) {
				int cid = c.getComponent().getId();
				int aid = 0;
				if (c.getElementLeft() != null
						&& c.getElementLeft().getAttribute() != null) {
					aid = c.getElementLeft().getAttribute().getId();
				} else if (c.getElementRight() != null
						&& c.getElementRight().getAttribute() != null) {
					aid = c.getElementRight().getAttribute().getId();
				}
				if (!had.contains(aid) && c.getComponent().getId() == cc) {
					had.add(aid);
					SlaveCfgDetail s = new SlaveCfgDetail();
					s.setComponentID(cid);
					s.setComponentName(c.getComponent().getName());
					if (c.getElementLeft() != null
							&& c.getElementLeft().getAttribute() != null) {
						s.setAttrName(c.getElementLeft().getAttribute()
								.getName());
						s.setEid(c.getElementLeft().getId());
					} else if (c.getElementRight() != null
							&& c.getElementRight().getAttribute() != null) {
						s.setAttrName(c.getElementRight().getAttribute()
								.getName());
						s.setEid(c.getElementRight().getId());
					}
					for (SlaveCommandeConfig c1 : configs) {
						if (c1.getElementLeft() != null
								&& c1.getComponent() != null
								&& c1.getElementLeft().getAttribute() != null
								&& c1.getElementLeft().getAttribute().getId() == aid
								&& c1.getComponent().getId() == cid) {
							s.setElementLeft(c1.getElementLeft().getName());
						}
					}
					for (SlaveCommandeConfig c2 : configs) {
						if (c2.getElementRight() != null
								&& c2.getComponent() != null
								&& c2.getElementRight().getAttribute().getId() == aid
								&& c2.getComponent().getId() == cid) {
							s.setElementRight(c2.getElementRight().getName());
						}
					}
					list.add(s);
				}

			}
			had.clear();
		}

		// map.put("rows", list);
		// map.put("total", npv.isNull(list) ? 0 : list.size());
		// return map;

		return list;
	}

	@Override
	public List<SlaveCommandeConfig> findBySC(int id) {
		return getDAO().queryBySlaveID(id);
	}

}
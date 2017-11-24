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
import com.aaw.sys.dao.IProductModelConfigDAO;
import com.aaw.sys.form.SlaveCfgChoice;
import com.aaw.sys.service.IProductModelConfigService;

@Service
public class ProductModelConfigService extends BaseService<ProductModelConfig>
		implements IProductModelConfigService {

	@Override
	protected IProductModelConfigDAO getDAO() {
		return (IProductModelConfigDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("productModelConfigDAO") IDAO<ProductModelConfig> dao) {
		super.setDAO(dao);
	}

	@Override
	public Map<String, Object> combobox(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ProductModelConfig> datas = getDAO().cfgList(id);
		List<Integer> had = new ArrayList<Integer>();
		List<Integer> hadc = new ArrayList<Integer>();
		List<SlaveCfgChoice> list = new ArrayList<SlaveCfgChoice>();
		// 组件分组
		for (ProductModelConfig c : datas) {
			int cid = c.getComponent().getId();
			if (!hadc.contains(cid)) {
				hadc.add(cid);
			}
		}
		// 获取组件相关
		for (int cc : hadc) {
			for (ProductModelConfig c : datas) {
				if (c.getElement().getAttribute().getAttributeType() == 1) {
					int cid = c.getComponent().getId();
					int aid = c.getElement().getAttribute().getId();
					if (!had.contains(aid) && c.getComponent().getId() == cc) {
						had.add(aid);
						SlaveCfgChoice s = new SlaveCfgChoice();
						s.setComponentID(cid);
						s.setComponentName(c.getComponent().getName());
						s.setAttrName(c.getElement().getAttribute().getName());
						List<Map<String, Object>> elementLeft = new ArrayList<Map<String, Object>>();
						for (ProductModelConfig c1 : datas) {
							if (c1.getElement().getAttribute().getId() == aid
									&& c1.getComponent().getId() == cid
									&& (c1.getPosition() == 1 || c1
											.getPosition() == 3)) {
								Map<String, Object> m1 = new HashMap<String, Object>();
								m1.put("id", c1.getElement().getId());
								m1.put("text", c1.getElement().getName());
								elementLeft.add(m1);
							}
						}
						s.setElementLeft(elementLeft);
						List<Map<String, Object>> elementRight = new ArrayList<Map<String, Object>>();
						for (ProductModelConfig c2 : datas) {
							if (c2.getElement().getAttribute().getId() == aid
									&& c2.getComponent().getId() == cid
									&& (c2.getPosition() == 2 || c2
											.getPosition() == 3)) {
								Map<String, Object> m2 = new HashMap<String, Object>();
								m2.put("id", c2.getElement().getId());
								m2.put("text", c2.getElement().getName());
								elementRight.add(m2);
							}
						}
						s.setElementRight(elementRight);
						list.add(s);
					}
				}

			}
			had.clear();
		}

		map.put("rows", list);
		map.put("total", npv.isNull(list) ? 0 : list.size());
		return map;
	}

	@Override
	public Map<String, Object> combobox2(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ProductModelConfig> datas = getDAO().cfgList(id);
		List<Integer> had = new ArrayList<Integer>();
		List<Integer> hadc = new ArrayList<Integer>();
		List<SlaveCfgChoice> list = new ArrayList<SlaveCfgChoice>();
		// 组件分组
		for (ProductModelConfig c : datas) {
			int cid = c.getComponent().getId();
			if (!hadc.contains(cid)) {
				hadc.add(cid);
			}
		}
		// 获取组件相关
		for (int cc : hadc) {
			for (ProductModelConfig c : datas) {
				if (c.getElement().getAttribute().getAttributeType() == 2) {
					int cid = c.getComponent().getId();
					int aid = c.getElement().getAttribute().getId();
					if (!had.contains(aid) && c.getComponent().getId() == cc) {
						had.add(aid);
						SlaveCfgChoice s = new SlaveCfgChoice();
						s.setComponentID(cid);
						s.setComponentName(c.getComponent().getName());
						s.setAttrName(c.getElement().getAttribute().getName());
						s.setAttrNameId(c.getElement().getAttribute().getId());
						List<Map<String, Object>> elementLeft = new ArrayList<Map<String, Object>>();
						for (ProductModelConfig c1 : datas) {
							if (c1.getElement().getAttribute().getId() == aid
									&& c1.getComponent().getId() == cid
									&& (c1.getPosition() == 1 || c1
											.getPosition() == 3)) {
								Map<String, Object> m1 = new HashMap<String, Object>();
								m1.put("id", c1.getElement().getId());
								m1.put("text", c1.getElement().getName());
								elementLeft.add(m1);
							}
						}
						s.setElementLeft(elementLeft);
						List<Map<String, Object>> elementRight = new ArrayList<Map<String, Object>>();
						for (ProductModelConfig c2 : datas) {
							if (c2.getElement().getAttribute().getId() == aid
									&& c2.getComponent().getId() == cid
									&& (c2.getPosition() == 2 || c2
											.getPosition() == 3)) {
								Map<String, Object> m2 = new HashMap<String, Object>();
								m2.put("id", c2.getElement().getId());
								m2.put("text", c2.getElement().getName());
								elementRight.add(m2);
							}
						}
						s.setElementRight(elementRight);
						list.add(s);
					}
				}
			}
			had.clear();
		}

		map.put("rows", list);
		map.put("total", npv.isNull(list) ? 0 : list.size());
		return map;
	}

	@Override
	public void deleteConfig(int comId, int modelId) {
		getDAO().deleteConfig(comId, modelId);
	}
}
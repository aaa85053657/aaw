package com.aaw.sys.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import vo.ComponentTree;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.ProductAttribute;
import com.aaw.bean.ProductComponent;
import com.aaw.bean.ProductElement;
import com.aaw.bean.ProductModel;
import com.aaw.bean.ProductModelConfig;
import com.aaw.sys.dao.IProductModelDAO;
import com.aaw.sys.form.ModelCfgDetail;
import com.aaw.sys.service.IProductAttributeService;
import com.aaw.sys.service.IProductComponentService;
import com.aaw.sys.service.IProductElementService;
import com.aaw.sys.service.IProductModelConfigService;
import com.aaw.sys.service.IProductModelService;

@Service
public class ProductModelService extends BaseService<ProductModel> implements
		IProductModelService {

	@Override
	protected IProductModelDAO getDAO() {
		return (IProductModelDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("productModelDAO") IDAO<ProductModel> dao) {
		super.setDAO(dao);
	}

	@Override
	public boolean deleteAndCheck(int id) {
		return getDAO().deleteAndCheck(id);
	}

	@Override
	public void saveCfg(int componentID, int position, int[] element, int id) {
		for (int i : element) {
			configService.save(new ProductModelConfig(componentID, position, i,
					id));
		}
	}

	@Resource
	private IProductModelConfigService configService;

	@Override
	public Map<String, Object> cfgList(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ModelCfgDetail> list = getDAO().cfgList(id);
		map.put("rows", list);
		map.put("total", npv.isNull(list) ? 0 : list.size());
		return map;
	}

	@Override
	public void delete(int id, int cid) {
		getDAO().delete(id, cid);
	}

	@Override
	public List<Map<String, Object>> combobox(int id) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<ProductModel> list = getDAO().findByProduct();
		for (ProductModel a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getName());
			reList.add(map);
		}
		return reList;
	}

	@Override
	public Object createTree(int id) {
		List<ProductComponent> components = componentService.list();
		List<ComponentTree> list = new ArrayList<ComponentTree>();
		for (ProductComponent component : components) {
			ComponentTree ct = new ComponentTree();
			ct.setId((-component.getId()));
			ct.setText(component.getName());
			List<ProductAttribute> attributes = attributeService
					.findByComponentId(component.getId());
			if (attributes != null && !attributes.isEmpty()) {
				ct.setChildren(createAttr(attributes));
			}
			list.add(ct);
		}
		return list;
	}

	@Override
	public Object createTree2(int id, int cid) {
		List<ProductElement> elements = elementService.findByAttrId(cid);
		List<ProductModelConfig> pmcs = getDAO().findByModel(id);
		List<ComponentTree> elementTree = new ArrayList<ComponentTree>();
		for (ProductElement element : elements) {
			ComponentTree ct = new ComponentTree();
			ct.setId(element.getId());
			String samplepath = element.getSamplepath();
			String src = "";
			if (samplepath != null && !samplepath.equals("")
					&& new File(samplepath).exists()) {
				src = "productElement/showById?id=" + element.getId();
			}
			String name = element.getName()
					+ " <img style='width:30px;height:15px' alt=' ' class='image' id='"
					+ element.getId() + "' src='" + src
					+ "' onmousemove='showPic(" + element.getId() + ")'>";
			ct.setText(name);
			if (pmcs != null) {
				for (ProductModelConfig pmc : pmcs) {
					if (pmc.getElement().getId() == element.getId()) {
						ct.setChecked(true);
					}
				}
			}
			elementTree.add(ct);
		}
		return elementTree;
	}

	@Override
	public Object createTree3(int id, int cid) {
		List<ProductElement> elements = elementService.findByAttrId(cid);
		List<ProductModelConfig> pmcs = getDAO().findByModel(id);
		List<ProductModelConfig> temp = new ArrayList<ProductModelConfig>();
		for (ProductElement element : elements) {
			if (pmcs != null) {
				for (ProductModelConfig pmc : pmcs) {
					if (pmc.getElement().getId() == element.getId()) {
						temp.add(pmc);
					}
				}
			}
		}
		List<ComponentTree> elementTree = new ArrayList<ComponentTree>();
		elementTree.addAll(createPosition(temp));
		return elementTree;
	}

	private List<ComponentTree> createAttr(List<ProductAttribute> attributes) {
		List<ComponentTree> attrChild = new ArrayList<ComponentTree>();
		for (ProductAttribute attr : attributes) {
			ComponentTree ct = new ComponentTree();
			ct.setId(attr.getId());
			ct.setText(attr.getName());
			attrChild.add(ct);
		}
		return attrChild;
	}

	private List<ComponentTree> createPosition(List<ProductModelConfig> pmcs) {
		List<ComponentTree> positionChild = new ArrayList<ComponentTree>();
		ComponentTree one = new ComponentTree();
		one.setId(1);
		one.setText("左");
		ComponentTree tow = new ComponentTree();
		tow.setId(2);
		tow.setText("右");
		ComponentTree three = new ComponentTree();
		three.setId(3);
		three.setText("左右");
		if (pmcs != null && pmcs.size() > 0 && pmcs.get(0).getPosition() == 1) {
			one.setChecked(true);
		} else if (pmcs != null && pmcs.size() > 0
				&& pmcs.get(0).getPosition() == 2) {
			tow.setChecked(true);
		} else {
			three.setChecked(true);
		}
		positionChild.add(one);
		positionChild.add(tow);
		positionChild.add(three);
		return positionChild;
	}

	@Resource
	private IProductComponentService componentService;
	@Resource
	private IProductAttributeService attributeService;
	@Resource
	private IProductElementService elementService;

}
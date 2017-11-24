package com.aaw.sys.ctl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import vo.ComponentTree;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.ProductAttribute;
import com.aaw.bean.ProductElement;
import com.aaw.bean.ProductModel;
import com.aaw.bean.ProductModelConfig;
import com.aaw.sys.service.IProductAttributeService;
import com.aaw.sys.service.IProductComponentService;
import com.aaw.sys.service.IProductModelConfigService;
import com.aaw.sys.service.IProductModelService;

@Controller
@RequestMapping("productModel")
public class ProductModelCtl extends BaseCtl<ProductModel> {

	@Resource
	private IProductModelService service;

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/model/model");
	}

	@RequestMapping(params = "goto=viewCfg")
	public ModelAndView viewCfgDetail(@RequestParam(defaultValue = "0") int id) {
		return new ModelAndView("sys/model/cfgDetail");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("ProductModelCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(ProductModel bean, HttpServletRequest request) {
		try {
			bean.setTimeCreation(new Date());
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("ProductModelCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(ProductModel bean, HttpServletRequest request) {
		try {
			bean.setTimeModification(new Date());
			service.update(bean);
			return updateSuc(request);
		} catch (Exception e) {
			log.error("ProductModelCtl修改对象异常", e);
		}
		return updateFail(request);
	}

	@RequestMapping(params = "goto=del")
	@ResponseBody
	public Object delete(@RequestParam(defaultValue = "0") int id,
			HttpServletRequest request) {
		try {
			if (service.deleteAndCheck(id)) {
				return delSuc(request);
			} else {
				return delRef(request);
			}
		} catch (Exception e) {
			log.error("ProductModelCtl通过ID删除对象异常", e);
		}
		return delFail(request);
	}

	@RequestMapping(params = "goto=unique")
	@ResponseBody
	public Object unique(String propName, String propVal,
			@RequestParam(defaultValue = "0") int id) {
		try {
			return unique(service, propName, propVal, id);
		} catch (Exception e) {
			log.error("ProductModelCtl验证是否唯一异常", e);
		}
		return 2;
	}

	@RequestMapping(params = "goto=saveCfg")
	@ResponseBody
	public Object saveCfg(@RequestParam(defaultValue = "0") int componentID,
			@RequestParam(value = "element[]") int[] element, int position,
			@RequestParam(defaultValue = "0") int id, HttpServletRequest request) {
		try {
			if (id > 0) {
				service.saveCfg(componentID, position, element, id);
				return suc(new RequestContext(request)
						.getMessage("model.cfg.saveSuc"));
			}
		} catch (Exception e) {
			log.error("ProductModelCtl保存配置异常", e);
		}
		return err(new RequestContext(request).getMessage("model.cfg.saveFail"));
	}

	@RequestMapping(params = "goto=cfgList")
	@ResponseBody
	public Object cfgList(@RequestParam(defaultValue = "0") int id) {
		try {
			return service.cfgList(id);
		} catch (Exception e) {
			log.error("ProductionLineCtl查询相关profile列表信息异常", e);
		}
		return new HashMap<String, Object>();
	}

	@RequestMapping(params = "goto=delCfg")
	@ResponseBody
	public Object delCfg(@RequestParam(defaultValue = "0") int id,
			HttpServletRequest request) {
		try {
			if (id > 0) {
				configService.delete(id);
				return delSuc(request);
			}
		} catch (Exception e) {
			log.error("ProductionLineCtl通过config对象ID删除关联关系异常", e);
		}
		return delFail(request);
	}

	@RequestMapping(params = "goto=delCfg2")
	@ResponseBody
	public Object delCfgByComponet(@RequestParam(defaultValue = "0") int id,
			@RequestParam(defaultValue = "0") int cid,
			HttpServletRequest request) {
		try {
			if (id > 0) {
				service.delete(id, cid);
				return delSuc(request);
			}
		} catch (Exception e) {
			log.error("ProductionLineCtl通过config对象ID删除关联关系异常", e);
		}
		return delFail(request);
	}

	@RequestMapping(params = "goto=combobox")
	@ResponseBody
	public Object combobox(@RequestParam(defaultValue = "0") int id) {
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		try {
			map = service.combobox(id);
		} catch (Exception e) {
			log.error("ProductionLineCtl查询候选列表异常", e);
		}
		return map;
	}

	/**
	 * 获取组件 属性
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "goto=componentTree")
	@ResponseBody
	public Object componentTree(@RequestParam(defaultValue = "0") int id) {
		return service.createTree(id);
	}

	/**
	 * 获取 属性值
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "goto=componentTree2")
	@ResponseBody
	public Object componentTree2(@RequestParam(defaultValue = "0") int id,
			@RequestParam(defaultValue = "0") int cid) {
		return service.createTree2(id, cid);
	}

	/**
	 * 获取 左右
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "goto=componentTree3")
	@ResponseBody
	public Object componentTree3(@RequestParam(defaultValue = "0") int id,
			@RequestParam(defaultValue = "0") int cid) {
		return service.createTree3(id, cid);
	}

	/**
	 * 获取 左右
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "goto=componentTree4")
	@ResponseBody
	public Object componentTree4() {
		List<ComponentTree> elementTree = new ArrayList<ComponentTree>();
		return elementTree;
	}

	/**
	 * 获取 属性值
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "goto=saveElement")
	@ResponseBody
	public void saveElement(@RequestParam(defaultValue = "0") int comId,
			@RequestParam(defaultValue = "0") int modelId,
			@RequestParam(defaultValue = "0") int pid, @RequestParam String eid) {
		configService.deleteConfig(comId, modelId);
		if (eid != null && !eid.equals("")) {
			String[] eids = eid.split(",");
			ProductAttribute pa = attributeService.query(comId);
			if (eids.length > 0) {
				for (int i = 0; i < eids.length; i++) {
					ProductModelConfig pmc = new ProductModelConfig();
					ProductElement element = new ProductElement();
					element.setId(Integer.valueOf(eids[i]));
					pmc.setElement(element);
					pmc.setPosition(pid);
					pmc.setComponent(pa.getComponent());
					ProductModel pm = new ProductModel();
					pm.setId(modelId);
					pmc.setModel(pm);
					configService.save(pmc);
				}
			}
		}

	}

	@Resource
	private IProductModelConfigService configService;
	@Resource
	private IProductComponentService componentService;
	@Resource
	private IProductAttributeService attributeService;
}
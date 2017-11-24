package com.aaw.sys.ctl;

import java.util.ArrayList;
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

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.ProductionLine;
import com.aaw.sys.service.IProductionLineConfigService;
import com.aaw.sys.service.IProductionLineService;

@Controller
@RequestMapping("productionLine")
public class ProductionLineCtl extends BaseCtl<ProductionLine> {

	@Resource
	private IProductionLineService service;

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/productionLine/line");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("ProductionLineCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(ProductionLine bean, HttpServletRequest request) {
		try {
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("ProductionLineCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(ProductionLine bean, HttpServletRequest request) {
		try {
			service.update(bean);
			return updateSuc(request);
		} catch (Exception e) {
			log.error("ProductionLineCtl修改对象异常", e);
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
			log.error("ProductionLineCtl通过ID删除对象异常", e);
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
			log.error("ProductionLineCtl验证是否唯一异常", e);
		}
		return 2;
	}

	@RequestMapping(params = "goto=cfgList")
	@ResponseBody
	public Object listMetaProcedure(@RequestParam(defaultValue = "0") int id) {
		try {
			return service.listMetaProcedure(id);
		} catch (Exception e) {
			log.error("ProductionLineCtl查询相关profile列表信息异常", e);
		}
		return new HashMap<String, Object>();
	}

	@RequestMapping(params = "goto=saveCfg")
	@ResponseBody
	public Object saveCfg(
			@RequestParam(value = "sequenceNum[]") int[] sequenceNum,
			@RequestParam(value = "procedure[]") int[] procedure,
			@RequestParam(defaultValue = "0") int id, HttpServletRequest request) {
		try {
			if (id > 0) {
				service.saveCfg(sequenceNum, procedure, id);
				return suc(new RequestContext(request)
						.getMessage("pline.cfg.saveSuc"));
			}
		} catch (Exception e) {
			log.error("ProductionLineCtl保存配置异常", e);
		}
		return err(new RequestContext(request).getMessage("pline.cfg.saveFail"));
	}

	@RequestMapping(params = "goto=delCfg")
	@ResponseBody
	public Object delCfg(@RequestParam(defaultValue = "0") int id,
			HttpServletRequest request) {
		try {
			if (id > 0) {
				productionLineConfigService.delete(id);
				return delSuc(request);
			}
		} catch (Exception e) {
			log.error("ProductionLineCtl通过config对象ID删除关联关系异常", e);
		}
		return delFail(request);
	}

	@RequestMapping(params = "goto=checkProcCout")
	@ResponseBody
	public Object checkProcCout(@RequestParam(defaultValue = "0") int id) {
		try {
			int c = service.checkProcCout(id);
			return c;
		} catch (Exception e) {
			log.error("ProductionLineCtl查询总共需要的工序数异常", e);
		}
		return 0;
	}

	@RequestMapping(params = "goto=combobox")
	@ResponseBody
	public Object combobox() {
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		try {
			map = service.combobox();
		} catch (Exception e) {
			log.error("ProductionLineCtl查询候选列表异常", e);
		}
		return map;
	}

	@Resource
	private IProductionLineConfigService productionLineConfigService;
}
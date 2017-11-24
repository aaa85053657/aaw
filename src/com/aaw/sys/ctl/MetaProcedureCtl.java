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
import com.aaw.bean.MetaProcedure;
import com.aaw.sys.service.IEmployeeService;
import com.aaw.sys.service.IMetaProcedureConfigService;
import com.aaw.sys.service.IMetaProcedureService;

@Controller
@RequestMapping("metaProcedure")
public class MetaProcedureCtl extends BaseCtl<MetaProcedure> {

	@Resource
	private IMetaProcedureService service;

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/procedure/procedure");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("MetaProcedureCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(MetaProcedure bean, HttpServletRequest request) {
		try {
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("MetaProcedureCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(MetaProcedure bean, HttpServletRequest request) {
		try {
			service.update(bean);
			return updateSuc(request);
		} catch (Exception e) {
			log.error("MetaProcedureCtl修改对象异常", e);
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
			log.error("MetaProcedureCtl通过ID删除对象异常", e);
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
			log.error("MetaProcedureCtl验证是否唯一异常", e);
		}
		return 2;
	}

	@RequestMapping(params = "goto=profileList")
	@ResponseBody
	public Object listProfile(@RequestParam(defaultValue = "0") int id) {
		try {
			return service.listProfile(id);
		} catch (Exception e) {
			log.error("MetaProcedureCtl查询相关profile列表信息异常", e);
		}
		return new HashMap<String, Object>();
	}

	@RequestMapping(params = "goto=saveCfg")
	@ResponseBody
	public Object saveCfg(@RequestParam(value = "priority[]") int[] priority,
			@RequestParam(value = "profile[]") int[] profile,
			@RequestParam(defaultValue = "0") int id, HttpServletRequest request) {
		try {
			if (id > 0) {
				service.saveCfg(priority, profile, id);
				return suc(new RequestContext(request)
						.getMessage("procedure.cfg.saveSuc"));
			}
		} catch (Exception e) {
			log.error("MetaProcedureCtl保存配置异常", e);
		}
		return err(new RequestContext(request)
				.getMessage("procedure.cfg.saveFail"));
	}

	@RequestMapping(params = "goto=delCfg")
	@ResponseBody
	public Object delCfg(@RequestParam(defaultValue = "0") int id,
			HttpServletRequest request) {
		try {
			if (id > 0) {
				metaProcedureConfigService.delete(id);
				return delSuc(request);
			}
		} catch (Exception e) {
			log.error("MetaProcedureCtl通过config对象ID删除关联关系异常", e);
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
			log.error("MetaProcedureCtl查询候选列表异常", e);
		}
		return map;
	}

	@RequestMapping(params = "goto=cbbWK")
	@ResponseBody
	public Object combobox4WK(@RequestParam(defaultValue = "0") int id) {
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		try {
			map = service.combobox(id);
		} catch (Exception e) {
			log.error("MetaProcedureCtl查询候选列表异常", e);
		}
		return map;
	}

	@RequestMapping(params = "goto=all")
	@ResponseBody
	public Object combobox2(@RequestParam(defaultValue = "0") int id) {
		List<Integer> list = employeeService.findProceById(id);
		return service.all(list);
	}

	@RequestMapping(params = "goto=combobox3")
	@ResponseBody
	public Object combobox3() {
		return service.all(null);
	}

	@Resource
	private IMetaProcedureConfigService metaProcedureConfigService;
	@Resource
	private IEmployeeService employeeService;
}
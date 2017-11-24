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

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.MetaProcedure;
import com.aaw.bean.ProductionLineConfig;
import com.aaw.sys.service.IProductionLineConfigService;
import com.aaw.sys.service.IWorkstationService;

@Controller
@RequestMapping("productionLineConfig")
public class ProductionLineConfigCtl extends BaseCtl<ProductionLineConfig> {

	@Resource
	private IProductionLineConfigService service;

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
			log.error("ProductionLineConfigCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(ProductionLineConfig bean, HttpServletRequest request) {
		try {
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("ProductionLineConfigCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(ProductionLineConfig bean, HttpServletRequest request) {
		try {
			service.update(bean);
			return suc("修改成功");
		} catch (Exception e) {
			log.error("ProductionLineConfigCtl修改对象异常", e);
		}
		return err("修改异常");
	}

	@RequestMapping(params = "goto=del")
	@ResponseBody
	public Object delete(@RequestParam(defaultValue = "0") int id) {
		try {
			service.delete(id);
			return suc("删除成功");
		} catch (Exception e) {
			log.error("ProductionLineConfigCtl通过ID删除对象异常", e);
		}
		return err("删除异常");
	}

	@RequestMapping(params = "act=combobox")
	@ResponseBody
	public Object combobox(int id, int wkID) {
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		try {
			List<MetaProcedure> list = service.queryMetaPListByLineID(id);
			List<MetaProcedure> temp = new ArrayList<MetaProcedure>();
			if (!npv.isNull(list)) {
				List<MetaProcedure> listM = workstationService.listCfg(wkID);
				if (!npv.isNull(listM)) {
					for (MetaProcedure m : listM) {
						for (MetaProcedure m1 : list) {
							if (m.getId() == m1.getId()) {
								temp.add(m1);
								break;
							}
						}
					}
				}
				list.removeAll(temp);
				for (MetaProcedure metaProcedure : list) {
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("id", metaProcedure.getId());
					m.put("text", metaProcedure.getName());
					map.add(m);
				}
				return map;
			}
		} catch (Exception e) {
			log.error("ProductionLineCtl查询候选列表异常", e);
		}
		return map;
	}

	@Resource
	private IWorkstationService workstationService;
}
package com.aaw.sys.ctl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.ProductComponent;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.aaw.sys.service.IProductComponentService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("productComponent")
public class ProductComponentCtl extends BaseCtl<ProductComponent> {

	@Resource
	private IProductComponentService service;

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/component/component");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("ProductComponentCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(ProductComponent bean, HttpServletRequest request) {
		try {
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("ProductComponentCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(ProductComponent bean, HttpServletRequest request) {
		try {
			service.update(bean);
			return updateSuc(request);
		} catch (Exception e) {
			log.error("ProductComponentCtl修改对象异常", e);
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
			log.error("ProductComponentCtl通过ID删除对象异常", e);
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
			log.error("ProductComponentCtl查询候选列表异常", e);
		}
		return 2;
	}

	@RequestMapping(params = "goto=combobox")
	@ResponseBody
	public Object combobox(@RequestParam(defaultValue = "0") int id) {
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		try {
			map = service.combobox(id);
		} catch (Exception e) {
			log.error("ProfileCtl查询候选列表异常", e);
		}
		return map;
	}

}
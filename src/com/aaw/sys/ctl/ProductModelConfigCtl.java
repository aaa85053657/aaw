package com.aaw.sys.ctl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.ProductModelConfig;
import com.aaw.sys.service.IProductModelConfigService;

@Controller
@RequestMapping("productModelConfig")
public class ProductModelConfigCtl extends BaseCtl<ProductModelConfig> {

	@Resource
	private IProductModelConfigService service;

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("ProductModelConfigCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(ProductModelConfig bean, HttpServletRequest request) {
		try {
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("ProductModelConfigCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(ProductModelConfig bean, HttpServletRequest request) {
		try {
			service.update(bean);
			return suc("修改成功");
		} catch (Exception e) {
			log.error("ProductModelConfigCtl修改对象异常", e);
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
			log.error("ProductModelConfigCtl通过ID删除对象异常", e);
		}
		return err("删除异常");
	}

	@RequestMapping(params = "goto=combobox")
	@ResponseBody
	public Object combobox(@RequestParam(defaultValue = "0") int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = service.combobox(id);
		} catch (Exception e) {
			log.error("ProductModelConfigCtl查询候选列表异常", e);
		}
		return map;
	}

	@RequestMapping(params = "goto=combobox2")
	@ResponseBody
	public Object combobox2(@RequestParam(defaultValue = "0") int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = service.combobox2(id);
		} catch (Exception e) {
			log.error("ProductModelConfigCtl查询候选列表异常", e);
		}
		return map;
	}

}
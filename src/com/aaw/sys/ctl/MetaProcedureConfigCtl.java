package com.aaw.sys.ctl;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.MetaProcedureConfig;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.aaw.sys.service.IMetaProcedureConfigService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("metaProcedureConfig")
public class MetaProcedureConfigCtl extends BaseCtl<MetaProcedureConfig> {

	@Resource
	private IMetaProcedureConfigService service;

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
			log.error("MetaProcedureConfigCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(MetaProcedureConfig bean, HttpServletRequest request) {
		try {
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("MetaProcedureConfigCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(MetaProcedureConfig bean, HttpServletRequest request) {
		try {
			service.update(bean);
			return suc("修改成功");
		} catch (Exception e) {
			log.error("MetaProcedureConfigCtl修改对象异常", e);
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
			log.error("MetaProcedureConfigCtl通过ID删除对象异常", e);
		}
		return err("删除异常");
	}

}
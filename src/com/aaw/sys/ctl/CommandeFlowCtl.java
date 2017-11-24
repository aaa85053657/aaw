package com.aaw.sys.ctl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.CommandeFlow;
import com.aaw.sys.service.ICommandeFlowService;

@Controller
@RequestMapping("commandeFlow")
public class CommandeFlowCtl extends BaseCtl<CommandeFlow> {

	@Resource
	private ICommandeFlowService service;

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
			log.error("CommandeFlowCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(CommandeFlow bean, HttpServletRequest request) {
		try {
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("CommandeFlowCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(CommandeFlow bean, HttpServletRequest request) {
		try {
			service.update(bean);
			return suc("修改成功");
		} catch (Exception e) {
			log.error("CommandeFlowCtl修改对象异常", e);
		}
		return err("修改异常");
	}

	@RequestMapping(params = "goto=del")
	@ResponseBody
	public Object delete(@RequestParam(defaultValue = "0") int id,
			HttpServletRequest request) {
		try {
			service.delete(id);
			return suc("删除成功");
		} catch (Exception e) {
			log.error("CommandeFlowCtl通过ID删除对象异常", e);
		}
		return err("删除异常");
	}

	@RequestMapping(params = "goto=unique")
	@ResponseBody
	public Object unique(String propName, String propVal, int id) {
		try {
			return unique(service, propName, propVal, id);
		} catch (Exception e) {
			log.error("CommandeFlowCtl验证是否唯一异常", e);
		}
		return 2;
	}

}
package com.aaw.sys.ctl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.molos.common.SessionConstant;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.CommandeType;
import com.aaw.sys.service.ICommandeTypeService;

@Controller
@RequestMapping("commandeType")
public class CommandeTypeCtl extends BaseCtl<CommandeType> {

	@Resource
	private ICommandeTypeService service;

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/commandeType/type");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("CommandeTypeCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(CommandeType bean, HttpServletRequest request) {
		try {
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("CommandeTypeCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(CommandeType bean, HttpServletRequest request) {
		try {
			service.update(bean);
			return updateSuc(request);
		} catch (Exception e) {
			log.error("CommandeTypeCtl修改对象异常", e);
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
			log.error("CommandeTypeCtl通过ID删除对象异常", e);
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
			log.error("CommandeTypeCtl验证是否唯一异常", e);
		}
		return 2;
	}

	@RequestMapping(params = "goto=combobox")
	@ResponseBody
	public Object combobox() {
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		try {
			map = service.combobox();
		} catch (Exception e) {
			log.error("CommandeTypeCtl查询候选列表异常", e);
		}
		return map;
	}

	@RequestMapping(params = "goto=combobox2")
	@ResponseBody
	public Object combobox2(HttpSession session) {
		int type = (Integer) session
				.getAttribute(SessionConstant.LOGIN_USER_TYPE);
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		try {
			map = service.combobox2(type);
		} catch (Exception e) {
			log.error("CommandeTypeCtl查询候选列表异常", e);
		}
		return map;
	}

}
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
import com.aaw.bean.CommandeStatusGroup;
import com.aaw.sys.service.ICommandeStatusGroupService;

@Controller
@RequestMapping("commandeStatusGroup")
public class CommandeStatusGroupCtl extends BaseCtl<CommandeStatusGroup> {

	@Resource
	private ICommandeStatusGroupService service;

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/commandeStatusTemp/commandeStatusGroup");
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
	public Object save(CommandeStatusGroup bean, HttpServletRequest request) {
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
	public Object update(CommandeStatusGroup bean, HttpServletRequest request) {
		try {
			service.update(bean);
			return updateSuc(request);
		} catch (Exception e) {
			log.error("CommandeTypeCtl修改对象异常", e);
		}
		return updateFail(request);
	}

	@RequestMapping(params = "goto=combox")
	@ResponseBody
	public Object combox() {
		try {
			List<CommandeStatusGroup> temp = service.list();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			if (temp == null || temp.isEmpty()) {
				return null;
			}
			for (CommandeStatusGroup csg : temp) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", csg.getId());
				map.put("name", csg.getName());
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			log.error("CommandeTypeCtl修改对象异常", e);
		}
		return null;
	}
}

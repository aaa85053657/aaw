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

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.Workstation;
import com.aaw.sys.service.IWorkstationService;

@Controller
@RequestMapping({ "Workstation", "work" })
public class WorkstationCtl extends BaseCtl<Workstation> {

	@RequestMapping(params = "act=view")
	public ModelAndView view() {
		return new ModelAndView("sys/Workstation/Workstation");
	}

	@RequestMapping("stage")
	public ModelAndView stage() {
		return new ModelAndView("sys/Workstation/stage");
	}

	@RequestMapping(params = "act=temp")
	public ModelAndView temp(@RequestParam(defaultValue = "1") int id) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		return new ModelAndView("sys/Workstation/temp", data);
	}

	@RequestMapping(params = "act=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("WorkstationCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "act=listCfg")
	@ResponseBody
	public Object listCfg(@RequestParam(defaultValue = "0") int id) {
		try {
			return service.listCfg(id);
		} catch (Exception e) {
			log.error("WorkstationCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "act=save")
	@ResponseBody
	public Object save(Workstation bean) {
		try {
			bean.setTimeCreation(new Date());
			service.save(bean);
			return suc("添加成功！");
		} catch (Exception e) {
			log.error("WorkstationCtl保存对象异常", e);
		}
		return err("添加失败！");
	}

	@RequestMapping(params = "act=update")
	@ResponseBody
	public Object update(Workstation bean) {
		try {
			bean.setTimeModification(new Date());
			service.update(bean);
			return suc("修改成功");
		} catch (Exception e) {
			log.error("WorkstationCtl修改对象异常", e);
		}
		return err("修改失败");
	}

	@RequestMapping(params = "act=unique")
	@ResponseBody
	public Object unique(@RequestParam(defaultValue = "0") int id,
			@RequestParam(defaultValue = "0") String propName,
			@RequestParam(defaultValue = "0") String propVal) {
		try {
			return unique(service, propName, propVal, id);
		} catch (Exception e) {
			log.error("WorkstationCtl通过唯一性查询异常", e);
		}
		return 3;
	}

	@RequestMapping(params = "act=combobox")
	@ResponseBody
	public Object combobox() {
		try {
			List<Object[]> list = service.list4Props("id", "name");
			if (!npv.isNull(list)) {
				List<Map<Object, Object>> reList = new ArrayList<Map<Object, Object>>();
				for (Object[] objs : list) {
					Map<Object, Object> map = new HashMap<Object, Object>();
					map.put("id", objs[0]);
					map.put("text", objs[1]);
					reList.add(map);
				}
				return reList;
			}
		} catch (Exception e) {
			log.error("WorkstationCtl用于获取候选信息异常", e);
		}
		return null;
	}

	// @RequestMapping(params = "act=del")
	// @ResponseBody
	// public Object delete(@RequestParam(defaultValue = "0") int id,
	// HttpServletRequest request) {
	// try {
	// if (service.deleteAndCheck(id)) {
	// return delSuc(request);
	// } else {
	// return delRef(request);
	// }
	// } catch (Exception e) {
	// log.error("WorkstationCtl通过ID删除对象异常", e);
	// }
	// return delFail(request);
	// }

	/**
	 * 保存 配置
	 * 
	 * @param sequenceNum
	 *            生产线的ID信息
	 * @param procedure
	 *            需要关联的procedure
	 * @param id
	 *            workstation的主键ID
	 * @param request
	 * @return
	 */
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
						.getMessage("wk.cfg.saveSuc"));
			}
		} catch (Exception e) {
			log.error("WorkstationCtl保存配置异常", e);
		}
		return err(new RequestContext(request).getMessage("wk.cfg.saveFail"));
	}

	@Resource
	private IWorkstationService service;

}

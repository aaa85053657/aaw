package com.aaw.sys.ctl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.SlaveCommande;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.aaw.sys.service.ISlaveCommandeService;
import com.aaw.sys.service.IWorksheetService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("slaveCommande")
public class SlaveCommandeCtl extends BaseCtl<SlaveCommande> {

	@Resource
	private ISlaveCommandeService service;

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
			log.error("SlaveCommandeCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(SlaveCommande bean, HttpServletRequest request) {
		try {
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("SlaveCommandeCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(SlaveCommande bean, HttpServletRequest request) {
		try {
			service.update(bean);
			return suc("修改成功");
		} catch (Exception e) {
			log.error("SlaveCommandeCtl修改对象异常", e);
		}
		return err("修改异常");
	}

	@RequestMapping(params = "goto=delCheck")
	@ResponseBody
	public Object delCheck(@RequestParam(defaultValue = "0") int id) {
		try {
			if (service.delCheck(id)) {
				return 1;
			} else {
				return 2;
			}
		} catch (Exception e) {
			log.error("SlaveCommandeCtl判断能否删除对象异常", e);
		}
		return 2;
	}

	@RequestMapping(params = "goto=getMainId")
	@ResponseBody
	public Object getMainId(@RequestParam(defaultValue = "0") int id) {
		SlaveCommande sc = service.query(id);
		return sc.getMainCommande().getId();
	}

	@RequestMapping(params = "goto=del")
	@ResponseBody
	public Object delete(@RequestParam(defaultValue = "0") int id,
			HttpServletRequest request) {
		try {
			worksheetService.updateDelSlave(id);
			service.delete(id);
			return suc("删除成功");
		} catch (Exception e) {
			log.error("SlaveCommandeCtl通过ID删除对象异常", e);
			e.printStackTrace();
		}
		return err("删除异常");
	}

	@RequestMapping(params = "goto=unique")
	@ResponseBody
	public Object unique(String propName, String propVal,
			@RequestParam(defaultValue = "0") int id) {
		try {
			return unique(service, propName, propVal, id);
		} catch (Exception e) {
			log.error("SlaveCommandeCtl验证是否唯一异常", e);
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
			log.error("SlaveCommandeCtl查询候选列表异常", e);
		}
		return map;
	}

	@Resource
	private IWorksheetService worksheetService;
}
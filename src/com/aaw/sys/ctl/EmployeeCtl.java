package com.aaw.sys.ctl;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.molos.common.SessionConstant;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.Employee;
import com.aaw.bean.EmployeeProcedureConfig;
import com.aaw.bean.MetaProcedure;
import com.aaw.sys.service.IEmployeeService;
import com.aaw.sys.service.IMetaProcedureService;
import com.onbarcode.barcode.Codabar;
import com.onbarcode.barcode.IBarcode;

@Controller
@RequestMapping("employee")
public class EmployeeCtl extends BaseCtl<Employee> {

	@Resource
	private IEmployeeService service;

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/employee/employee");
	}

	@RequestMapping(params = "goto=cfgSkill")
	public ModelAndView viewCfgSkill() {
		return new ModelAndView("sys/employeeProcedure/employeeProcedure");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("EmployeeCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=list2")
	@ResponseBody
	public Object list2(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.mapDesc(page, rows);
		} catch (Exception e) {
			log.error("EmployeeCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=procedure")
	@ResponseBody
	public Object procedure(@RequestParam(defaultValue = "0") int id) {
		List<Integer> list = service.findProceById(id);
		List<Integer> list3 = service.findById(id);
		List<MetaProcedure> list2 = new ArrayList<MetaProcedure>();
		for (int i = 0; i < list.size(); i++) {
			MetaProcedure metaProcedure = metaProcedureService.query(list
					.get(i));
			metaProcedure.setId(list3.get(0));
			list2.add(metaProcedure);
		}
		return list2;

	}

	/**
	 * 保存
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(params = "goto=saveProcedure")
	@ResponseBody
	public Object saveProcedure(EmployeeProcedureConfig params, int[] procedures) {
		try {

			service.saveProcedure(params, procedures);
			return suc("新增技能成功");
		} catch (Exception e) {
			log.error("EmployeeCtl保存技能异常", e);
		}
		return err("新增技能失败");
	}

	@RequestMapping(params = "goto=delProcedure")
	@ResponseBody
	public Object delProcedure(@RequestParam(defaultValue = "0") int id) {
		try {
			service.delProcedure(id);
			return suc("移除技能成功");
		} catch (Exception e) {
			log.error("EmployeeCtl保存技能异常", e);
		}
		return err("移除技能失败");
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(Employee bean, HttpServletRequest request) {
		try {
			bean.setTimeCreation(new Date());
			// if (bean.getAccount() != null
			// && (bean.getAccount().getId() == null || bean.getAccount()
			// .getId() < 1)) {
			// bean.setAccount(null);
			// }
			// bean.setBadgeCode(new String(System.currentTimeMillis() + ""));
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("EmployeeCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(Employee bean, HttpServletRequest request) {
		try {
			bean.setTimeModification(new Date());
			// if (bean.getAccount() != null && (bean.getAccount().getId() ==
			// null || bean.getAccount().getId() < 1)) {
			// bean.setAccount(null);
			// }
			service.update(bean);
			return updateSuc(request);
		} catch (Exception e) {
			log.error("EmployeeCtl修改对象异常", e);
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
			log.error("EmployeeCtl通过ID删除对象异常", e);
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
			log.error("EmployeeCtl验证是否唯一异常", e);
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
			log.error("EmployeeCtl查询候选列表异常", e);
		}
		return map;
	}

	@RequestMapping(params = "goto=oubobox")
	@ResponseBody
	public Object comboboxCanCrateOrderUser() {
		try {
			List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
			Map<String, Object> cc = new HashMap<String, Object>();
			cc.put("isStaff", 1);
			List<Employee> list = service.list(cc);
			for (Employee a : list) {
				if (a.getName().trim().equals("SuperAdministrator")) {
					continue;
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", a.getId());
				map.put("text", a.getName());
				reList.add(map);
			}
			return reList;
		} catch (Exception e) {
			log.error("EmployeeCtl查询候选列表异常", e);
		}
		return null;
	}

	@RequestMapping(params = "act=badgeCode")
	public void badgeCode(String code, HttpServletResponse response) {
		try {
			// EAN13 barcode = new EAN13();
			Codabar barcode = new Codabar();
			// EAN128 barcode=new EAN128();
			barcode.setData(code);
			ServletOutputStream servletoutputstream = response
					.getOutputStream();
			response.setContentType("image/jpeg");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			// Generate EAN13 barcode & output to ServletOutputStream
			barcode.drawBarcode(servletoutputstream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(params = "act=badgeCode2")
	public void badgeCode2(String code, HttpServletResponse response) {
		try {
			// EAN13 barcode = new EAN13();
			Codabar barcode = new Codabar();
			// EAN128 barcode=new EAN128();
			barcode.setData(code);
			barcode.setRotate(IBarcode.ROTATE_270);
			barcode.setUom(IBarcode.UOM_PIXEL);
			// barcode bar module width (X) in pixel
			barcode.setX(1.5f);
			// barcode bar module height (Y) in pixel
			barcode.setY(30f);
			barcode.setLeftMargin(1f);
			barcode.setRightMargin(0f);
			barcode.setTopMargin(0f);
			barcode.setBottomMargin(0f);
			// barcode encoding data font style
			barcode.setTextFont(new Font("宋体", 0, 12));
			// space between barcode and barcode encoding data
			barcode.setTextMargin(6);
			ServletOutputStream servletoutputstream = response
					.getOutputStream();
			response.setContentType("image/jpeg");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			// Generate EAN13 barcode & output to ServletOutputStream
			barcode.drawBarcode(servletoutputstream);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(params = "act=outTime")
	@ResponseBody
	public Object outTime(HttpSession session, HttpServletRequest request) {
		Employee e = (Employee) request.getSession().getAttribute(
				SessionConstant.LOGIN_USER);
		if (e == null) {
			return suc("超时!");
		}
		return err("未超时！");
	}


	@Resource
	private IMetaProcedureService metaProcedureService;

}
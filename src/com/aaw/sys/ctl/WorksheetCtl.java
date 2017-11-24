package com.aaw.sys.ctl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import molos.plugins.tool.ToolFactory;
import molos.plugins.tool.common.IDateTool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import cn.molos.common.SessionConstant;
import cn.molos.task.TaskManager;
import cn.molos.task.WorksheetUnLockTask;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.Employee;
import com.aaw.bean.FactoryStatus;
import com.aaw.bean.FactoryStatusDetail;
import com.aaw.bean.SlaveCommandeFreeConfig;
import com.aaw.bean.Worksheet;
import com.aaw.bean.WorksheetDetail;
import com.aaw.bean.WorksheetDetailTemp;
import com.aaw.bean.Workstation;
import com.aaw.sys.form.SlaveCfgDetail;
import com.aaw.sys.form.WorkingForm;
import com.aaw.sys.service.IEmployeeService;
import com.aaw.sys.service.IFactoryStatusDetailService;
import com.aaw.sys.service.IFactoryStatusService;
import com.aaw.sys.service.ISlaveCommandeConfigService;
import com.aaw.sys.service.ISlaveCommandeFreeConfigService;
import com.aaw.sys.service.ISlaveCommandeService;
import com.aaw.sys.service.IWorksheetDetailService;
import com.aaw.sys.service.IWorksheetDetailTempService;
import com.aaw.sys.service.IWorksheetService;
import com.aaw.sys.service.IWorkstationService;

@Controller
@RequestMapping("worksheet")
public class WorksheetCtl extends BaseCtl<Worksheet> {

	@Resource
	private IWorksheetService service;

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/worksheet/worksheet");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("WorksheetCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(Worksheet bean, HttpServletRequest request) {
		try {
			bean.setTimeCreation(new Date());
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("WorksheetCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(Worksheet bean, HttpServletRequest request) {
		try {
			bean.setTimeModification(new Date());
			service.update(bean);
			return suc("修改成功");
		} catch (Exception e) {
			log.error("WorksheetCtl修改对象异常", e);
		}
		return err("修改异常");
	}

	@RequestMapping(params = "goto=del")
	@ResponseBody
	public Object delete(@RequestParam(defaultValue = "0") int id,
			HttpServletRequest request) {
		try {
			if (service.deleteAndCheck(id)) {
				worksheetDetailService.endTask(id);
				Worksheet worksheet = service.query(id);
				worksheet.setTimeDelete(new Date());
				worksheet.setSlaveCommande(null);
				FactoryStatus factoryStatus = factoryStatusService
						.findByWorksheet(id);
				factoryStatus.setOptStatus(8);
				factoryStatus.setWorkStatus(8);
				service.update(worksheet);
				factoryStatusService.update(factoryStatus);
				return delSuc(request);
			} else {
				return delRef(request);
			}
		} catch (Exception e) {
			log.error("WorksheetCtl通过ID删除对象异常", e);
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
			log.error("WorksheetCtl验证是否唯一异常", e);
		}
		return 2;
	}

	@RequestMapping(params = "goto=uniqueCommande")
	@ResponseBody
	public Object uniqueCommande(String propName, String propVal,
			@RequestParam(defaultValue = "0") int id) {
		try {
			return service.uniqueCommande(propVal);
		} catch (Exception e) {
			log.error("WorksheetCtl验证是否唯一异常", e);
		}
		return 2;
	}

	@RequestMapping(params = "goto=working")
	@ResponseBody
	public Object procedureWorksheet(HttpServletRequest request,
			WorkingForm form) {
		try {
			service.updateTask(form);
			return suc(new RequestContext(request).getMessage("crud.del.suc"));
		} catch (Exception e) {
			log.error("WorksheetCtl加工单处理异常", e);
		}
		return err(new RequestContext(request).getMessage("crud.del.suc"));
	}

	/**
	 * @param id
	 *            副单ID
	 * @param wid
	 *            worksheet ID
	 * @return
	 */
	@RequestMapping(params = "goto=handling")
	public ModelAndView handling(@RequestParam(defaultValue = "0") int id,
			@RequestParam(defaultValue = "0") int wid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SlaveCfgDetail> configs = configService.queryListByCommandeId(id);
		map.put("sid", id);
		map.put("scds", configs);
		map.put("sm", slaveCommandeService.query(id));
		map.put("content", configService.slaveDetail(id));
		map.put("metaProcedureList",
				worksheetDetailService.queryListByWorksheetID(wid, 1, 1));
		return new ModelAndView("sys/worksheetDefatil/handling", map);
	}

	@RequestMapping(params = "goto=querySlave")
	@ResponseBody
	public Object querySlave(@RequestParam(defaultValue = "0") int id) {
		return configService.queryListByCommandeId(id);
	}

	/**
	 * @param id
	 *            父ID
	 * @param t
	 *            类型，1为操作数据的页面，0为不可操作数据
	 * 
	 * @return
	 */
	@RequestMapping(params = "goto=oprProfile")
	public ModelAndView oprProfile(@RequestParam(defaultValue = "0") int id,
			@RequestParam(defaultValue = "0") int t) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<WorksheetDetail> list = worksheetDetailService.queryListByPID(id,
				1);
		map.put("detail", list);
		map.put("wd4mp", worksheetDetailService.query(id));
		if (t == 1) {
			map.put("pid", id);
			return new ModelAndView("sys/worksheet/profile-write", map);
		} else {
			return new ModelAndView("sys/worksheet/profile-read", map);
		}
	}

	@RequestMapping(params = "goto=beginWorking")
	@ResponseBody
	public Object beginWorking(@RequestParam(defaultValue = "0") int wid,
			HttpSession se) {
		Employee e = (Employee) se.getAttribute(SessionConstant.LOGIN_USER);
		if (e == null) {
			e = new Employee();
			e.setId(1);
		}
		Worksheet worksheet = service.query(wid);
		if (worksheet.getOpr() == null
				|| worksheet.getOpr().getId() == e.getId()) {
			return suc("success");
		}
		return err("该加工单已被加工");
	}

	/**
	 * 根据编号查找用户或订单
	 */
	@RequestMapping(params = "goto=findByCode")
	@ResponseBody
	public Object findByCode(@RequestParam String id,
			@RequestParam(defaultValue = "0") int type,
			HttpServletRequest request) {
		Employee e = (Employee) request.getSession().getAttribute(
				SessionConstant.LOGIN_USER);
		if (e == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", 9);
			return map;
		}
		String ip = getIpAddr(request);
		Map<String, Object> cc = new HashMap<String, Object>();
		cc.put("parameter", ip);
		List<Workstation> wks = workstationService.list(cc);
		return service.findByCode(id, type, wks.get(0));
	}

	/**
	 * 根据编号二次查找
	 * 
	 * @param id
	 * @param code
	 * @param type
	 * @return
	 */
	@RequestMapping(params = "goto=findByCode2")
	@ResponseBody
	public Object findByCode2(@RequestParam(defaultValue = "0") int id,
			@RequestParam String code,
			@RequestParam(defaultValue = "0") int type,
			HttpServletRequest request) {
		Employee e = (Employee) request.getSession().getAttribute(
				SessionConstant.LOGIN_USER);
		if (e == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", 9);
			return map;
		}
		String ip = getIpAddr(request);
		Map<String, Object> cc = new HashMap<String, Object>();
		cc.put("parameter", ip);
		List<Workstation> wks = workstationService.list(cc);
		return service.findByCode2(id, code, type, wks);

	}

	/**
	 * app 详细操作界面
	 * 
	 * @return
	 */
	@RequestMapping(params = "goto=appView")
	public ModelAndView appView(@RequestParam(defaultValue = "0") int did,
			@RequestParam(defaultValue = "0") int eid,
			@RequestParam(defaultValue = "0") int oprTime) {
		if (did == 0) {
			return new ModelAndView("app");
		}
		Employee employee = employeeService.query(eid);
		List<WorksheetDetail> temp = worksheetDetailService.findById(did);
		WorksheetDetailTemp detail = null;
		WorksheetDetail worksheetDetail = null;
		for (WorksheetDetail detail2 : temp) {
			if (detail2.getStatus() != 9) {
				Date date = new Date();
				detail2.setIsRead(null);
				detail2.setCreationTime(date);
				detail2.setModificationTime(date);
				TaskManager taskManager = TaskManager.newInstance();
				detail2.setStatus(9);
				if (oprTime != 0) {
					// detail2.setEndTime(d.dateBeforeMinute(new Date(),
					// oprTime));
					detail2.setUnlockTime(d.dateBeforeMinute(new Date(),
							oprTime));
				}
				if (oprTime != 0) {
					try {
						taskManager.schedule(
								new WorksheetUnLockTask(detail2.getId()),
								(oprTime * 60 * 1000));
					} catch (Exception e) {
					}
				}
				worksheetDetailService.update(detail2);
			}
			if (detail2.getParentId() == 0) {
				detail = detailTempService.findByDetail(detail2.getId());
				worksheetDetail = detail2;
			}

		}
		// 订单工序时间轴记录
		WorksheetDetail detail2 = worksheetDetailService.query(did);
		FactoryStatus factoryStatus = factoryStatusService
				.findByWorksheet(detail2.getWorksheet().getId());
		factoryStatus.setStatus(factoryStatus.getStatus());
		factoryStatus.setCurrentStatus(2);
		factoryStatus.setModificationTime(new Date());
		factoryStatus.setOptStatus(11);
		factoryStatus.setWorkStatus(6);
		factoryStatusService.update(factoryStatus);
		FactoryStatusDetail statusDetail = factoryStatusDetailService
				.findByWDID(did);
		if (statusDetail == null) {
			statusDetail = new FactoryStatusDetail();
			statusDetail.setFactoryStatus(factoryStatus);
			statusDetail.setStatus(factoryStatus.getStatus());
			statusDetail.setCurrentStatus(2);
			statusDetail.setModificationTime(new Date());
			factoryStatusDetailService.save(statusDetail);
		}

		temp.remove(worksheetDetail);
		List<WorksheetDetailTemp> list = new ArrayList<WorksheetDetailTemp>();
		for (WorksheetDetail detailTemp : temp) {
			list.add(detailTempService.findByDetail(detailTemp.getId()));
		}
		List<SlaveCfgDetail> cfgDetails = null;
		if (detail != null) {
			cfgDetails = configService.queryListByCommandeId(detail
					.getWorksheetDetail().getWorksheet().getSlaveCommande()
					.getId());
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("msg", "该订单操作，已在本操作台上进行过");
			return new ModelAndView("app", map);
		}
		List<SlaveCommandeFreeConfig> list2 = configService2
				.queryListByCommandeId(detail.getWorksheetDetail()
						.getWorksheet().getSlaveCommande().getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("employee", employee);
		map.put("detail", detail);
		map.put("details", list);
		map.put("cfgDetails", cfgDetails);
		map.put("list2", list2);
		return new ModelAndView("app2", map);
	}

	/**
	 * 查找正在加工的商品
	 * 
	 * @return
	 */
	@RequestMapping(params = "goto=findWorking")
	@ResponseBody
	public Object findWorking(HttpServletRequest request) {
		Employee e = (Employee) request.getSession().getAttribute(
				SessionConstant.LOGIN_USER);
		if (e == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", 9);
			return map;
		}
		String ip = getIpAddr(request);
		Map<String, Object> cc = new HashMap<String, Object>();
		cc.put("parameter", ip);
		List<Workstation> wks = workstationService.list(cc);
		return service.findWorking(wks.get(0));
	}

	/**
	 * 修改操作人
	 * 
	 * @return
	 */
	@RequestMapping(params = "goto=updateEMP")
	@ResponseBody
	public Object updateEMP(@RequestParam(defaultValue = "0") int did,
			@RequestParam(defaultValue = "0") int eid) {
		return service.updateEMP(did, eid);
	}

	/**
	 * 验证是否可以跳转
	 * 
	 * @param wid
	 * @return
	 */
	@RequestMapping(params = "goto=clickWork")
	@ResponseBody
	public Object clickWork(@RequestParam(defaultValue = "0") int wid,
			HttpServletRequest request) {
		String ip = getIpAddr(request);
		Map<String, Object> cc = new HashMap<String, Object>();
		cc.put("parameter", ip);
		List<Workstation> wks = workstationService.list(cc);
		return service.clickWork(wid, wks.get(0));
	}

	@RequestMapping(params = "goto=appView3")
	public ModelAndView findByWid2(@RequestParam(defaultValue = "0") int did,
			@RequestParam(defaultValue = "0") int oprTime) {
		if (did == 0) {
			return new ModelAndView("app");
		}
		List<WorksheetDetail> temp = worksheetDetailService.findById(did);
		WorksheetDetailTemp detail = null;
		WorksheetDetail worksheetDetail = null;
		for (WorksheetDetail detail2 : temp) {
			TaskManager taskManager = TaskManager.newInstance();
			detail2.setStatus(9);
			if (oprTime != 0) {
				detail2.setEndTime(d.dateBeforeMinute(new Date(), oprTime));
			}
			worksheetDetailService.update(detail2);
			if (detail2.getParentId() == 0) {
				detail = detailTempService.findByDetail(detail2.getId());
				worksheetDetail = detail2;
			}
			if (oprTime != 0) {
				taskManager.schedule(new WorksheetUnLockTask(detail2.getId()),
						(oprTime * 60 * 1000));
			}
		}
		temp.remove(worksheetDetail);
		List<WorksheetDetailTemp> list = new ArrayList<WorksheetDetailTemp>();
		for (WorksheetDetail detailTemp : temp) {
			list.add(detailTempService.findByDetail(detailTemp.getId()));
		}
		List<SlaveCfgDetail> cfgDetails = configService
				.queryListByCommandeId(detail.getWorksheetDetail()
						.getWorksheet().getSlaveCommande().getId());
		Map<String, Object> map = new HashMap<String, Object>();
		List<SlaveCommandeFreeConfig> list2 = configService2
				.queryListByCommandeId(detail.getWorksheetDetail()
						.getWorksheet().getSlaveCommande().getId());
		map.put("employee", detail.getWorksheetDetail().getWorksheet().getOpr());
		map.put("detail", detail);
		map.put("details", list);
		map.put("cfgDetails", cfgDetails);
		map.put("list2", list2);
		return new ModelAndView("app2", map);
	}

	@RequestMapping(params = "goto=isOverTime")
	@ResponseBody
	public Object isOverTime(@RequestParam(defaultValue = "0") int wid) {
		List<WorksheetDetail> details = worksheetDetailService
				.findOverTime(wid);
		List<WorksheetDetail> details2 = worksheetDetailService.findEnd(wid);
		if (npv.isNull(details) && npv.isNull(details2)) {
			return err("没有");
		} else if (!npv.isNull(details) && npv.isNull(details2)) {
			WorksheetDetail detail = details.get(0);
			detail.setIsRead(null);
			worksheetDetailService.update(detail);
			return suc("加工单已超时回滚");
		} else {
			return suc("加工单已终止！");
		}

	}

	IDateTool d = ToolFactory.getDateTool();

	@Resource
	private ISlaveCommandeService slaveCommandeService;
	@Resource
	private ISlaveCommandeConfigService configService;
	@Resource
	private IWorksheetDetailService worksheetDetailService;
	@Resource
	private IEmployeeService employeeService;
	@Resource
	private IWorksheetDetailTempService detailTempService;
	@Resource
	private IWorkstationService workstationService;
	@Resource
	private IFactoryStatusService factoryStatusService;
	@Resource
	private IFactoryStatusDetailService factoryStatusDetailService;
	@Resource
	private ISlaveCommandeFreeConfigService configService2;

}

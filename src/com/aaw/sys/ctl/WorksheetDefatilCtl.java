package com.aaw.sys.ctl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import molos.plugins.tool.ToolFactory;
import molos.plugins.tool.common.IDateTool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import cn.molos.common.PropTool;
import cn.molos.common.SessionConstant;
import cn.molos.task.TaskManager;
import cn.molos.task.WorksheetUnLockTask;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.Employee;
import com.aaw.bean.FactoryStatus;
import com.aaw.bean.FactoryStatusDetail;
import com.aaw.bean.RollBackDetail;
import com.aaw.bean.Worksheet;
import com.aaw.bean.WorksheetDetail;
import com.aaw.bean.WorksheetException;
import com.aaw.bean.WorksheetHistory;
import com.aaw.sys.form.WorkingForm;
import com.aaw.sys.service.IFactoryStatusDetailService;
import com.aaw.sys.service.IFactoryStatusService;
import com.aaw.sys.service.ISlaveCommandeConfigService;
import com.aaw.sys.service.ISlaveCommandeService;
import com.aaw.sys.service.IWorksheetDetailService;
import com.aaw.sys.service.IWorksheetDetailTempService;
import com.aaw.sys.service.IWorksheetExceptionService;
import com.aaw.sys.service.IWorksheetHistoryService;
import com.aaw.sys.service.IWorksheetService;

/**
 * @author Owner
 *
 */
@Controller
@RequestMapping("worksheetdefatil")
public class WorksheetDefatilCtl extends BaseCtl<WorksheetDetail> {

	@RequestMapping(params = "goto=task")
	public ModelAndView task(HttpServletRequest request) {
		request.getSession().setAttribute("REFRESH_TASK_TIME",
				Integer.parseInt(PropTool.get("REFRESH_TASK_TIME")) * 1000);
		return new ModelAndView("sys/worksheetDefatil/task");
	}

	/**
	 * 获取表单列表
	 * 
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object queryList(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows,
			HttpServletRequest request) {
		Employee e = (Employee) request.getSession().getAttribute(
				SessionConstant.LOGIN_USER);
		if (e != null) {
			return worksheetDetailService.queryList(page, rows, e.getId());
		}
		return worksheetDetailService.queryList(page, rows, 0);

	}

	@RequestMapping(params = "goto=map4EUI")
	@ResponseBody
	public Object searchMore(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows,
			@RequestParam(defaultValue = "0") int pid,
			HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute(
				SessionConstant.LOGIN_USER);
		if (employee == null) {
			employee = new Employee();
			employee.setId(1);
		}
		if (pid != 0) {
			return worksheetDetailService.searchMore(page, rows, pid,
					employee.getId());
		} else {
			return worksheetDetailService.getWorkSheetList(page, rows,
					employee.getId());
		}
	}

	// /**
	// * @param id
	// * 副单ID
	// * @param wid
	// * worksheet ID
	// * @return
	// */
	// @RequestMapping(params = "goto=handling")
	// public ModelAndView handling(@RequestParam(defaultValue = "0") int id,
	// @RequestParam(defaultValue = "0") int wid,
	// HttpServletRequest request) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("sm", slaveCommandeService.query(id));
	// map.put("content", configService.slaveDetail(id));
	// map.put("metaProcedureList",
	// worksheetDetailService.queryListByWorksheetID(wid, 1, 1));
	// map.put("profileList", worksheetDetailService.queryListByPID(wid, 1));
	// return new ModelAndView("sys/worksheetDefatil/handling", map);
	// }

	/**
	 * 获取回滚列表
	 * 
	 * @param wid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "goto=getWorksheet")
	@ResponseBody
	public Object getWorksheet(@RequestParam(defaultValue = "0") int wid) {
		WorksheetDetail worksheetDetail = worksheetDetailService.query(wid);
		List<WorksheetDetail> details = (List<WorksheetDetail>) worksheetDetailService
				.queryListByWorksheetDetailID(worksheetDetail.getWorksheet()
						.getId());
		List<RollBackDetail> list = new ArrayList<RollBackDetail>();
		for (WorksheetDetail detail : details) {
			RollBackDetail rollBackDetail = new RollBackDetail();
			rollBackDetail.setId(detail.getId());
			rollBackDetail.setName(detail.getMetaProcedure().getName());
			list.add(rollBackDetail);
		}
		return list;
	}

	/**
	 * 异常处理
	 * 
	 * @param workException
	 * @param worksheetDetailId
	 * @param rollBackId
	 * @return
	 */
	@RequestMapping(params = "goto=saveExcption")
	@ResponseBody
	public Object saveException(WorksheetException workException,
			int[] worksheetDetailId, int[] rollBackId, int[] er, int wid2) {
		try {
			if (worksheetDetailId != null && worksheetDetailId.length != 0
					&& rollBackId != null && rollBackId.length != 0
					&& er != null && er.length != 0) {
				Worksheet worksheet = null;
				StringBuffer sb = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				for (int i : er) {
					sb2.append(i).append(",");
				}
				for (int rid : rollBackId) {
					sb.append(rid).append(",");
					List<WorksheetDetail> details = worksheetDetailService
							.findById(rid);
					for (WorksheetDetail detail : details) {
						detail.setComments("");
						detail.setOperator(null);
						detail.setStatus(4);
						detail.setEndTime(null);
						detail.setCreationTime(null);
						detail.setUnlockTime(null);
						detail.setModificationTime(new Date());
						worksheetDetailService.update(detail);
						worksheet = detail.getWorksheet();
						detailTempService.deleteByWDID(detail.getId());
					}
				}
				String str = sb.deleteCharAt(sb.lastIndexOf(",")).toString();
				String str2 = sb2.deleteCharAt(sb2.lastIndexOf(",")).toString();
				for (int wid : worksheetDetailId) {
					workException.setWorksheetDetail(new WorksheetDetail(wid));
					workException.setCreateTime(new Date());
					workException.setRollBack(str);
					workException.setExceptionReason(str2);
					exceptionService.save(workException);
				}
				worksheet.setOpr(null);
				worksheetService.update(worksheet);

				WorksheetDetail detail = worksheetDetailService.query(wid2);
				FactoryStatus factoryStatus = factoryStatusService
						.findByWorksheet(detail.getWorksheet().getId());
				FactoryStatusDetail statusDetail = new FactoryStatusDetail();
				statusDetail.setFactoryStatus(factoryStatus);
				statusDetail.setStatus(factoryStatus.getStatus());
				statusDetail.setCurrentStatus(4);
				statusDetail.setModificationTime(new Date());
				statusDetail.setWorksheetDetail(detail);
				factoryStatusDetailService.save(statusDetail);
				if (factoryStatus != null) {
					WorksheetDetail worksheetDetail = worksheetDetailService
							.query(rollBackId[0]);
					factoryStatus
							.setStatus((worksheetDetail.getPriority() - 1));
					factoryStatus.setCurrentStatus(4);
					factoryStatus.setOptStatus(10);
					factoryStatus.setModificationTime(new Date());
					factoryStatusService.update(factoryStatus);
				}

				return suc("异常记录成功，对异常工序已回滚");
			}
			return err("异常记录失败,请选择失误操作，以及回滚操作");

		} catch (Exception e) {
			log.error("WorksheetCtl加工单异常处理失败", e);
		}
		return err("异常记录失败");

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
		WorksheetDetail detail = worksheetDetailService.query(id);
		map.put("detail", list);
		map.put("flag", detail.getStatus());
		map.put("wd4mp", worksheetDetailService.query(id));
		if (t == 1) {
			map.put("pid", id);
			return new ModelAndView("sys/worksheetDefatil/profile-write", map);
		} else {
			return new ModelAndView("sys/worksheetDefatil/profile-read", map);
		}
	}

	@RequestMapping(params = "goto=working")
	@ResponseBody
	public Object procedureWorksheet(HttpServletRequest request,
			WorkingForm form) {
		try {
			// if (!worksheetDetailService.checkExecute(form
			// .getWorksheetDetailId())) {
			// return err("已在其他电脑上操作使用过！");
			// }
			WorksheetHistory history = worksheetDetailService.updateTask(form);
			WorksheetDetail detail = worksheetDetailService.query(form
					.getWorksheetDetailId());
			Date date = new Date();
			detail.setEndTime(date);
			detail.setModificationTime(date);
			worksheetDetailService.update(detail);
			detailTempService.deleteByWDID(detail.getId());
			Worksheet worksheet = detail.getWorksheet();
			worksheet.setOpr(null);
			worksheetService.update(worksheet);
			historyService.save(history);

			FactoryStatusDetail statusDetail = new FactoryStatusDetail();
			FactoryStatus factoryStatus = factoryStatusService
					.findByWorksheet(detail.getWorksheet().getId());
			factoryStatus.setStatus(detail.getPriority());
			if (detail.getPriority() == factoryStatus.getStatusCount()) {
				factoryStatus.setOptStatus(12);
			} else {
				factoryStatus.setOptStatus(10);
			}
			factoryStatusService.update(factoryStatus);
			statusDetail.setFactoryStatus(factoryStatus);
			statusDetail.setStatus(detail.getPriority());
			statusDetail.setCurrentStatus(3);
			statusDetail.setModificationTime(new Date());
			statusDetail.setWorksheetDetail(detail);
			factoryStatusDetailService.save(statusDetail);
			if (factoryStatus != null) {
				factoryStatus.setCurrentStatus(3);
				factoryStatusService.update(factoryStatus);
			}
			return suc(new RequestContext(request).getMessage("crud.del.suc"));
		} catch (Exception e) {
			log.error("WorksheetCtl加工单处理异常", e);
		}
		return err(new RequestContext(request).getMessage("crud.del.suc"));
	}

	/**
	 * 保存临时记录
	 * 
	 * @param request
	 * @param form
	 * @return
	 */
	@RequestMapping(params = "goto=workingTemp")
	@ResponseBody
	public Object procedureWorksheetTemp(HttpServletRequest request,
			WorkingForm form) {
		try {
			worksheetDetailService.updateTaskTemp(form);
			return suc(new RequestContext(request).getMessage("crud.del.suc"));
		} catch (Exception e) {
			log.error("WorksheetCtl加工单处理异常", e);
		}
		return err(new RequestContext(request).getMessage("crud.del.suc"));
	}

	/**
	 * 终止加工
	 * 
	 * @param request
	 * @param form
	 * @return
	 */
	@RequestMapping(params = "goto=endworking")
	@ResponseBody
	public Object endWorksheet(HttpServletRequest request,
			@RequestParam(defaultValue = "0") int wid) {
		try {
			worksheetDetailService.endTask(wid);
			Worksheet worksheet = worksheetService.query(wid);
			worksheet.setTimeAbort(new Date());
			worksheetService.update(worksheet);
			FactoryStatus status = factoryStatusService
					.findByWorksheet(worksheet.getId());
			if (status != null) {
				status.setWorkStatus(7);
				status.setOptStatus(7);
				factoryStatusService.update(status);
			}
			return suc(new RequestContext(request).getMessage("crud.del.suc"));
		} catch (Exception e) {
			log.error("WorksheetCtl加工单处理异常", e);
		}
		return err(new RequestContext(request).getMessage("crud.del.suc"));
	}

	/**
	 * 判断是否可以执行此功能
	 * 
	 * @param wid
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goto=canClick")
	@ResponseBody
	public Object canClick(@RequestParam(defaultValue = "0") int wid,
			HttpServletRequest request) {
		Object o = request.getSession().getServletContext()
				.getAttribute("" + wid + "");
		int flag = 1;
		if (o != null) {
			flag = (Integer) o;
		}
		if (flag == 1 || flag == 0) {
			return err("不能执行此功能");
		} else {
			return suc("成功");
		}

	}

	/**
	 * 开始加工检测
	 * 
	 * @param request
	 * @param wid
	 *            加工单详细工序id
	 * @return
	 */
	@RequestMapping(params = "goto=beginwork")
	@ResponseBody
	public Object beginWork(HttpServletRequest request,
			@RequestParam(defaultValue = "0") int wid,
			@RequestParam(defaultValue = "10") int timeout) {
		TaskManager taskManager = TaskManager.newInstance();
		Employee employee = (Employee) request.getSession().getAttribute(
				SessionConstant.LOGIN_USER);
		if (employee == null) {
			employee = new Employee();
			employee.setId(1);
		}
		try {
			WorksheetDetail worksheetDetail = worksheetDetailService.query(wid);
			if (worksheetDetail.getStatus() == 1
					|| worksheetDetail.getStatus() == 4) {
				worksheetDetail.setStatus(9);
				worksheetDetail.setEndTime(d.dateBeforeMinute(new Date(),
						timeout));
				worksheetDetailService.update(worksheetDetail);
				Worksheet worksheet = worksheetDetail.getWorksheet();
				worksheet.setOpr(employee);
				worksheetService.update(worksheet);
				taskManager.schedule(new WorksheetUnLockTask(wid),
						(timeout * 60 * 1000));
				return suc("开始加工操作");
			} else {
				return err("已在加工中");
			}
		} catch (Exception e) {
			log.error("WorksheetCtl加工单处理异常", e);
		}
		return err("已在加工中");
	}

	IDateTool d = ToolFactory.getDateTool();

	@Resource
	private IWorksheetExceptionService exceptionService;
	@Resource
	private IWorksheetHistoryService historyService;
	@Resource
	private ISlaveCommandeService slaveCommandeService;
	@Resource
	private IWorksheetDetailService worksheetDetailService;
	@Resource
	private ISlaveCommandeConfigService configService;
	@Resource
	private IWorksheetService worksheetService;
	@Resource
	private IWorksheetDetailTempService detailTempService;
	@Resource
	private IFactoryStatusService factoryStatusService;
	@Resource
	private IFactoryStatusDetailService factoryStatusDetailService;

}

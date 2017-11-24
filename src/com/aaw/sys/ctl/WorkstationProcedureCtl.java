package com.aaw.sys.ctl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.WorkstationProcedure;
import com.aaw.sys.service.IWorkstationProcedureService;

@Controller
@RequestMapping("WorkstationProcedure")
public class WorkstationProcedureCtl extends BaseCtl<WorkstationProcedure> {

	@RequestMapping(params = "goto=delCfg")
	@ResponseBody
	public Object delete(@RequestParam(defaultValue = "0") int id,
			HttpServletRequest request) {
		try {
			service.delete(id);
			return delSuc(request);
		} catch (Exception e) {
			log.error("UpmsRoleCtl通过ID删除对象异常", e);
		}
		return delFail(request);
	}

	@Resource
	private IWorkstationProcedureService service;
}

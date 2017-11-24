package com.aaw.sys.ctl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.WorksheetDetailTemp;
import com.aaw.sys.service.IWorksheetDetailTempService;

/**
 * @author Owner
 *
 */
@Controller
@RequestMapping("worksheetdefatiltemp")
public class WorksheetDefatilTempCtl extends BaseCtl<WorksheetDetailTemp> {

	@Resource
	private IWorksheetDetailTempService service;

}

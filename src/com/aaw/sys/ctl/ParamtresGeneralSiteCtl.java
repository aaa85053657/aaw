package com.aaw.sys.ctl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.ParametresGeneralSite;
import com.aaw.sys.service.IParamtresGeneralSiteService;

@Controller
@RequestMapping("paramtres")
public class ParamtresGeneralSiteCtl extends BaseCtl<ParametresGeneralSite> {

	@RequestMapping(params = "clickControlExternalCode")
	@ResponseBody
	public Object clickControlExternalCode() {
		ParametresGeneralSite generalSite = service
				.findByPN("ControlExternalCode");
		if (generalSite != null && generalSite.getValueNumber() == 1) {
			return 1;
		}
		return 2;
	}

	@Resource
	private IParamtresGeneralSiteService service;

}

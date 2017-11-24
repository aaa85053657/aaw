package com.aaw.sys.ctl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.FranchisesAccount;
import com.aaw.sys.service.IFranchisesAccountService;

@Controller
@RequestMapping("franchisesAcc")
public class FranchisesAccountCtl extends BaseCtl<FranchisesAccount> {

	@RequestMapping(params = "act=unique")
	@ResponseBody
	public Object unique(@RequestParam(defaultValue = "0") int id,
			@RequestParam(defaultValue = "0") String propName,
			@RequestParam(defaultValue = "0") String propVal) {
		try {
			return unique(service, propName, propVal, id);
		} catch (Exception e) {
			log.error("UpmsAccountCtl通过唯一性查询异常", e);
		}
		return 3;
	}

	@Resource
	private IFranchisesAccountService service;

}
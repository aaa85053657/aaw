package com.aaw.sys.ctl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.molos.common.SessionConstant;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.CustomerInfo;
import com.aaw.bean.Franchises;
import com.aaw.sys.service.ICustomerInfoService;
import com.aaw.sys.service.IFranchisesService;

@Controller
@RequestMapping("customerInfo")
public class CustomerInfoCtl extends BaseCtl<CustomerInfo> {

	@Resource
	private ICustomerInfoService service;

	@Resource
	private IFranchisesService franchisesService;

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/customer/customer");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("CustomerInfoCtl列表查询异常", e);
		}
		return null;
	}

	// 筛选
	@RequestMapping(params = "goto=list2")
	@ResponseBody
	public Object list2(HttpSession session,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		int type = (Integer) session
				.getAttribute(SessionConstant.LOGIN_USER_TYPE);
		try {
			if (type == 1) {
				return service.map2(page, rows, null);
			} else {
				Franchises franchises = (Franchises) session
						.getAttribute(SessionConstant.LOGIN_USER);
				return service.map2(page, rows, franchises);
			}
			// return service.map(page, rows);
		} catch (Exception e) {
			log.error("CustomerInfoCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(HttpSession session, CustomerInfo bean,
			HttpServletRequest request) {
		try {
			int type = (Integer) session
					.getAttribute(SessionConstant.LOGIN_USER_TYPE);
			if (type == 1) {
				bean.setCustomerType(1);
				bean.setInputChannel(0);
			} else {
				Franchises franchises = (Franchises) session
						.getAttribute(SessionConstant.LOGIN_USER);
				bean.setCustomerType(2);
				bean.setInputChannel(franchises.getId());
			}
			bean.setTimeCreation(new Date());
			service.save(bean);
			session.setAttribute("addCustId", bean.getId());
			// service.updateRF(bean.getCodeId());
			return saveSuc(request);
		} catch (Exception e) {
			log.error("CustomerInfoCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=findAddId")
	@ResponseBody
	public Object findAddId(HttpSession session) {
		Integer i = (Integer) session.getAttribute("addCustId");
		session.setAttribute("addCustId", null);
		if (i == null) {
			return -1;
		}
		return i;
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(CustomerInfo bean, HttpServletRequest request) {
		try {
			bean.setTimeModification(new Date());
			service.update(bean);
			return updateSuc(request);
		} catch (Exception e) {
			log.error("CustomerInfoCtl修改对象异常", e);
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
			log.error("CustomerInfoCtl通过ID删除对象异常", e);
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
			log.error("CustomerInfoCtl验证是否唯一异常", e);
		}
		return 2;
	}

	@RequestMapping(params = "goto=combobox")
	@ResponseBody
	public Object combobox(HttpSession session) {
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		int type = (Integer) session
				.getAttribute(SessionConstant.LOGIN_USER_TYPE);
		int i = 0;
		if (type != 1) {
			Franchises franchises = (Franchises) session
					.getAttribute(SessionConstant.LOGIN_USER);
			i = franchises.getId();
		}
		try {
			map = service.combobox(i);
		} catch (Exception e) {
			log.error("CustomerInfoCtl查询候选列表异常", e);
		}
		return map;
	}
}
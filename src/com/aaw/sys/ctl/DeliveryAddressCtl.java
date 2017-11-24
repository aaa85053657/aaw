package com.aaw.sys.ctl;

import java.util.ArrayList;
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

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.DeliveryAddress;
import com.aaw.sys.service.IDeliveryAddressService;

@Controller
@RequestMapping("deliveryAddress")
public class DeliveryAddressCtl extends BaseCtl<DeliveryAddress> {

	@Resource
	private IDeliveryAddressService service;

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/deliveryAddress/addr");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("DeliveryAddressCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(HttpSession session, DeliveryAddress bean,
			HttpServletRequest request) {
		try {
			service.save(bean);
			session.setAttribute("addAdressId", bean.getId());
			return saveSuc(request);
		} catch (Exception e) {
			log.error("DeliveryAddressCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=findAddId")
	@ResponseBody
	public Object save(HttpSession session) {
		Integer i = (Integer) session.getAttribute("addAdressId");
		session.setAttribute("addAdressId", null);
		if (i == null) {
			return -1;
		}
		return i;
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(DeliveryAddress bean, HttpServletRequest request) {
		try {
			service.update(bean);
			return updateSuc(request);
		} catch (Exception e) {
			log.error("DeliveryAddressCtl修改对象异常", e);
		}
		return updateFail(request);
	}

	@RequestMapping(params = "goto=del")
	@ResponseBody
	public Object delete(@RequestParam(defaultValue = "0") int id,
			HttpServletRequest request) {
		try {
			service.delete(id);
			return delSuc(request);
		} catch (Exception e) {
			log.error("DeliveryAddressCtl通过ID删除对象异常", e);
		}
		return delFail(request);
	}

	@RequestMapping(params = "goto=combobox")
	@ResponseBody
	public Object combobox(@RequestParam(defaultValue = "0") int id) {
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		try {
			if (id > 0) {
				map = service.combobox(id);
			}
		} catch (Exception e) {
			log.error("DeliveryAddressCtl查询候选列表异常", e);
		}
		return map;
	}
}
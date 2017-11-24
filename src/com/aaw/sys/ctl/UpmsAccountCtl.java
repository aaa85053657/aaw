package com.aaw.sys.ctl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.UpmsAccount;
import com.aaw.sys.service.IEmployeeService;
import com.aaw.sys.service.IUpmsAccountService;

/**
 * @description 账户信息控制对象
 * @author molos
 * @createTime 2015-06-09 10:11:57
 */
@Controller
@RequestMapping("UpmsAccount")
public class UpmsAccountCtl extends BaseCtl<UpmsAccount> {

	@RequestMapping(params = "act=view")
	public ModelAndView view() {
		return new ModelAndView("sys/upms/UpmsAccount");
	}

	@RequestMapping(params = "act=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("UpmsAccountCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "act=save")
	@ResponseBody
	public Object save(UpmsAccount bean, HttpServletRequest request) {
		try {
			String str = request.getParameter("eid");
			if (!npv.isNull(str)) {
				int acID = service.save(bean);
				employeeService.updateById("account.id", acID, Integer.valueOf(str));
			}
			return suc("添加成功！");
		} catch (Exception e) {
			log.error("UpmsAccountCtl保存对象异常", e);
		}
		return err("添加失败！");
	}

	@RequestMapping(params = "act=update")
	@ResponseBody
	public Object update(UpmsAccount bean, HttpServletRequest request) {
		try {
			String str = request.getParameter("eid");
			if (!npv.isNull(str)) {
				service.update(bean);
				// employeeService.updateById("account.id", bean.getId(),
				// Integer.valueOf(str));
			}
			return suc("修改成功");
		} catch (Exception e) {
			log.error("UpmsAccountCtl修改对象异常", e);
		}
		return err("修改失败");
	}

	@RequestMapping(params = "act=del")
	@ResponseBody
	public Object delete(@RequestParam(defaultValue = "0") int id) {
		try {
			service.delete(id);
			return suc("删除成功");
		} catch (Exception e) {
			log.error("UpmsAccountCtl通过ID删除对象异常", e);
		}
		return err("删除失败");
	}

	@RequestMapping(params = "act=unique")
	@ResponseBody
	public Object unique(@RequestParam(defaultValue = "0") int id, @RequestParam(defaultValue = "0") String propName,
			@RequestParam(defaultValue = "0") String propVal) {
		try {
			return unique(service, propName, propVal, id);
		} catch (Exception e) {
			log.error("UpmsAccountCtl通过唯一性查询异常", e);
		}
		return 3;
	}

	@Resource
	private IUpmsAccountService service;
	@Resource
	private IEmployeeService employeeService;

}

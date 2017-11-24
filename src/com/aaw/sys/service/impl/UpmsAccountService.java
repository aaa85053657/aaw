package com.aaw.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import cn.molos.common.SessionConstant;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.Employee;
import com.aaw.bean.FranchisesAccount;
import com.aaw.bean.UpmsAccount;
import com.aaw.sys.dao.IUpmsAccountDAO;
import com.aaw.sys.service.IEmployeeService;
import com.aaw.sys.service.IFranchisesAccountService;
import com.aaw.sys.service.IUpmsAccountService;

@Service
public class UpmsAccountService extends BaseService<UpmsAccount> implements
		IUpmsAccountService {

	@Override
	protected IUpmsAccountDAO getDAO() {
		return (IUpmsAccountDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("upmsAccountDAO") IDAO<UpmsAccount> dao) {
		super.setDAO(dao);
	}

	@Override
	public ModelAndView doLogin(HttpSession se, UpmsAccount account,
			RedirectAttributes attr, Integer type) {
		if (se.getAttribute(SessionConstant.LOGIN_USER) != null) {
			return new ModelAndView("main");
		}
		if (account == null || npv.isNull(account.getName())
				|| npv.isNull(account.getPazzwd())) {
			attr.addFlashAttribute("error", "账号/密码不可为空！");
			return new ModelAndView(new RedirectView("login"));
		}
		if (type == 1) {
			List<UpmsAccount> list = getDAO().query4Login(account);
			if (npv.isNull(list)) {
				attr.addFlashAttribute("error", "账号/密码错误");
				return new ModelAndView(new RedirectView("login"));
			} else {
				se.setAttribute(SessionConstant.LOGIN_USER, list.get(0)
						.getEmployee());
				se.setAttribute(SessionConstant.LOGIN_USER_TYPE, 1);
				return new ModelAndView("main");
			}
		} else {
			List<FranchisesAccount> list = accountService.query4Login(account);
			if (npv.isNull(list)) {
				attr.addFlashAttribute("error", "账号/密码错误");
				return new ModelAndView(new RedirectView("login"));
			} else {
				se.setAttribute(SessionConstant.LOGIN_USER, list.get(0)
						.getFranchises());
				se.setAttribute(SessionConstant.LOGIN_USER_TYPE, 2);
				return new ModelAndView("main");
			}
		}
	}

	@Override
	public ModelAndView goOut(HttpSession se) {
		se.removeAttribute(SessionConstant.LOGIN_USER);
		return new ModelAndView("login");
	}

	@Override
	public int refEmp(final int id) {
		List<UpmsAccount> list = getDAO().list(new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				put("role.id", id);
			}
		});
		return npv.isNull(list) ? 0 : 1;
	}

	@Override
	public Map<String, Object> map(int current, int size) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<UpmsAccount> data = list(current, size);
		if (npv.isNull(data)) {
			map.put("total", 0);
			map.put("rows", null);
		} else {
			int total = total();
			List<Employee> list = employeeService.list();
			for (int i = 0; i < data.size(); i++) {
				for (Employee e : list) {

					if (e != null && e.getAccount() != null
							&& data.get(i).getId() == e.getAccount().getId()) {
						data.get(i).setEmpName(e.getName());
					}
				}
			}
			map.put("total", total);
			map.put("rows", data);
		}
		return map;
	}

	@Resource
	private IEmployeeService employeeService;
	@Resource
	private IFranchisesAccountService accountService;

}
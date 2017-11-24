package com.aaw.base.service.impl;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import molos.plugins.smvc.service.imp.AbstractService;
import molos.plugins.tool.ToolFactory;
import molos.plugins.tool.validators.INPValidator;
import cn.molos.common.SessionConstant;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.Employee;

public abstract class BaseService<T> extends AbstractService<T> implements
		IBaseService<T> {

	protected INPValidator npv = ToolFactory.getNPV();

	/**
	 * 国际化本地设置
	 */
	protected Locale locale = new Locale("zh");

	/**
	 * 从session中获取员工对象
	 * 
	 * @param se
	 * @return
	 */
	protected Employee getEmployeeFromSession(HttpSession se) {
		Object obj = se.getAttribute(SessionConstant.LOGIN_USER);
		if (obj != null && obj instanceof Employee) {
			Employee e = (Employee) obj;
			return e;
		}
		return null;
	}

}

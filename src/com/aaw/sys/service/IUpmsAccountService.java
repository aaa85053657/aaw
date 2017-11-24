package com.aaw.sys.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.UpmsAccount;

public interface IUpmsAccountService extends IBaseService<UpmsAccount> {

	ModelAndView doLogin(HttpSession se, UpmsAccount account,
			RedirectAttributes attr, Integer type);

	ModelAndView goOut(HttpSession se);

	int refEmp(int id);
}
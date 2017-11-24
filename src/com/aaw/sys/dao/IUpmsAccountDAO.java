package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.UpmsAccount;

public interface IUpmsAccountDAO extends IBaseDAO<UpmsAccount> {

	/**
	 * 根据登录账户信息查询账号相关内容
	 * 
	 * @param account
	 * @return
	 */
	List<UpmsAccount> query4Login(UpmsAccount account);
}
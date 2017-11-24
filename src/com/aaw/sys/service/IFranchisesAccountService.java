package com.aaw.sys.service;

import java.util.List;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.Franchises;
import com.aaw.bean.FranchisesAccount;
import com.aaw.bean.UpmsAccount;

public interface IFranchisesAccountService extends
		IBaseService<FranchisesAccount> {

	/**
	 * 根据登录账户信息查询账号相关内容
	 * 
	 * @param account
	 * @return
	 */
	List<FranchisesAccount> query4Login(UpmsAccount account);

	/**
	 * 根据加盟商查询加盟商账户
	 * 
	 * @param account
	 * @return
	 */
	FranchisesAccount findByFra(Franchises franchises);

}
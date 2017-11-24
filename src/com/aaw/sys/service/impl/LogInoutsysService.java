package com.aaw.sys.service.impl;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.LogInoutsys;
import com.aaw.sys.dao.ILogInoutsysDAO;
import com.aaw.sys.service.ILogInoutsysService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import molos.plugins.smvc.dao.IDAO;

@Service
public class LogInoutsysService extends BaseService<LogInoutsys> implements ILogInoutsysService{

@Override
protected ILogInoutsysDAO getDAO() {
return (ILogInoutsysDAO) super.getDAO();
}

@Autowired
@Override
protected void setDAO(@Qualifier("logInoutsysDAO") IDAO<LogInoutsys> dao) {
super.setDAO(dao);
}
}
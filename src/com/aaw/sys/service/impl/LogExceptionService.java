package com.aaw.sys.service.impl;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.LogException;
import com.aaw.sys.dao.ILogExceptionDAO;
import com.aaw.sys.service.ILogExceptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import molos.plugins.smvc.dao.IDAO;

@Service
public class LogExceptionService extends BaseService<LogException> implements ILogExceptionService{

@Override
protected ILogExceptionDAO getDAO() {
return (ILogExceptionDAO) super.getDAO();
}

@Autowired
@Override
protected void setDAO(@Qualifier("logExceptionDAO") IDAO<LogException> dao) {
super.setDAO(dao);
}
}
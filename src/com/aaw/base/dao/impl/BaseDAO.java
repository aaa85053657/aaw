package com.aaw.base.dao.impl;

import molos.plugins.smvc.dao.imp.AbstractDAO;
import molos.plugins.tool.ToolFactory;
import molos.plugins.tool.validators.INPValidator;

import com.aaw.base.dao.IBaseDAO;

public abstract class BaseDAO<T> extends AbstractDAO<T> implements IBaseDAO<T> {
	/**
	 * 空指针校验对象
	 */
	protected INPValidator npv = ToolFactory.getNPV();
}

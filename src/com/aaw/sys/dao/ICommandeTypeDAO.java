package com.aaw.sys.dao;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.CommandeType;

public interface ICommandeTypeDAO extends IBaseDAO<CommandeType> {

	boolean deleteAndCheck(int id);
}
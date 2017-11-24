package com.aaw.sys.dao;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.CommandePriority;

public interface ICommandePriorityDAO extends IBaseDAO<CommandePriority> {

	boolean deleteAndCheck(int id);
}
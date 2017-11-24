package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.CommandeStatus;

public interface ICommandeStatusDAO extends IBaseDAO<CommandeStatus> {

	List<CommandeStatus> queryListByMID(int id);

	void initSt(int id);
}
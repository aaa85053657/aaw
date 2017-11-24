package com.aaw.sys.service;

import java.util.List;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.CommandeStatus;

public interface ICommandeStatusService extends IBaseService<CommandeStatus>{

	List<CommandeStatus> queryListByMID(int id);

	void initSt(int id);
}
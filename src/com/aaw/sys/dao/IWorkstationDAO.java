package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.MetaProcedure;
import com.aaw.bean.Workstation;

public interface IWorkstationDAO extends IBaseDAO<Workstation> {

	List<MetaProcedure> listCfg(int id);

}

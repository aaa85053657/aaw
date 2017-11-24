package com.aaw.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.MetaProcedure;
import com.aaw.bean.Workstation;
import com.aaw.bean.WorkstationProcedure;
import com.aaw.sys.dao.IWorkstationDAO;
import com.aaw.sys.service.IWorkstationProcedureService;
import com.aaw.sys.service.IWorkstationService;

@Service
public class WorkstationService extends BaseService<Workstation> implements
		IWorkstationService {

	@Override
	protected IWorkstationDAO getDAO() {
		return (IWorkstationDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("workstationDAO") IDAO<Workstation> dao) {
		super.setDAO(dao);
	}

	@Override
	public void saveCfg(int[] sequenceNum, int[] procedure, int id) {
		for (int i = 0; i < procedure.length; i++) {
			WorkstationProcedure bean = new WorkstationProcedure(id,
					sequenceNum[i], procedure[i]);
			workstationProcedureService.save(bean);
		}
	}

	@Override
	public List<MetaProcedure> listCfg(int id) {
		return getDAO().listCfg(id);
	}

	@Resource
	private IWorkstationProcedureService workstationProcedureService;

}

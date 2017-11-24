package com.aaw.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.MetaProcedure;
import com.aaw.bean.ProductionLine;
import com.aaw.bean.Profile;
import com.aaw.bean.SlaveCommande;
import com.aaw.bean.Worksheet;
import com.aaw.bean.WorksheetDetail;
import com.aaw.bean.Workstation;
import com.aaw.sys.dao.IWorksheetDAO;
import com.aaw.sys.form.WorkingForm;
import com.aaw.sys.service.IMetaProcedureService;
import com.aaw.sys.service.IProfileService;
import com.aaw.sys.service.ISlaveCommandeService;
import com.aaw.sys.service.IWorksheetDetailService;
import com.aaw.sys.service.IWorksheetService;

@Service
public class WorksheetService extends BaseService<Worksheet> implements
		IWorksheetService {
	@Override
	protected IWorksheetDAO getDAO() {
		return (IWorksheetDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("worksheetDAO") IDAO<Worksheet> dao) {
		super.setDAO(dao);
	}

	@Override
	public int save(Worksheet t) {
		int wsID = getDAO().save(t);// 保存加工单
		// 创建加工单对应操作数据worksheetDetail
		SlaveCommande sc = slaveCommandeService.query(t.getSlaveCommande()
				.getId());
		ProductionLine line = sc.getModel().getLine();
		List<MetaProcedure> metaList = metaProcedureService.listByLineID(line
				.getId());

		for (MetaProcedure mp : metaList) {
			int mid = worksheetDetailService.save(new WorksheetDetail(wsID, mp
					.getId(), mp.getSequenceNum(), 0, 1, ""));
			List<Profile> pList = profileService.listByMetaID(mp.getId());
			for (Profile profile : pList) {
				worksheetDetailService.save(new WorksheetDetail(wsID, mp
						.getId(), profile.getId(), profile.getPriority(), mid,
						1, ""));
			}
		}
		return wsID;
	}

	public Map<String, Object> save4Create(Worksheet t) {
		Map<String, Object> map = new HashMap<String, Object>();
		int wsID = getDAO().save(t);// 保存加工单
		// 创建加工单对应操作数据worksheetDetail
		SlaveCommande sc = slaveCommandeService.query(t.getSlaveCommande()
				.getId());
		ProductionLine line = sc.getModel().getLine();
		List<MetaProcedure> metaList = metaProcedureService.listByLineID(line
				.getId());
		int size = metaList.size();
		for (MetaProcedure mp : metaList) {
			int mid = worksheetDetailService.save(new WorksheetDetail(wsID, mp
					.getId(), mp.getSequenceNum(), 0, 1, ""));
			List<Profile> pList = profileService.listByMetaID(mp.getId());
			for (Profile profile : pList) {
				worksheetDetailService.save(new WorksheetDetail(wsID, mp
						.getId(), profile.getId(), profile.getPriority(), mid,
						1, ""));
			}
		}
		map.put("id", wsID);// worksheet ID
		map.put("sz", size);// 总工序
		return map;
	}

	@Override
	public boolean deleteAndCheck(int id) {
		return getDAO().deleteAndCheck(id);
	}

	@Override
	public void updateTask(WorkingForm form) {
		getDAO().updateTask(form);
	}

	@Override
	public void endTask(WorkingForm form) {
		getDAO().endTask(form);

	}

	@Override
	public void updateDelSlave(int id) {
		getDAO().updateDelSlave(id);
	}

	@Override
	public Object uniqueCommande(String propVal) {
		return getDAO().uniqueCommande(propVal);
	}

	@Override
	public Object findByCode(String id, int type, Workstation workstation) {
		return getDAO().findByCode(id, type, workstation);
	}

	@Override
	public Object findByCode2(int id, String code, int type,
			List<Workstation> wks) {
		return getDAO().findByCode2(id, code, type, wks.get(0));
	}

	@Override
	public Object findWorking(Workstation workstation) {
		return getDAO().findWorking(workstation);
	}

	@Override
	public Object updateEMP(int did, int eid) {
		return getDAO().updateEMP(did, eid);
	}

	@Override
	public WorksheetDetail findDeatilByCod(int wid) {
		return getDAO().findDeatilByCod(wid);
	}

	@Override
	public Object clickWork(int wid, Workstation workstation) {
		return getDAO().clickWork(wid, workstation);
	}

	@Resource
	private IWorksheetDetailService worksheetDetailService;
	@Resource
	private ISlaveCommandeService slaveCommandeService;
	@Resource
	private IMetaProcedureService metaProcedureService;
	@Resource
	private IProfileService profileService;

}

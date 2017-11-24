package com.aaw.sys.dao;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.Worksheet;
import com.aaw.bean.WorksheetDetail;
import com.aaw.bean.Workstation;
import com.aaw.sys.form.WorkingForm;

public interface IWorksheetDAO extends IBaseDAO<Worksheet> {

	void updateTask(WorkingForm form);

	void endTask(WorkingForm form);

	Object uniqueCommande(String propVal);

	void updateDelSlave(int id);

	Object findByCode(String id, int type, Workstation workstation);

	Object findByCode2(int id, String code, int type, Workstation workstation);

	Object findWorking(Workstation workstation);

	Object updateEMP(int did, int eid);

	WorksheetDetail findDeatilByCod(int wid);

	Object clickWork(int wid, Workstation workstation);

	boolean deleteAndCheck(int id);

}

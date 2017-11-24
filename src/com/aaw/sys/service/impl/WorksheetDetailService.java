package com.aaw.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import molos.plugins.smvc.dao.IDAO;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.WorksheetDetail;
import com.aaw.bean.WorksheetHistory;
import com.aaw.sys.dao.IWorksheetDetailDAO;
import com.aaw.sys.form.WorkingForm;
import com.aaw.sys.service.IWorksheetDetailService;

@Service
public class WorksheetDetailService extends BaseService<WorksheetDetail>
		implements IWorksheetDetailService {
	@Override
	protected IWorksheetDetailDAO getDAO() {
		return (IWorksheetDetailDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("worksheetDetailDAO") IDAO<WorksheetDetail> dao) {
		super.setDAO(dao);
	}

	@Override
	public List<WorksheetDetail> queryListByWorksheetID(int wid, int type,
			int order) {
		List<WorksheetDetail> list = getDAO().queryListByWorksheetID(wid, type,
				order);
		for (WorksheetDetail w : list) {
			if (w.getStatus() == 1 || w.getStatus() == 4 || w.getStatus() == 9) {
				w.setFlag(1);
				break;
			}
		}
		return list;
	}

	@Override
	public List<WorksheetDetail> queryListByPID(int pid, int order) {
		return getDAO().queryListByPID(pid, order);
	}

	@Override
	public WorksheetHistory updateTask(WorkingForm form) {
		return getDAO().updateTask(form);

	}

	@Override
	public void endTask(int wid) {
		getDAO().endTask(wid);

	}

	@Override
	public Object queryListByWorksheetDetailID(int wid) {
		return getDAO().queryListByWorksheetID(wid);
	}

	@Override
	public List<WorksheetDetail> findById(int rid) {
		return getDAO().findById(rid);
	}

	@Override
	public Map<String, Object> queryList(int page, int rows, Integer integer) {
		return getDAO().queryList(page, rows, integer);
	}

	@Override
	public List<WorksheetDetail> queryListByWork(int id) {
		return getDAO().queryListByWork(id);
	}

	@Override
	public void updateStatus(int id, int i) {
		getDAO().updateStatus(id, i);

	}

	@Override
	public Map<String, Object> searchMore(int page, int rows, int pid,
			Integer integer) {
		return getDAO().searchMore(page, rows, pid, integer);
	}

	@Override
	public void updateStatus(Date date) {
		getDAO().updateStatus(date);

	}

	@Override
	public List<WorksheetDetail> findByStatus(int i) {
		return getDAO().findByStatus(i);
	}

	@Override
	public Map<String, Object> getWorkSheetList(int page, int rows, Integer id) {
		return getDAO().getWorkSheetList(page, rows, id);
	}

	@Override
	public boolean checkExecute(int worksheetDetailId) {
		return getDAO().checkExecute(worksheetDetailId);
	}

	@Override
	public void updateTaskTemp(WorkingForm form) {
		getDAO().updateTaskTemp(form);

	}

	public void updateIsRead(int id, int i) {
		getDAO().updateIsRead(id, i);

	}

	@Override
	public List<WorksheetDetail> findOverTime(int wid) {
		return getDAO().findOverTime(wid);
	}

	@Override
	public List<WorksheetDetail> findEnd(int wid) {
		return getDAO().findEnd(wid);
	}
}

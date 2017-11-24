package com.aaw.sys.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.WorksheetDetail;
import com.aaw.bean.WorksheetHistory;
import com.aaw.sys.form.WorkingForm;

public interface IWorksheetDetailDAO extends IBaseDAO<WorksheetDetail> {

	List<WorksheetDetail> queryListByWorksheetID(int wid, int type, int order);

	List<WorksheetDetail> queryListByPID(int pid, int order);

	WorksheetHistory updateTask(WorkingForm form);

	void endTask(int wid);

	Object queryListByWorksheetID(int wid);

	List<WorksheetDetail> findById(int rid);

	Map<String, Object> queryList(int page, int rows, Integer integer);

	List<WorksheetDetail> queryListByWork(int id);

	void updateStatus(int id, int i);

	Map<String, Object> searchMore(int page, int rows, int pid, Integer integer);

	void updateStatus(Date date);

	List<WorksheetDetail> findByStatus(int i);

	Map<String, Object> getWorkSheetList(int page, int rows, Integer id);

	boolean checkExecute(int worksheetDetailId);

	void updateTaskTemp(WorkingForm form);

	void updateIsRead(int id, int i);

	List<WorksheetDetail> findOverTime(int wid);

	List<WorksheetDetail> findEnd(int wid);

}

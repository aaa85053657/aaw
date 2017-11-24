package com.aaw.sys.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.WorksheetDetail;
import com.aaw.bean.WorksheetHistory;
import com.aaw.sys.form.WorkingForm;

public interface IWorksheetDetailService extends IBaseService<WorksheetDetail> {

	/**
	 * @param wid
	 *            worksheet ID
	 * @param type
	 *            类别 1，为查询MetaProcedure,2 Profile
	 * @param order
	 *            1为 asc/升序； 2为desc/降序
	 * @return
	 */
	List<WorksheetDetail> queryListByWorksheetID(int wid, int type, int order);

	/**
	 * @param pid
	 *            父ID
	 * @param order
	 *            1为 asc/升序； 2为desc/降序
	 * @return
	 */
	List<WorksheetDetail> queryListByPID(int pid, int order);

	/**
	 * 更新加工单状态
	 * 
	 * @param form
	 */
	WorksheetHistory updateTask(WorkingForm form);

	/**
	 * 保存临时记录
	 * 
	 * @param form
	 */
	void updateTaskTemp(WorkingForm form);

	/**
	 * 终止加工单
	 * 
	 * @param form
	 */
	void endTask(int wid);

	Object queryListByWorksheetDetailID(int wid);

	List<WorksheetDetail> findById(int rid);

	Map<String, Object> queryList(int page, int rows, Integer integer);

	List<WorksheetDetail> queryListByWork(int id);

	/**
	 * @param id
	 *            加工单详情 id
	 * @param i
	 *            状态
	 */
	void updateStatus(int id, int i);

	Map<String, Object> searchMore(int page, int rows, int pid, Integer integer);

	void updateStatus(Date date);

	List<WorksheetDetail> findByStatus(int i);

	Map<String, Object> getWorkSheetList(int page, int rows, Integer id);

	/**
	 * 验证是否可以执行 保存，回退等功能
	 * 
	 * @param worksheetDetailId
	 * @return
	 */
	boolean checkExecute(int worksheetDetailId);

	void updateIsRead(int id, int i);

	/**
	 * 查看时候已经超时回滚
	 * @param wid
	 * @return
	 */
	List<WorksheetDetail> findOverTime(int wid);

	/**
	 * 查询终止的订单
	 * @param wid
	 * @return
	 */
	List<WorksheetDetail> findEnd(int wid);

}

package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.Worksheet;
import com.aaw.bean.WorksheetDetail;
import com.aaw.bean.Workstation;
import com.aaw.sys.form.WorkingForm;

public interface IWorksheetService extends IBaseService<Worksheet> {

	boolean deleteAndCheck(int id);

	/**
	 * 更新加工单状态
	 * 
	 * @param form
	 */
	void updateTask(WorkingForm form);

	void endTask(WorkingForm form);

	Object uniqueCommande(String propVal);

	void updateDelSlave(int id);

	/**
	 * @param id
	 *            查询编号
	 * @param type
	 *            类型
	 * @return
	 */
	Object findByCode(String id, int type, Workstation workstation);

	/**
	 * 第二次查找
	 * 
	 * @param id
	 * @param code
	 * @param type
	 * @param wks
	 * @return
	 */
	Object findByCode2(int id, String code, int type, List<Workstation> wks);

	/**
	 * 查找正在加工的商品
	 * 
	 * @param workstation
	 * 
	 * @return
	 */
	Object findWorking(Workstation workstation);

	/**
	 * 修改opr
	 * 
	 * @param did
	 * @param eid
	 * @return
	 */
	Object updateEMP(int did, int eid);

	/**
	 * 更具id查找
	 * 
	 * @param wid
	 * @return
	 */
	WorksheetDetail findDeatilByCod(int wid);

	/**
	 * 验证是否可以跳转
	 * 
	 * @param wid
	 * @param workstation
	 * @return
	 */
	Object clickWork(int wid, Workstation workstation);

	/**
	 * 保存worksheet 并返回ID 和工序总数，
	 * 
	 * @param t
	 *            需要保存的工序
	 * @return
	 */
	Map<String, Object> save4Create(Worksheet t);
}

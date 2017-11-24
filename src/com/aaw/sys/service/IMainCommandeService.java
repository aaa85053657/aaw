package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.Franchises;
import com.aaw.bean.MainCommande;
import com.aaw.sys.form.CommandeCfg;
import com.aaw.sys.form.MDConditions;

public interface IMainCommandeService extends IBaseService<MainCommande> {

	boolean deleteAndCheck(int id);

	void saveCfg(CommandeCfg bean);

	Map<String, Object> cfgList(int id);

	void initStatusData(int id);

	List<Map<String, Object>> stList(int id);

	/**
	 * 订单状态变更方法
	 * 
	 * @param mid
	 *            订单号
	 * @param id
	 *            状态ID
	 * @param st
	 *            状态值,0、执行中 1、 成功、2、失败
	 * @param string
	 * @return
	 */
	boolean saveStChange(int mid, int id, int st, String string);

	void initSt(int id);

	List<Map<String, Object>> combobox();

	/**
	 * 需要显示的CODEID
	 * 
	 * @return
	 */
	String showCodeId();

	String showCodeId2();

	/**
	 * 是否已经存在副单，存在为true
	 * 
	 * @param id
	 *            主单ID
	 * @return
	 */
	boolean hasSalve(int id);

	/**
	 * 条件查询
	 * 
	 * @param page
	 *            当前页号
	 * @param rows
	 *            记录数
	 * @param cc
	 *            条件对象
	 * @param i 
	 * @return
	 */
	Object map(int page, int rows, MDConditions cc, int i);

	
	/**
	 * 加盟商 条件查询
	 * 
	 * @param page
	 *            当前页号
	 * @param rows
	 *            记录数
	 * @param cc
	 *            条件对象
	 * @return
	 */
	Object map2(int page, int rows, MDConditions cc, List<Franchises> temp);

	/**
	 * 是否可以直接重置 是否有加工单
	 * 
	 * @param id
	 * @return
	 */
	int isInit(int id);

	/**
	 * 是否可以直接重置 是否有副单
	 * 
	 * @param id
	 * @return
	 */
	int isInit2(int id);

	void updateCode(String code);

	void updateCode2(String code);

	void initStatusData2(int id, Integer id2);

}
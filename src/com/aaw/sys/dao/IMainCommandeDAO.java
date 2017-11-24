package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.CommandeStatus;
import com.aaw.bean.CommandeStatusTemp;
import com.aaw.bean.FactoryStatus;
import com.aaw.bean.FranchisesCommandeRelation;
import com.aaw.bean.MainCommande;
import com.aaw.bean.SlaveCommande;
import com.aaw.sys.form.MDConditions;

public interface IMainCommandeDAO extends IBaseDAO<MainCommande> {

	boolean deleteAndCheck(int id);

	List<SlaveCommande> querySlaveByMainID(int id);

	void initStatusData(int id, List<CommandeStatusTemp> temp);

	/**
	 * 更改订单状态
	 * 
	 * @param mid
	 *            订单ID
	 * @param cst
	 *            当前被更改的状态的ID
	 * @param st
	 *            修改的状态
	 */
	void saveStChange(int mid, CommandeStatus cst, int st);

	String codeId();

	/**
	 * 需要显示的CODE ID
	 * 
	 * @return
	 */
	String showCodeID();

	String showCodeID2();

	/**
	 * @param cc
	 * @return
	 */
	int total(MDConditions cc);

	int total2(MDConditions cc, List<Integer> mainTemp);

	/**
	 * @param page
	 * @param rows
	 * @param cc
	 * @param i 
	 * @return
	 */
	List<MainCommande> list(int page, int rows, MDConditions cc, int i);

	List<MainCommande> list2(int page, int rows, MDConditions cc,
			List<Integer> mainTemp);

	List<SlaveCommande> listSCList(List<MainCommande> list);

	List<FactoryStatus> listFSList(List<MainCommande> list);

	int isInit(int id);

	int isInit2(int id);

	/**
	 * 根据订单当前状态获取当前加工流程名称
	 * 
	 * @param status
	 * @param i
	 * @return
	 */
	String findByCol7(int sid, int status);

	void codeId2();

	/**
	 * 获取订单关联的加盟商
	 * 
	 * @param m
	 * @param i
	 * @return
	 */
	FranchisesCommandeRelation findFCR(MainCommande m);

	/**
	 * 获取该加盟商的订单
	 * 
	 * @param is
	 * @return
	 */
	List<Integer> listByFran(List<Integer> is);

}
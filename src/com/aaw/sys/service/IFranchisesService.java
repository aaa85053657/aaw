package com.aaw.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.Franchises;
import com.aaw.bean.FranchisesAccount;
import com.aaw.bean.FranchisesAccountAssets;
import com.aaw.bean.FranchisesAssets;
import com.aaw.sys.form.FrandConditions;

public interface IFranchisesService extends IBaseService<Franchises> {

	/**
	 * 根据账户验证权限
	 * 
	 * @param account
	 * @param as
	 */
	boolean clickAssets(FranchisesAccount account, int as);

	/**
	 * 需要显示的CODEID
	 * 
	 * @return
	 */
	String showCodeId();

	/**
	 * 更新编号
	 * 
	 * @param code
	 */
	void updateCode(String code);

	/**
	 * 查询子孙级别
	 * 
	 * @param i
	 * @return
	 */
	List<Integer> findAllLevel(int i);

	/**
	 * 条件查询
	 * 
	 * @param page
	 * @param rows
	 * @param c
	 *            查询条件
	 * @param id
	 *            父id
	 * @return
	 */
	Object map4EUI(int page, int rows, FrandConditions c, Integer id);

	/**
	 * 查询加盟商资源
	 * 
	 * @return
	 */
	List<FranchisesAssets> findAllAssets();

	/**
	 * 根据加盟商查询加盟商资源
	 * 
	 * @return
	 */
	List<FranchisesAccountAssets> findAssetsByFran(Integer fid);

	/**
	 * 删除权限
	 * 
	 * @return
	 */
	void deleteAccAss(FranchisesAccount bean);

	/**
	 * 获取加盟商combobox
	 * 
	 * @param franchises
	 * @param level
	 * @return
	 */
	List<Map<String, Object>> combobox2(Franchises franchises, Integer level);

	/**
	 * 获取加盟商
	 * 
	 * @param id
	 * @return
	 */
	List<Franchises> findByFran(Franchises franchises);

	ArrayList<List<Object>> getList(List<Franchises> temp);

	/**
	 * 查询所有加盟商编号
	 * 
	 * @return
	 */
	List<Object> findAllCode();

	/**
	 * 根据编号查找
	 * 
	 * @return
	 */
	Franchises findByCode(String code);

	FranchisesAccount findAccByFran(Franchises f);

	/**
	 * 判断需要自动输入
	 * 
	 * @return
	 */
	boolean clickInput();

	List<Map<String, Object>> getTree(Franchises fran);

}
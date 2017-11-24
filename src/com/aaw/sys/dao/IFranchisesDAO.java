package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.Franchises;
import com.aaw.bean.FranchisesAccount;
import com.aaw.bean.FranchisesAccountAssets;
import com.aaw.bean.FranchisesAssets;

public interface IFranchisesDAO extends IBaseDAO<Franchises> {

	boolean clickAssets(FranchisesAccount account, int as);

	String showCodeID();

	String codeId();

	/**
	 * 根据父id查找子集
	 * 
	 * @param i
	 * @return
	 */
	List<Franchises> findByParent(int i);

	List<FranchisesAssets> findAllAssets();

	List<FranchisesAccountAssets> findAssetsByFran(Integer fid);

	void deleteAccAss(FranchisesAccount bean);

	FranchisesAccount findAccByFran(Franchises franchises);

	List<FranchisesAccountAssets> findAssetsByFranAcc(Integer id);

	List<Object> findAllCode();

	Franchises findByCode(String code);

	boolean clickInput();

}
package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.SlaveCommande;

public interface ISlaveCommandeDAO extends IBaseDAO<SlaveCommande> {

	List<SlaveCommande> list(int id);

	boolean delCheck(int id);
}
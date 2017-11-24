package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.MetaProcedure;
import com.aaw.bean.Profile;

public interface IMetaProcedureDAO extends IBaseDAO<MetaProcedure> {

	boolean deleteAndCheck(int id);

	List<Profile> listProfile(int id);

	List<MetaProcedure> combobox(int id);

	List<MetaProcedure> all();

	List<MetaProcedure> all(List<Integer> integers);

}
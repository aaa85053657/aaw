package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.Profile;

public interface IProfileDAO extends IBaseDAO<Profile> {

	boolean deleteAndCheck(int id);

	List<Profile> combobox(int id);
}
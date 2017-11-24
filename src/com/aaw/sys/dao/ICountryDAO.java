package com.aaw.sys.dao;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.Country;

public interface ICountryDAO extends IBaseDAO<Country> {

	boolean deleteAndCheck(int id);
}
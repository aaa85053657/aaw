package com.aaw.sys.dao;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.ProfileType;

public interface IProfileTypeDAO extends IBaseDAO<ProfileType> {

	boolean deleteAndCheck(int id);
}
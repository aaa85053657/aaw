package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.UpmsRole;

public interface IUpmsRoleDAO extends IBaseDAO<UpmsRole> {

	void saveRelation(int id, Integer[] rids);

	List<Integer> relations(int F);

	List<Integer> resourcesId(int id);

	void unbind(int id);
}
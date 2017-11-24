package com.aaw.sys.service;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.UpmsRole;

public interface IUpmsRoleService extends IBaseService<UpmsRole> {

	void saveRelation(int id, Integer[] rids);

	Object findBind(int id);

	void bind(int id, Integer[] rids);
}
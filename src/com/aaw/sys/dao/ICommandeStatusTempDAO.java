package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.CommandeStatusTemp;

public interface ICommandeStatusTempDAO extends IBaseDAO<CommandeStatusTemp> {

	boolean hasStartNode();

	void updatePreNode(Integer previousId, int id);

	void updateChidNum(Integer parentId);

	void delChildren(int id, int cNum);

	List<CommandeStatusTemp> findByGID(Integer gid);

	boolean uniqueByGID(String propName, String propVal, int id, int gid);

}

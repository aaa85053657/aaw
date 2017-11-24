package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.CommandeStatusTemp;

public interface ICommandeStatusTempService extends
		IBaseService<CommandeStatusTemp> {

	List<Map<String, Object>> treeList(Integer gid);

	boolean hasStartNode();

	void updatePreNode(Integer previousId, int id);

	void updateChidNum(Integer parentId);

	void delChildren(int id, int cNum);

	Object uniqueByGID(ICommandeStatusTempService service, String propName,
			String propVal, int id, int gid);

	List<CommandeStatusTemp> findByGID(Integer gid);

}

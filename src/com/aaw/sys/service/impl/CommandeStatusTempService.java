package com.aaw.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import molos.plugins.smvc.dao.IDAO;
import molos.plugins.tool.changer.TypeConver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.CommandeStatusTemp;
import com.aaw.sys.dao.ICommandeStatusTempDAO;
import com.aaw.sys.service.ICommandeStatusTempService;

@Service
public class CommandeStatusTempService extends BaseService<CommandeStatusTemp>
		implements ICommandeStatusTempService {

	@Override
	protected ICommandeStatusTempDAO getDAO() {
		return (ICommandeStatusTempDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(
			@Qualifier("commandeStatusTempDAO") IDAO<CommandeStatusTemp> dao) {
		super.setDAO(dao);
	}

	@Override
	public List<Map<String, Object>> treeList(Integer gid) {
		List<CommandeStatusTemp> list = getDAO().findByGID(gid);
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		if (!npv.isNull(list)) {
			for (CommandeStatusTemp c : list) {
				if (c.getParentId() == 0) {
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("id", c.getId());
					m.put("name", c.getName());
					m.put("isSendMail", c.getIsSendMail());
					m.put("comments", c.getComments());
					m.put("hasChildren", c.getHasChildren());
					m.put("nextId", c.getNextId());
					m.put("parentId", c.getParentId());
					m.put("previousId", c.getPreviousId());
					m.put("children", children(c, list));
					reList.add(m);
				}
			}
		}
		return sortRs(reList);
	}

	private List<Map<String, Object>> children(CommandeStatusTemp c,
			List<CommandeStatusTemp> list) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		for (CommandeStatusTemp c1 : list) {
			if (c1.getParentId() == c.getId()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", c1.getId());
				m.put("name", c1.getName());
				m.put("isSendMail", c1.getIsSendMail());
				m.put("comments", c1.getComments());
				m.put("hasChildren", c1.getHasChildren());
				m.put("nextId", c1.getNextId());
				m.put("parentId", c1.getParentId());
				m.put("previousId", c1.getPreviousId());
				m.put("children", children(c1, list));
				reList.add(m);
			}
		}
		return sortRs(reList);
	}

	private List<Map<String, Object>> sortRs(List<Map<String, Object>> list) {
		TypeConver t = new TypeConver();
		List<Map<String, Object>> rList = new ArrayList<Map<String, Object>>();
		Map<String, Object> temp = null;
		for (Map<String, Object> m : list) {
			if (t.toInt(m.get("previousId")) == 0) {
				rList.add(m);
				temp = m;
				list.remove(m);
				break;
			}
		}
		while (true) {
			if (list.size() > 0) {
				for (Map<String, Object> map : list) {
					if (t.toInt(temp.get("nextId")) == t.toInt(map.get("id"))) {
						rList.add(map);
						temp = map;
						list.remove(map);
						break;
					}
				}
			} else {
				break;
			}
		}
		return rList;
	}

	@Override
	public boolean hasStartNode() {
		return getDAO().hasStartNode();
	}

	@Override
	public void updatePreNode(Integer previousId, int id) {
		getDAO().updatePreNode(previousId, id);
	}

	@Override
	public void updateChidNum(Integer parentId) {
		getDAO().updateChidNum(parentId);
	}

	@Override
	public void delChildren(int id, int cNum) {
		getDAO().delChildren(id, cNum);
	}

	@Override
	public Object uniqueByGID(ICommandeStatusTempService service,
			String propName, String propVal, int id, int gid) {
		if (getDAO().uniqueByGID(propName, propVal, id, gid)) {
			return 1;
		}
		return 2;
	}

	@Override
	public List<CommandeStatusTemp> findByGID(Integer gid) {
		return getDAO().findByGID(gid);
	}
}
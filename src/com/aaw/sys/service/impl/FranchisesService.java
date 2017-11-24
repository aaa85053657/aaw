package com.aaw.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import molos.plugins.smvc.dao.IDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.Franchises;
import com.aaw.bean.FranchisesAccount;
import com.aaw.bean.FranchisesAccountAssets;
import com.aaw.bean.FranchisesAssets;
import com.aaw.sys.dao.IFranchisesDAO;
import com.aaw.sys.form.FrandConditions;
import com.aaw.sys.service.IFranchisesService;

@Service
public class FranchisesService extends BaseService<Franchises> implements
		IFranchisesService {

	@Override
	protected IFranchisesDAO getDAO() {
		return (IFranchisesDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("franchisesDAO") IDAO<Franchises> dao) {
		super.setDAO(dao);
	}

	@Override
	public boolean clickAssets(FranchisesAccount account, int as) {
		return getDAO().clickAssets(account, as);
	}

	@Override
	public String showCodeId() {
		return getDAO().showCodeID();
	}

	@Override
	public void updateCode(String code) {
		getDAO().codeId();

	}

	@Override
	public List<Integer> findAllLevel(int i) {
		List<Integer> list = new ArrayList<Integer>();
		return getLevels(i, list);
	}

	private List<Integer> getLevels(int i, List<Integer> list) {
		List<Franchises> temp = getDAO().findByParent(i);
		if (temp != null && !temp.isEmpty()) {
			list.add(temp.get(0).getFranchiseLevel());
			for (Franchises f : temp) {
				getLevels(f.getId(), list);
			}
		}
		List<Integer> results = new ArrayList<Integer>(new HashSet<Integer>(
				list));
		return results;
	}

	@Override
	public Object map4EUI(int page, int rows, FrandConditions c, Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Franchises> temp = listMap4EUI(c, id);
		map.put("total", (page * rows));
		if (temp.size() > (page * rows)) {
			map.put("rows", temp.subList(((page - 1) * rows), (page * rows)));
		} else {
			map.put("rows", temp.subList(((page - 1) * rows), temp.size()));
		}
		return map;
	}

	private List<Franchises> listMap4EUI(FrandConditions c, Integer id) {
		List<Franchises> temp = new ArrayList<Franchises>();
		List<Franchises> list = new ArrayList<Franchises>();
		getAllList(id, temp);
		if (c.getLevel() != null) {
			for (Franchises f : temp) {
				if (f.getFranchiseLevel() != c.getLevel()) {
					list.add(f);
				}
			}
		}
		temp.removeAll(list);
		list.clear();
		if (c.getFname() != null) {
			for (Franchises f : temp) {
				if (f.getName().indexOf(c.getFname()) == -1) {
					list.add(f);
				}
			}
		}
		temp.removeAll(list);
		return temp;
	}

	private List<Franchises> getAllList(Integer id, List<Franchises> list) {
		List<Franchises> temp = getDAO().findByParent(id);
		if (temp != null && !temp.isEmpty()) {
			for (Franchises f : temp) {
				if (f.getState() != 3) {
					list.add(f);
				}
				getAllList(f.getId(), list);
			}
		}
		return list;
	}

	@Override
	public int save(Franchises t) {
		boolean flag = getDAO().clickInput();
		if (!flag) {
			t.setCode(getDAO().codeId());
		}
		return super.save(t);
	}

	@Override
	public List<FranchisesAssets> findAllAssets() {
		return getDAO().findAllAssets();
	}

	@Override
	public List<FranchisesAccountAssets> findAssetsByFran(Integer fid) {
		return getDAO().findAssetsByFran(fid);
	}

	@Override
	public void deleteAccAss(FranchisesAccount bean) {
		getDAO().deleteAccAss(bean);
	}

	@Override
	public List<Map<String, Object>> combobox2(Franchises franchises,
			Integer level) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		int id = 0;
		if (franchises != null) {
			id = franchises.getId();
			if (level == 0 || franchises.getFranchiseLevel() == level) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", franchises.getId());
				map.put("text", franchises.getName());
				reList.add(map);
			}

		}
		List<Franchises> list = new ArrayList<Franchises>();
		getAllList(id, list);
		for (Franchises a : list) {
			if (level == 0 || a.getFranchiseLevel() == level) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", a.getId());
				map.put("text", a.getName());
				reList.add(map);
			}
		}
		return reList;
	}

	@Override
	public List<Franchises> findByFran(Franchises franchises) {
		List<Franchises> list = new ArrayList<Franchises>();
		list.add(franchises);
		getAllList2(franchises.getId(), list);
		return list;
	}

	private List<Franchises> getAllList2(Integer id, List<Franchises> list) {
		List<Franchises> temp = getDAO().findByParent(id);
		if (temp != null && !temp.isEmpty()) {
			for (Franchises f : temp) {
				list.add(f);
				getAllList2(f.getId(), list);
			}
		}
		return list;
	}

	@Override
	public ArrayList<List<Object>> getList(List<Franchises> temp) {
		ArrayList<List<Object>> list = new ArrayList<List<Object>>();
		for (Franchises f : temp) {
			ArrayList<Object> objs = new ArrayList<Object>();
			objs.add(f.getCode());
			objs.add(f.getName());
			objs.add(f.getDescription());
			objs.add(f.getAddress());
			objs.add(f.getContactName());
			objs.add(f.getContactNumber());
			objs.add(f.getContactEmail());
			objs.add(f.getState());
			objs.add(f.getFranchiseLevel());
			objs.add(f.getCategory());
			if (f.getParentId() == 0) {
				objs.add("0");
			} else {
				Franchises franchises = getDAO().query(f.getParentId());
				objs.add(franchises.getCode());
			}
			objs.add(f.getContactPhone());
			FranchisesAccount fa = getDAO().findAccByFran(f);
			if (fa == null) {
				objs.add("");
				objs.add("");
				objs.add("");
				objs.add("");
				objs.add("");
				objs.add("");
			} else {
				objs.add(fa.getAccount());
				objs.add(fa.getPazzword());
				List<FranchisesAccountAssets> faas = getDAO()
						.findAssetsByFranAcc(fa.getId());
				if (faas == null) {
					objs.add("0");
					objs.add("0");
					objs.add("0");
					objs.add("0");
				} else {
					List<Integer> is = new ArrayList<Integer>();
					for (FranchisesAccountAssets faa : faas) {
						is.add(faa.getAssets().getId());
					}
					if (is.contains(1)) {
						objs.add("1");
					} else {
						objs.add("0");
					}
					if (is.contains(2)) {
						objs.add("1");
					} else {
						objs.add("0");
					}
					if (is.contains(3)) {
						objs.add("1");
					} else {
						objs.add("0");
					}
					if (is.contains(4)) {
						objs.add("1");
					} else {
						objs.add("0");
					}
				}
			}
			list.add(objs);
		}
		return list;
	}

	@Override
	public List<Object> findAllCode() {
		return getDAO().findAllCode();
	}

	@Override
	public Franchises findByCode(String code) {
		return getDAO().findByCode(code);
	}

	@Override
	public FranchisesAccount findAccByFran(Franchises f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean clickInput() {
		return getDAO().clickInput();
	}

	@Override
	public List<Map<String, Object>> getTree(Franchises fran) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int parentId = 0;
		if (fran != null) {
			parentId = fran.getId();
		}
		List<Franchises> temp = getDAO().findByParent(parentId);
		if (temp != null && !temp.isEmpty()) {
			for (Franchises f : temp) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", f.getId());
				map.put("text", f.getName() + "(" + f.getFranchiseLevel()
						+ "级)");
				List<Map<String, Object>> tempList = getTree(f);
				if (tempList != null && !tempList.isEmpty()) {
					map.put("children", tempList);
				}
				list.add(map);
			}
		}
		return list;
	}
}
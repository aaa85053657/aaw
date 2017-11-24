package com.aaw.sys.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import molos.plugins.smvc.dao.IDAO;
import molos.plugins.tool.ToolFactory;
import molos.plugins.tool.validators.INPValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.RequestContext;

import cn.molos.common.SessionConstant;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.Employee;
import com.aaw.bean.RoleAssetsConfig;
import com.aaw.bean.UpmsAccount;
import com.aaw.bean.UpmsAssets;
import com.aaw.bean.UpmsRole;
import com.aaw.sys.dao.IUpmsAssetsDAO;
import com.aaw.sys.service.IEmployeeService;
import com.aaw.sys.service.IRoleAssetsConfigService;
import com.aaw.sys.service.IUpmsAccountService;
import com.aaw.sys.service.IUpmsAssetsService;
import com.aaw.sys.service.IUpmsRoleService;

@Service
public class UpmsAssetsService extends BaseService<UpmsAssets> implements
		IUpmsAssetsService {

	@Override
	protected IUpmsAssetsDAO getDAO() {
		return (IUpmsAssetsDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("upmsAssetsDAO") IDAO<UpmsAssets> dao) {
		super.setDAO(dao);
	}

	@Override
	public List<Map<String, Object>> getMenus(HttpServletRequest request) {
		HttpSession se = request.getSession();
		int type = (Integer) se.getAttribute(SessionConstant.LOGIN_USER_TYPE);
		if (type == 1) {
			List<UpmsAssets> list = null;
			Employee e = getEmployeeFromSession(se);
			if (e != null) {
				list = new ArrayList<UpmsAssets>();
				UpmsRole role = e.getAccount().getRole();
				list = getDAO().listByRID(role.getId());
			}
			if (list != null) {
				Collections.sort(list, new Comparator<UpmsAssets>() {
					@Override
					public int compare(UpmsAssets o1, UpmsAssets o2) {
						return o1.getOrderValue() - o2.getOrderValue();
					}
				});
				return assmMenu(list, request);
			}
			return new ArrayList<Map<String, Object>>();
		} else {
			List<UpmsAssets> list = getDAO().listByFran();
			Collections.sort(list, new Comparator<UpmsAssets>() {
				@Override
				public int compare(UpmsAssets o1, UpmsAssets o2) {
					return o1.getOrderValue() - o2.getOrderValue();
				}
			});
			return assmMenu(list, request);
		}
	}

	private INPValidator npv = ToolFactory.getNPV();

	/**
	 * 组装资源为菜单需要的数据格式
	 * 
	 * @param list
	 * @return
	 */
	private List<Map<String, Object>> assmMenu(List<UpmsAssets> list,
			HttpServletRequest request) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<UpmsAssets> temp = new ArrayList<UpmsAssets>();
		temp.addAll(list);
		RequestContext rc = new RequestContext(request);
		for (UpmsAssets ar : list) {
			if (ar.getType() == 1 && ar.getParent() == 0) {
				temp.remove(ar);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("menuid", ar.getId());
				map.put("icon", ar.getIcon());
				map.put("menuname", rc.getMessage(ar.getModuleName()));
				List<Map<String, Object>> cList = child(ar, temp, rc);
				if (!npv.isNull(cList)) {
					map.put("menus", cList);
				}
				reList.add(map);
			}
		}
		return reList;
	}

	/**
	 * 二级菜单处理
	 * 
	 * @param a
	 * @param list
	 * @param rc
	 * @return
	 */
	private List<Map<String, Object>> child(UpmsAssets a,
			List<UpmsAssets> list, RequestContext rc) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		if (!npv.isNull(list)) {
			List<UpmsAssets> temp = new ArrayList<UpmsAssets>();
			temp.addAll(list);
			for (UpmsAssets aa : list) {
				temp.remove(aa);
				if (a.getId() == aa.getParent()) {
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("menuid", aa.getId());
					m.put("icon", aa.getIcon());
					m.put("menuname", rc.getMessage(aa.getModuleName()));
					m.put("url", aa.getModulePath());
					// m.put("url", aa.getPath() + "?id=" + aa.getId());
					List<Map<String, Object>> cList1 = child(aa, temp, rc);
					if (!npv.isNull(cList1)) {
						m.put("menus", cList1);
					}
					reList.add(m);
				}
			}
		}

		return reList;
	}

	@Override
	public List<Map<String, Object>> assetsList(List<UpmsAssets> list,
			HttpServletRequest request) {
		return assmList(list, request);
	}

	private List<Map<String, Object>> assmList(List<UpmsAssets> list,
			HttpServletRequest request) {
		RequestContext rc = new RequestContext(request);
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<UpmsAssets> temp = new ArrayList<UpmsAssets>();
		temp.addAll(list);
		for (UpmsAssets a : list) {
			if (a.getType() == 1 && a.getParent() == 0) {
				temp.remove(a);
				Map<String, Object> map = assmMap(a, rc);
				List<Map<String, Object>> cList = child2(a, temp, rc);
				if (!npv.isNull(cList)) {
					map.put("children", cList);
				}
				reList.add(map);
			}
		}
		return reList;
	}

	private List<Map<String, Object>> child2(UpmsAssets a,
			List<UpmsAssets> list, RequestContext rc) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		if (!npv.isNull(list)) {
			List<UpmsAssets> temp = new ArrayList<UpmsAssets>();
			temp.addAll(list);
			for (UpmsAssets aa : list) {
				temp.remove(aa);
				if (a.getId() == aa.getParent()) {
					Map<String, Object> m = assmMap(aa, rc);
					List<Map<String, Object>> cList1 = child2(aa, temp, rc);
					if (!npv.isNull(cList1)) {
						m.put("children", cList1);
					}
					reList.add(m);
				}
			}
		}
		return reList;
	}

	/**
	 * @description 转换资源信息对象为指定的json map格式
	 * @param r
	 *            当前需要被转的资源对象
	 * @return
	 */
	private Map<String, Object> assmMap(UpmsAssets r) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", r.getId());
		map.put("iconCls", r.getIcon());
		map.put("state", "open");
		map.put("text", r.getModuleName());
		return map;
	}

	private Map<String, Object> assmMap(UpmsAssets r, RequestContext rc) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", r.getId());
		map.put("iconCls", r.getIcon());
		map.put("state", "open");
		map.put("text", rc.getMessage(r.getModuleName()));
		return map;
	}

	@Override
	public List<Map<String, Object>> assetsList2(List<UpmsAssets> list,
			List<Integer> list2, HttpServletRequest request) {
		RequestContext rc = new RequestContext(request);
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<UpmsAssets> temp = new ArrayList<UpmsAssets>();
		temp.addAll(list);
		for (UpmsAssets a : list) {
			if (a.getType() == 1 && a.getParent() == 0) {
				temp.remove(a);
				Map<String, Object> map = assmMap(a, rc);
				List<Map<String, Object>> cList = child3(a, temp, rc, list2);
				if (!npv.isNull(cList)) {
					map.put("children", cList);
				}
				reList.add(map);
			}
		}
		return reList;
	}

	private List<Map<String, Object>> child3(UpmsAssets a,
			List<UpmsAssets> list, RequestContext rc, List<Integer> list2) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		if (!npv.isNull(list)) {
			List<UpmsAssets> temp = new ArrayList<UpmsAssets>();
			temp.addAll(list);
			for (UpmsAssets aa : list) {
				temp.remove(aa);
				if (a.getId() == aa.getParent()) {
					Map<String, Object> m = assmMap2(aa, rc, list2);
					List<Map<String, Object>> cList1 = child3(aa, temp, rc,
							list2);
					if (!npv.isNull(cList1)) {
						m.put("children", cList1);
					}
					reList.add(m);
				}
			}
		}
		return reList;
	}

	private Map<String, Object> assmMap2(UpmsAssets r, RequestContext rc,
			List<Integer> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", r.getId());
		map.put("iconCls", r.getIcon());
		map.put("state", "open");
		map.put("text", rc.getMessage(r.getModuleName()));
		for (Integer i : list) {
			if (r.getId() == i) {
				map.put("checked", true);
			}
		}
		return map;
	}

	@Override
	public void init() {
		String srRoleName = "SuperAdministrator";
		String srAccountName = "SuperAdministrator";
		String srAccountPWD = "123";
		String srEmployeeName = "SuperAdministrator";
		String srEmployeeCID = "00001";
		int roleId = 0;
		List<Integer> existConfigAsstesIDs = new ArrayList<Integer>();
		if (upmsRoleService.isUnique("name", srRoleName, 0)) {// 库中不存在超级管理角色则进行初始化角色账户等信息
			roleId = upmsRoleService.save(new UpmsRole(srRoleName));
		} else {
			roleId = upmsRoleService.list("name", srRoleName).get(0).getId();
			List<RoleAssetsConfig> racList = roleAssetsConfigService.list(
					"upmsRoleId", roleId);
			for (RoleAssetsConfig roleAssetsConfig : racList) {
				existConfigAsstesIDs.add(roleAssetsConfig.getUpmsAssetsId());
			}
		}
		List<UpmsAssets> asstes = this.list();
		for (UpmsAssets upmsAssets : asstes) {
			if (!existConfigAsstesIDs.contains(upmsAssets.getId())) {
				roleAssetsConfigService.save(new RoleAssetsConfig(upmsAssets
						.getId(), roleId));
			}
		}
		UpmsAccount ua = null;// 管理员账户创建
		if (upmsAccountService.isUnique("name", srAccountName, 0)) {
			ua = new UpmsAccount(srAccountName, srAccountPWD, new UpmsRole(
					roleId));
			upmsAccountService.save(ua);
		} else {
			ua = upmsAccountService.list("name", srAccountName).get(0);
		}
		Employee e = null;
		// 管理员员工创建
		if (employeeService.isUnique("name", srEmployeeName, 0)) {

			e = new Employee(srEmployeeCID, srEmployeeName, 1,
					"380224447@qq.com", new Date(), 1);
			e.setAccount(ua);
			employeeService.save(e);
		} else {
			e = employeeService.list("name", srEmployeeName).get(0);
		}
	}

	@Override
	public List<Map<String, Object>> list4Bind(int roleID) {
		List<UpmsAssets> list = list();
		List<Integer> list2 = getDAO().findBindIDs(roleID);// 获取已经被绑定的数据资源ID
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<UpmsAssets> temp = new ArrayList<UpmsAssets>();
		temp.addAll(list);
		for (UpmsAssets a : list) {
			if (a.getType() == 1 && a.getParent() == 0) {
				temp.remove(a);
				Map<String, Object> map = assmMap(a);
				List<Map<String, Object>> cList = child3(a, temp, list2);
				if (!npv.isNull(cList)) {
					map.put("children", cList);
				}
				reList.add(map);
			}
		}
		return reList;
	}

	/**
	 * @description
	 * @param a
	 * @param list
	 * @param list2
	 * @return
	 */
	private List<Map<String, Object>> child3(UpmsAssets a,
			List<UpmsAssets> list, List<Integer> list2) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		if (!npv.isNull(list)) {
			List<UpmsAssets> temp = new ArrayList<UpmsAssets>();
			temp.addAll(list);
			for (UpmsAssets aa : list) {
				temp.remove(aa);
				if (a.getId() == aa.getParent()) {
					Map<String, Object> m = assmMap2(aa, list2);
					List<Map<String, Object>> cList1 = child3(aa, temp, list2);
					if (!npv.isNull(cList1)) {
						m.put("children", cList1);
					}
					reList.add(m);
				}
			}
		}
		return reList;
	}

	/**
	 * @description
	 * @param r
	 * @param list
	 * @return
	 */
	private Map<String, Object> assmMap2(UpmsAssets r, List<Integer> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", r.getId());
		map.put("iconCls", r.getIcon());
		map.put("state", "open");
		map.put("text", r.getModuleName());
		if (!npv.isNull(list) && list.contains(r.getId())) {
			map.put("checked", true);
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> listAll(HttpServletRequest request) {
		List<UpmsAssets> list = list();
		RequestContext rc = new RequestContext(request);
		List<UpmsAssets> temp = new ArrayList<UpmsAssets>(list);
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		for (UpmsAssets r : list) {
			if (r.getType() == 1 && r.getParent() == 0) {
				temp.remove(r);
				Map<String, Object> map = assmMap(r, rc);
				List<Map<String, Object>> cList = children(r, temp, rc);
				if (!npv.isNull(cList)) {
					map.put("children", cList);
				}
				reList.add(map);
			}
		}
		return reList;
	}

	private List<Map<String, Object>> children(UpmsAssets u,
			List<UpmsAssets> list, RequestContext rc) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		if (!npv.isNull(list)) {
			List<UpmsAssets> temp = new ArrayList<UpmsAssets>();
			temp.addAll(list);
			for (UpmsAssets cu : list) {
				temp.remove(cu);
				if (u.getId() == cu.getParent()) {
					Map<String, Object> m = assmMap(cu, rc);
					List<Map<String, Object>> cList = children(cu, temp, rc);
					if (!npv.isNull(cList)) {
						m.put("children", cList);
					}
					reList.add(m);
				}
			}
		}
		return reList;

	}

	@Override
	public List<Integer> list4BindIDs(int roleID) {
		return getDAO().findBindIDs(roleID);
	}

	@Resource
	private IUpmsRoleService upmsRoleService;
	@Resource
	private IRoleAssetsConfigService roleAssetsConfigService;
	@Resource
	private IUpmsAccountService upmsAccountService;
	@Resource
	private IEmployeeService employeeService;
}
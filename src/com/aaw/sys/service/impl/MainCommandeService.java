package com.aaw.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import molos.plugins.smvc.dao.IDAO;
import molos.plugins.tool.changer.TypeConver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.CommandeStatus;
import com.aaw.bean.CommandeStatusTemp;
import com.aaw.bean.CustomerInfo;
import com.aaw.bean.FactoryStatus;
import com.aaw.bean.Franchises;
import com.aaw.bean.FranchisesCommandeRelation;
import com.aaw.bean.MainCommande;
import com.aaw.bean.SlaveCommande;
import com.aaw.bean.SlaveCommandeConfig;
import com.aaw.bean.SlaveCommandeFreeConfig;
import com.aaw.bean.Worksheet;
import com.aaw.sys.dao.IMainCommandeDAO;
import com.aaw.sys.form.CommandeCfg;
import com.aaw.sys.form.MDConditions;
import com.aaw.sys.service.ICommandeStatusService;
import com.aaw.sys.service.ICommandeStatusTempService;
import com.aaw.sys.service.IFactoryStatusService;
import com.aaw.sys.service.IMainCommandeService;
import com.aaw.sys.service.ISlaveCommandeConfigService;
import com.aaw.sys.service.ISlaveCommandeFreeConfigService;
import com.aaw.sys.service.ISlaveCommandeService;
import com.aaw.sys.service.IWorksheetService;

@Service
public class MainCommandeService extends BaseService<MainCommande> implements
		IMainCommandeService {

	@Override
	protected IMainCommandeDAO getDAO() {
		return (IMainCommandeDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("mainCommandeDAO") IDAO<MainCommande> dao) {
		super.setDAO(dao);
	}

	@Override
	public boolean deleteAndCheck(int id) {
		return getDAO().deleteAndCheck(id);
	}

	@Override
	public void saveCfg(CommandeCfg bean) {
		int id = slaveCommandeService.save(bean.getCommande());
		List<SlaveCommandeConfig> list = bean.getConfigs();
		List<SlaveCommandeFreeConfig> list2 = bean.getConfigs2();
		for (SlaveCommandeConfig slaveCommandeConfig : list) {
			if (slaveCommandeConfig.getElementLeft() != null
					&& slaveCommandeConfig.getElementLeft().getId() == null) {
				slaveCommandeConfig.setElementLeft(null);
			}
			if (slaveCommandeConfig.getElementRight() != null
					&& slaveCommandeConfig.getElementRight().getId() == null) {
				slaveCommandeConfig.setElementRight(null);
			}
			slaveCommandeConfig.setSlaveCommande(new SlaveCommande(id));
			slaveCommandeConfigService.save(slaveCommandeConfig);
		}
		if (list2 != null) {
			for (SlaveCommandeFreeConfig config : list2) {
				config.setSlaveCommande(new SlaveCommande(id));
				slaveCommandeFreeConfigService.save(config);
			}
		}
	}

	@Override
	public Map<String, Object> cfgList(int id) {
		List<SlaveCommande> list = getDAO().querySlaveByMainID(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", npv.isNull(list) ? 0 : list.size());
		return map;
	}

	@Override
	public void initStatusData(int id) {
		List<CommandeStatusTemp> temp = commandeStatusTempService.list();
		getDAO().initStatusData(id, temp);
	}

	@Override
	public void initStatusData2(int id, Integer gid) {
		List<CommandeStatusTemp> temp = commandeStatusTempService
				.findByGID(gid);
		getDAO().initStatusData(id, temp);
	}

	@Override
	public List<Map<String, Object>> stList(int id) {
		List<CommandeStatus> list = commandeStatusService.queryListByMID(id);
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		if (!npv.isNull(list)) {
			for (CommandeStatus c : list) {
				if (c.getParentId().intValue() == 0) {
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("id", c.getId());
					m.put("name", c.getName());
					m.put("isSendMail", c.getIsSendMail());
					m.put("comments", c.getComments());
					m.put("hasChildren", c.getHasChildren());
					m.put("nextId", c.getNextId());
					m.put("parentId", c.getParentId());
					m.put("previousId", c.getPreviousId());
					m.put("status", c.getStatus());
					m.put("mid", id);
					m.put("children", children(c, list, id));
					reList.add(m);
				}
			}
		}
		return sortRs(reList);
	}

	private List<Map<String, Object>> children(CommandeStatus c,
			List<CommandeStatus> list, int id) {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		for (CommandeStatus c1 : list) {
			if (c1.getParentId().intValue() == c.getId().intValue()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", c1.getId());
				m.put("name", c1.getName());
				m.put("isSendMail", c1.getIsSendMail());
				m.put("comments", c1.getComments());
				m.put("hasChildren", c1.getHasChildren());
				m.put("nextId", c1.getNextId());
				m.put("parentId", c1.getParentId());
				m.put("previousId", c1.getPreviousId());
				m.put("status", c1.getStatus());
				m.put("mid", id);
				m.put("children", children(c1, list, id));
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
		do {
			if (npv.isNull(list)) {
				break;
			} else {
				for (Map<String, Object> map : list) {
					if (t.toInt(temp.get("nextId")).intValue() == t.toInt(
							map.get("id")).intValue()) {
						rList.add(map);
						temp = map;
						list.remove(map);
						break;
					}
				}
			}
		} while (true);
		return rList;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean saveStChange(int mid, int id, int st, String string) {
		CommandeStatus cst = commandeStatusService.query(id);
		MainCommande commande = getDAO().query(mid);
		commande.setOrderStatus(3);
		getDAO().update(commande);
		getDAO().saveStChange(mid, cst, st);
		if (cst.getIsSendMail() > 0) {// 需要发送邮件
			MainCommande m = query(mid);
			CustomerInfo cus = m.getCustomer();
			String toEmailAddr = cus.getEmail();
		}
		if (st == 1) {
			cst = qcst4CreateWs(cst, string);
			if (cst != null) {
				getDAO().saveStChange(mid, cst, 1);
				MainCommande m = query(mid);
				List<SlaveCommande> list = slaveCommandeService.list(
						"mainCommande.id", mid);
				if (!npv.isNull(list)) {
					if (cst.getIsSendMail() > 0) { // 需要发送邮件
						CustomerInfo cus = m.getCustomer();
						String toEmailAddr = cus.getEmail();
					}
					for (SlaveCommande slaveCommande : list) {
						Worksheet w = new Worksheet();
						w.setTimeCreation(new Date());
						w.setSlaveCommande(slaveCommande);
						// int wid=worksheetService.save(w);// 创建加工单
						Map<String, Object> mp = worksheetService
								.save4Create(w);// 创建加工单
						int wid = (Integer) mp.get("id");// Worksheet ID
						int sz = (Integer) mp.get("sz");// 总工序数量
						FactoryStatus fs = new FactoryStatus();// 添加默认 进度信息
						fs.setStatus(0);
						fs.setStatusCount(sz);
						fs.setSlaveCommande(slaveCommande);
						fs.setWorksheet(new Worksheet(wid));
						fs.setCurrentStatus(1);
						fs.setOptStatus(10);
						fs.setWorkStatus(5);
						factoryStatusService.save(fs);
						commande.setOrderStatus(4);
						getDAO().update(commande);
					}
				}
				return true;
			}
		}
		return false;
	}

	public CommandeStatus qcst4CreateWs(CommandeStatus cst, String string) {
		if (cst.getParentId() == 0 && cst.getNextId() > 0) {
			cst = commandeStatusService.query(cst.getNextId());
			if (cst.getName().equals(string)) {
				return cst;
			} else if (cst.getParentId() > 0) {
				cst = commandeStatusService.query(cst.getParentId());
				return qcst4CreateWs(cst, string);
			}
		}
		return null;
	}

	@Override
	public void initSt(int id) {
		commandeStatusService.initSt(id);
	}

	@Override
	public List<Map<String, Object>> combobox() {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<MainCommande> list = list();
		for (MainCommande a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getCodeId());
			reList.add(map);
		}
		return reList;
	}

	@Override
	public int save(MainCommande t) {
		t.setCodeId(getDAO().codeId());
		return super.save(t);
	}

	@Override
	public String showCodeId() {
		return getDAO().showCodeID();
	}

	@Override
	public String showCodeId2() {
		return getDAO().showCodeID2();
	}

	@Resource
	private ISlaveCommandeService slaveCommandeService;
	@Resource
	private ISlaveCommandeConfigService slaveCommandeConfigService;
	@Resource
	private ICommandeStatusTempService commandeStatusTempService;
	@Resource
	private ICommandeStatusService commandeStatusService;
	@Resource
	private IWorksheetService worksheetService;
	@Resource
	private IFactoryStatusService factoryStatusService;
	@Resource
	private ISlaveCommandeFreeConfigService slaveCommandeFreeConfigService;

	@Override
	public boolean hasSalve(int id) {
		return npv.isNull(getDAO().querySlaveByMainID(id)) ? false : true;
	}

	@Override
	public Object map(int page, int rows, MDConditions cc, int i) {
		Map<String, Object> map = new HashMap<String, Object>();
		int slaveId = -1;
		map.put("total", getDAO().total(cc));
		List<MainCommande> list = getDAO().list(page, rows, cc, i);
		if (!npv.isNull(list)) {
			for (MainCommande m : list) {
				FranchisesCommandeRelation fcr = getDAO().findFCR(m);
				if (fcr != null) {
					m.setFid(fcr.getFranchises());
				}
			}
			List<SlaveCommande> scList = getDAO().listSCList(list);
			if (!npv.isNull(scList)) {
				for (MainCommande m : list) {
					for (SlaveCommande s : scList) {
						if (s != null
								&& s.getMainCommande().getId() == m.getId()) {
							m.setCol1(s.getModel().getName());
							m.setCol3(s.getModel().getLine().getName());
							break;
						}
					}
				}
			}
			List<FactoryStatus> fsList = getDAO().listFSList(list);
			if (!npv.isNull(fsList)) {
				for (MainCommande m : list) {
					for (FactoryStatus s : fsList) {
						if (s != null
								&& s.getSlaveCommande() != null
								&& s.getSlaveCommande().getMainCommande()
										.getId() == m.getId()) {
							SimpleDateFormat dateFormat = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							String datetime = null;
							if (s.getModificationTime() != null) {
								datetime = dateFormat.format(s
										.getModificationTime());
							}
							slaveId = s.getSlaveCommande().getId();
							String col7 = getDAO().findByCol7(slaveId,
									s.getStatus());
							String str = " " + s.getStatus() + "/"
									+ s.getStatusCount();
							m.setCol2(str);
							m.setCol4(s.getOptStatus());
							m.setCol6(s.getWorkStatus());
							m.setCol7(col7);
							m.setCol8(datetime);
							break;
						}
					}
				}
			}
		}
		map.put("rows", list);
		return map;
	}

	@Override
	public Object map2(int page, int rows, MDConditions cc,
			List<Franchises> temp) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Integer> is = new ArrayList<Integer>();
		for (Franchises franchises : temp) {
			is.add(franchises.getId());
		}
		List<Integer> mainTemp = getDAO().listByFran(is);
		if (mainTemp == null || mainTemp.isEmpty()) {
			map.put("total", 0);
			map.put("rows", new ArrayList<MainCommande>());
			return map;
		}
		int slaveId = -1;
		map.put("total", getDAO().total2(cc, mainTemp));
		List<MainCommande> list = getDAO().list2(page, rows, cc, mainTemp);
		if (!npv.isNull(list)) {
			for (MainCommande m : list) {
				FranchisesCommandeRelation fcr = getDAO().findFCR(m);
				if (fcr != null) {
					m.setFid(fcr.getFranchises());
				}
			}
			List<SlaveCommande> scList = getDAO().listSCList(list);
			if (!npv.isNull(scList)) {
				for (MainCommande m : list) {
					for (SlaveCommande s : scList) {
						if (s != null
								&& s.getMainCommande().getId() == m.getId()) {
							m.setCol1(s.getModel().getName());
							m.setCol3(s.getModel().getLine().getName());
							break;
						}
					}
				}
			}
			List<FactoryStatus> fsList = getDAO().listFSList(list);
			if (!npv.isNull(fsList)) {
				for (MainCommande m : list) {
					for (FactoryStatus s : fsList) {
						if (s != null
								&& s.getSlaveCommande() != null
								&& s.getSlaveCommande().getMainCommande()
										.getId() == m.getId()) {
							SimpleDateFormat dateFormat = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							String datetime = null;
							if (s.getModificationTime() != null) {
								datetime = dateFormat.format(s
										.getModificationTime());
							}
							slaveId = s.getSlaveCommande().getId();
							String col7 = getDAO().findByCol7(slaveId,
									s.getStatus());
							String str = " " + s.getStatus() + "/"
									+ s.getStatusCount();
							m.setCol2(str);
							m.setCol4(s.getOptStatus());
							m.setCol6(s.getWorkStatus());
							m.setCol7(col7);
							m.setCol8(datetime);
							break;
						}
					}
				}
			}
		}
		map.put("rows", list);
		return map;
	}

	@SuppressWarnings("unused")
	private String optStatus(Integer integer) {
		switch (integer) {
		case 1:
			return "下道工序未开始";
		case 2:
			return "下道工序已开始";
		case 3:
			return "当前加工单已终止";
		case 4:
			return "当前加工单已删除";
		default:
			return "";
		}
	}

	@Override
	public int isInit(int id) {
		return getDAO().isInit(id);
	}

	@Override
	public int isInit2(int id) {
		return getDAO().isInit2(id);
	}

	@Override
	public void updateCode(String code) {
		getDAO().codeId();

	}

	@Override
	public void updateCode2(String code) {
		getDAO().codeId2();
	}

}

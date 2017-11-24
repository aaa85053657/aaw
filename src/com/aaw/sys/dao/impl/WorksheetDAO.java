package com.aaw.sys.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.Employee;
import com.aaw.bean.FactoryStatus;
import com.aaw.bean.MainCommande;
import com.aaw.bean.Profile;
import com.aaw.bean.SlaveCommande;
import com.aaw.bean.Worksheet;
import com.aaw.bean.WorksheetDetail;
import com.aaw.bean.WorksheetDetailTemp;
import com.aaw.bean.Workstation;
import com.aaw.bean.WorkstationProcedure;
import com.aaw.sys.dao.IWorksheetDAO;
import com.aaw.sys.form.WorkingForm;
import com.aaw.sys.service.IFactoryStatusService;
import com.aaw.sys.service.IWorksheetDetailService;
import com.aaw.sys.service.IWorksheetDetailTempService;
import com.aaw.sys.service.IWorksheetService;

@SuppressWarnings("unchecked")
@Repository
public class WorksheetDAO extends BaseDAO<Worksheet> implements IWorksheetDAO {

	@Override
	public void updateTask(WorkingForm form) {
		Criteria c = currentSession().createCriteria(WorksheetDetail.class);
		c.add(Restrictions.eq("id", form.getWorksheetDetailId()));
		WorksheetDetail temp = null;
		List<WorksheetDetail> temps = c.list();
		if (temps != null && !temps.isEmpty()) {
			temp = temps.get(0);
		}
		if (temp != null && temp.getWorksheet().getTimeAbort() == null
				&& temp.getWorksheet().getTimeDelete() == null) {
			String hql = "update WorksheetDetail w set w.status=:st,w.operator.id=:eid where id=:id";
			currentSession().createQuery(hql).setParameter("st", 2)
					.setParameter("eid", form.getOperatorId())
					.setParameter("id", form.getWorksheetDetailId())
					.executeUpdate();
			List<Profile> list = form.getProList();
			if (!npv.isNull(list)) {
				String hql2 = "update WorksheetDetail w set w.comments=:comments where id=:id";
				for (Profile profile : list) {
					currentSession().createQuery(hql2)
							.setParameter("id", profile.getId())
							.setParameter("comments", profile.getComments())
							.executeUpdate();
				}
			}
		}
	}

	@Override
	public void endTask(WorkingForm form) {
		String hql = "update WorksheetDetail w set w.status=:st,w.operator.id=:eid where id=:id";
		currentSession().createQuery(hql).setParameter("st", 2)
				.setParameter("eid", form.getOperatorId())
				.setParameter("id", form.getWorksheetDetailId())
				.executeUpdate();
		List<Profile> list = form.getProList();
		if (!npv.isNull(list)) {
			String hql2 = "update WorksheetDetail w set w.comments=:comments where id=:id";
			for (Profile profile : list) {
				currentSession().createQuery(hql2)
						.setParameter("id", profile.getId())
						.setParameter("comments", profile.getComments())
						.executeUpdate();
			}
		}

	}

	@Override
	public Object uniqueCommande(String propVal) {
		Criteria c = currentSession().createCriteria(Worksheet.class);
		if (propVal != null) {
			Criteria cc = c.createCriteria("slaveCommande");
			cc.add(Restrictions.eq("codeId", propVal));
		}
		c.add(Restrictions.isNull("timeDelete"));
		Object o = c.setProjection(Projections.rowCount()).uniqueResult();
		if (o != null) {
			return Integer.valueOf(o.toString()) == 0 ? 1 : 2;
		} else {
			return 1;
		}
	}

	@Override
	public void updateDelSlave(int id) {
		Criteria c = currentSession().createCriteria(SlaveCommande.class);
		List<SlaveCommande> temps = c.add(Restrictions.eq("id", id)).list();
		if (temps != null && !temps.isEmpty()) {
			SlaveCommande temp = temps.get(0);
			MainCommande commande = temp.getMainCommande();
			if (commande != null) {
				String hql4 = "update MainCommande m set m.orderStatus=1 where id=:mid";
				currentSession().createQuery(hql4)
						.setParameter("mid", commande.getId()).executeUpdate();
			}
		}
		String hql = "from Worksheet w where w.slaveCommande.id=:sid and w.timeDelete is NULL and w.timeAbort is NULL ";
		List<Worksheet> list = currentSession().createQuery(hql)
				.setParameter("sid", id).list();
		if (list != null && list.size() != 0) {
			for (Worksheet worksheet : list) {
				worksheetDetailService.endTask(worksheet.getId());
				worksheet.setTimeAbort(new Date());
				worksheetService.update(worksheet);
			}
		}
		String hql2 = "update Worksheet w set w.slaveCommande.id=null where w.slaveCommande.id=:sid";
		currentSession().createQuery(hql2).setParameter("sid", id)
				.executeUpdate();

		String hql5 = "update FactoryStatus f set f.slaveCommande.id=null where  f.slaveCommande.id=:sid";
		currentSession().createQuery(hql5).setParameter("sid", id)
				.executeUpdate();

		String hql3 = "delete from SlaveCommandeConfig sc where sc.slaveCommande.id=:sid";
		currentSession().createQuery(hql3).setParameter("sid", id)
				.executeUpdate();

	}

	@Override
	public Object findByCode(String id, int type, Workstation workstation) {
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = "";
		int i = 0;
		Object object = null;
		String hql = "from WorkstationProcedure where workstation.id=:wid";
		List<WorkstationProcedure> procelist = currentSession()
				.createQuery(hql).setParameter("wid", workstation.getId())
				.list();
		if (!npv.isNull(procelist)) {
			if (id != null && !id.trim().equals("")) {
				List<Worksheet> list = sheetByCode(id);
				List<WorksheetDetail> details = detailBySheet(list, procelist);
				if (!npv.isNull(details)) {
					object = list.get(0);
					i = 1;
					map.put("type", i);
					map.put("object", object);
					return map;
				} else {
					String hql1 = "from Employee where badgeCode=:bc";
					List<Employee> list2 = currentSession().createQuery(hql1)
							.setParameter("bc", id).list();
					if (!npv.isNull(list2)) {
						object = list2.get(0);
						i = 2;
						map.put("type", i);
						map.put("object", object);
						return map;
					}
				}
			}
		}
		String hql2 = "from MainCommande where codeId=:id";
		List<MainCommande> commandes = currentSession().createQuery(hql2)
				.setParameter("id", id).list();
		if (commandes != null && !commandes.isEmpty()) {
			msg = "该订单已终止或关闭！";
		} else {
			msg = "无法识别代码,请先扫描订单条码或者工牌,并按后续提示操作";
		}
		map.put("type", i);
		map.put("object", object);
		map.put("msg", msg);
		return map;
	}

	@Override
	public Object findByCode2(int id, String code, int type,
			Workstation workstation) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = 0;
		String msg = "无效登录";
		WorksheetDetail worksheetDetail = null;
		Employee employee = null;
		String hql = "from WorkstationProcedure where workstation.id=:wid";
		List<WorkstationProcedure> procelist = currentSession()
				.createQuery(hql).setParameter("wid", workstation.getId())
				.list();
		if (!npv.isNull(procelist)) {
			if (code != null && !code.trim().equals("")) {
				if (type == 1) {
					Integer[] objs = { 1, 4, 9, 10 };
					String hql1 = "from Employee where badgeCode=:cid";
					List<Employee> list = currentSession().createQuery(hql1)
							.setParameter("cid", code).list();
					if (!npv.isNull(list)) {
						String hql2 = "from WorksheetDetail where worksheet.id=:wid and parentId=0 and status in:ss order by priority asc";
						List<WorksheetDetail> details = currentSession()
								.createQuery(hql2).setParameter("wid", id)
								.setParameterList("ss", objs).list();
						if (!npv.isNull(details)) {
							WorksheetDetail detail = details.get(0);
							if (detail.getStatus() != 10) {
								updateDetail(detail, list.get(0));
								updateTemp(detail);
								String hql3 = "from WorksheetDetail where worksheet.id=:wid and parentId=:pid order by priority asc";
								List<WorksheetDetail> details2 = currentSession()
										.createQuery(hql3)
										.setParameter("wid", id)
										.setParameter("pid", detail.getId())
										.list();
								if (!npv.isNull("details2")) {
									for (WorksheetDetail detail2 : details2) {
										updateTemp(detail2);
									}
								}
								worksheetDetail = detail;
								employee = list.get(0);
								i = 1;
							} else if (detail.getStatus() == 5) {
								i = 5;
								msg = "该加工单已被终止或删除！";
							} else if (detail.getWorksheet().getOpr() != null
									&& detail.getWorksheet().getOpr().getId() == list
											.get(0).getId()) {
								worksheetDetail = detail;
								employee = list.get(0);
								i = 1;
							} else if (detail.getWorksheet().getOpr() != null
									&& detail.getWorksheet().getOpr().getId() != list
											.get(0).getId()) {
								worksheetDetail = detail;
								employee = list.get(0);
								i = 2;
							} else {
								worksheetDetail = detail;
								employee = list.get(0);
								i = 1;
							}
						}
					}
				} else if (type == 2) {
					String hql1 = "from Employee where id=:eid";
					List<Employee> list = currentSession().createQuery(hql1)
							.setParameter("eid", id).list();
					List<Worksheet> sheets = sheetByCode(code);
					List<WorksheetDetail> details = detailBySheet(sheets,
							procelist);
					if (!npv.isNull(list)) {
						if (!npv.isNull(details)) {
							WorksheetDetail detail = details.get(0);
							// if (detail.getStatus() != 9) {
							if (detail.getStatus() != 10) {
								updateDetail(detail, list.get(0));
								updateTemp(detail);
								String hql3 = "from WorksheetDetail where worksheet.id=:wid and parentId=:pid order by priority asc";
								List<WorksheetDetail> details2 = currentSession()
										.createQuery(hql3)
										.setParameter("wid",
												detail.getWorksheet().getId())
										.setParameter("pid", detail.getId())
										.list();
								if (!npv.isNull("details2")) {
									for (WorksheetDetail detail2 : details2) {
										updateTemp(detail2);
									}
								}
								worksheetDetail = detail;
								employee = list.get(0);
								i = 1;
							} else if (detail.getWorksheet().getOpr() != null
									&& detail.getWorksheet().getOpr().getId() == list
											.get(0).getId()) {
								worksheetDetail = detail;
								employee = list.get(0);
								i = 1;
							} else if (detail.getWorksheet().getOpr() != null
									&& detail.getWorksheet().getOpr().getId() != list
											.get(0).getId()) {
								worksheetDetail = detail;
								employee = list.get(0);
								i = 2;
							} else {
								worksheetDetail = detail;
								employee = list.get(0);
								i = 1;
							}
						} else {
							String hql2 = "from MainCommande where codeId=:id";
							List<MainCommande> commandes = currentSession()
									.createQuery(hql2).setParameter("id", code)
									.list();
							if (commandes != null) {
								msg = "该订单已终止或关闭！";
							} else {
								msg = "无法识别代码,请先扫描订单条码或者工牌,并按后续提示操作";
							}
						}
					}
				}
			}
		}
		map.put("type", i);
		map.put("msg", msg);
		map.put("detail", worksheetDetail);
		map.put("employee", employee);
		return map;
	}

	private void updateTemp(WorksheetDetail detail) {
		String hql = "from WorksheetDetailTemp where worksheetDetail.id=:wid";
		List<WorksheetDetailTemp> list = currentSession().createQuery(hql)
				.setParameter("wid", detail.getId()).list();
		if (npv.isNull(list)) {
			WorksheetDetailTemp temp = new WorksheetDetailTemp();
			temp.setWorksheetDetail(detail);
			temp.setComments(detail.getComments());
			detailTempService.save(temp);
		}
	}

	private void updateDetail(WorksheetDetail worksheetDetail, Employee employee) {
		worksheetDetail.setStatus(9);
		worksheetDetailService.update(worksheetDetail);
		Worksheet worksheet = worksheetDetail.getWorksheet();
		worksheet.setOpr(employee);
		worksheetService.update(worksheet);
	}

	private List<WorksheetDetail> detailBySheet(List<Worksheet> list,
			List<WorkstationProcedure> procelist) {
		if (!npv.isNull(list)) {
			Integer[] objs = { 1, 4, 9, 10 };
			Integer[] obj2 = new Integer[procelist.size()];
			for (int i = 0; i < obj2.length; i++) {
				obj2[i] = procelist.get(i).getProcedure().getId();
			}
			String hql2 = "from WorksheetDetail where worksheet.id=:wid and status in :ss and parentId=0 and metaProcedure.id in :mid  order by priority ";
			return currentSession().createQuery(hql2)
					.setParameter("wid", list.get(0).getId())
					.setParameterList("ss", objs).setParameterList("mid", obj2)
					.list();
		}
		return null;
	}

	private List<Worksheet> sheetByCode(String code) {
		String hql1 = "from Worksheet where slaveCommande.mainCommande.codeId=:smId";
		return currentSession().createQuery(hql1).setParameter("smId", code)
				.list();
	}

	@Override
	public Object findWorking(Workstation workstation) {
		Criteria criteria = currentSession().createCriteria(Worksheet.class);
		List<Worksheet> list = criteria.add(Restrictions.isNotNull("opr"))
				.list();
		String hql = "from WorkstationProcedure where workstation.id=:wid";
		List<WorkstationProcedure> procelist = currentSession()
				.createQuery(hql).setParameter("wid", workstation.getId())
				.list();
		if (list != null && !list.isEmpty()) {
			Integer[] obj2 = null;
			if (procelist != null && !procelist.isEmpty()) {
				obj2 = new Integer[procelist.size()];
				for (int i = 0; i < obj2.length; i++) {
					obj2[i] = procelist.get(i).getProcedure().getId();
				}
			}
			List<Worksheet> remove = new ArrayList<Worksheet>();
			for (Worksheet worksheet : list) {
				Object[] ss = new Object[2];
				ss[0] = 9;
				ss[1] = 10;
				String hql2 = "from WorksheetDetail where worksheet.id=:wid and status in :ss and parentId=0 and metaProcedure.id in :mid order by priority ";
				List<WorksheetDetail> temp = currentSession().createQuery(hql2)
						.setParameter("wid", worksheet.getId())
						.setParameterList("mid", obj2)
						.setParameterList("ss", ss).list();
				if (temp == null || temp.isEmpty()) {
					remove.add(worksheet);
				}
			}
			list.removeAll(remove);
		}
		return list;

	}

	@Override
	public Object updateEMP(int did, int eid) {
		WorksheetDetail detail = worksheetDetailService.query(did);
		Worksheet worksheet = detail.getWorksheet();
		Employee employee = new Employee(eid);
		worksheet.setOpr(employee);
		worksheetService.update(worksheet);
		return 1;
	}

	@Override
	public WorksheetDetail findDeatilByCod(int wid) {
		return null;
	}

	@Override
	public Object clickWork(int wid, Workstation workstation) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from WorkstationProcedure where workstation.id=:wid";
		List<WorkstationProcedure> procelist = currentSession()
				.createQuery(hql).setParameter("wid", workstation.getId())
				.list();
		List<WorksheetDetail> details = null;
		if (!npv.isNull(procelist)) {
			List<Worksheet> list = currentSession()
					.createCriteria(Worksheet.class)
					.add(Restrictions.eq("id", wid)).list();
			details = detailBySheet(list, procelist);
		}
		int i = 0;
		Object object = null;
		if (!npv.isNull(details)) {
			// if (details.get(0).getStatus() != 9) {
			object = details.get(0);
			i = 1;
			map.put("type", i);
			map.put("object", object);
			return map;
			// } else {
			// i = 2;
			// map.put("type", i);
			// return map;
			// }
		}
		i = 2;
		map.put("type", i);
		return map;
	}

	@Override
	public boolean deleteAndCheck(int id) {
		Worksheet worksheet = worksheetService.query(id);
		FactoryStatus factoryStatus = factoryStatusService
				.findByWorksheet(worksheet.getId());
		if ((factoryStatus.getStatus() == 0 && factoryStatus.getOptStatus() == 1)
				|| worksheet.getTimeAbort() != null) {
			return true;
		}
		return false;
	}

	@Resource
	private IWorksheetDetailService worksheetDetailService;
	@Resource
	private IWorksheetService worksheetService;
	@Resource
	private IWorksheetDetailTempService detailTempService;
	@Resource
	private IFactoryStatusService factoryStatusService;

}

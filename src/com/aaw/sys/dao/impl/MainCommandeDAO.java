package com.aaw.sys.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import molos.plugins.tool.ToolFactory;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.CommandeStatus;
import com.aaw.bean.CommandeStatusTemp;
import com.aaw.bean.FactoryStatus;
import com.aaw.bean.FranchisesCommandeRelation;
import com.aaw.bean.MainCommande;
import com.aaw.bean.ProductionLine;
import com.aaw.bean.ProductionLineConfig;
import com.aaw.bean.RefData;
import com.aaw.bean.SlaveCommande;
import com.aaw.bean.Worksheet;
import com.aaw.sys.dao.IMainCommandeDAO;
import com.aaw.sys.form.MDConditions;

@Repository
@SuppressWarnings({ "unchecked", "unused" })
public class MainCommandeDAO extends BaseDAO<MainCommande> implements
		IMainCommandeDAO {

	@Override
	public boolean deleteAndCheck(int id) {

		String hql = "select a.id from SlaveCommande as a where a.mainCommande.id=:id";
		List<SlaveCommande> list = currentSession().createQuery(hql)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (!npv.isNull(list)) {
			return false;
		}
		String hql2 = "delete from CommandeStatus cs where cs.commande.id=:cid";
		currentSession().createQuery(hql2).setParameter("cid", id)
				.executeUpdate();
		this.delete(id);
		return true;
	}

	@Override
	public List<SlaveCommande> querySlaveByMainID(int id) {
		String hql = "from SlaveCommande a where a.mainCommande.id=:id";
		return currentSession().createQuery(hql).setParameter("id", id).list();
	}

	@Override
	public void initStatusData(int id, List<CommandeStatusTemp> temp) {
		if (npv.isNull(temp)) {
			return;
		}
		List<CommandeStatusTemp> tList = new ArrayList<CommandeStatusTemp>();
		for (CommandeStatusTemp c : temp) {
			if (c.getParentId() == 0) {
				tList.add(c);
			}
		}
		List<CommandeStatusTemp> temp2 = new ArrayList<CommandeStatusTemp>(temp);
		temp2.removeAll(tList);
		Collections.sort(tList, new Comparator<CommandeStatusTemp>() {
			@Override
			public int compare(CommandeStatusTemp o1, CommandeStatusTemp o2) {
				return o1.getPreviousId() - o2.getPreviousId();
			}
		});

		List<Integer> ids = new ArrayList<Integer>();
		saveNode(ids, 0, tList, temp2, id);
		dealNodePreAndNext(ids);
	}

	/**
	 * 保存节点
	 * 
	 * @param ids
	 *            保存后的ID号
	 * @param i
	 *            父节点ID
	 * @param tList
	 *            当次需要保存的数据
	 * @param temp2
	 *            提供下一批数据筛选的集合
	 * @param id
	 *            当前保存的订单的ID号
	 */
	private void saveNode(List<Integer> ids, int i,
			List<CommandeStatusTemp> tList, List<CommandeStatusTemp> temp2,
			int id) {
		for (CommandeStatusTemp c : tList) {
			CommandeStatus sc = new CommandeStatus(c, id);
			sc.setParentId(i);
			int sid = Integer.parseInt(currentSession().save(sc).toString());
			ids.add(sid);
			if (c.getHasChildren() > 0) {
				List<Integer> ids2 = new ArrayList<Integer>();
				List<CommandeStatusTemp> need = new ArrayList<CommandeStatusTemp>();
				for (CommandeStatusTemp cc : temp2) {
					if (cc.getParentId() == c.getId()) {
						need.add(cc);
					}
				}

				Collections.sort(need, new Comparator<CommandeStatusTemp>() {
					@Override
					public int compare(CommandeStatusTemp o1,
							CommandeStatusTemp o2) {
						return o1.getPreviousId() - o2.getPreviousId();
					}
				});

				List<CommandeStatusTemp> tp2 = new ArrayList<CommandeStatusTemp>(
						temp2);
				tp2.removeAll(need);
				saveNode(ids2, sid, need, tp2, id);
				dealNodePreAndNext(ids2);
			}
		}
	}

	private void dealNodePreAndNext(List<Integer> ids) {
		if (ids.size() <= 1) {
			return;
		}
		int previousId = 0;
		int nextId = 0;
		int id = 0;
		for (int i = 0; i < ids.size(); i++) {
			id = ids.get(i);
			if (i == 0 && ids.size() > (i + 1)) {
				nextId = ids.get(i + 1);
			} else if (i == ids.size() - 1) {
				previousId = ids.get(i - 1);
				nextId = 0;
			} else {
				previousId = ids.get(i - 1);
				nextId = ids.get(i + 1);
			}
			updateNext(id, previousId, nextId);
		}
	}

	//
	// private void saveNextNode(List<Integer> ids,
	// List<CommandeStatusTemp> tList, CommandeStatusTemp ct,
	// TypeConver tc, int id, List<CommandeStatusTemp> temp, int parentId) {
	// if (tList.size() == 1) {
	// CommandeStatus sc = new CommandeStatus(tList.get(0), id);
	// sc.setParentId(parentId);
	// int sid = tc.toInt(currentSession().save(sc));
	// ids.add(sid);
	// tList.clear();
	// } else {
	// if (ct == null) {
	// for (CommandeStatusTemp c1 : tList) {
	// if (c1.getPreviousId() == 0) {
	// ct = c1;
	// CommandeStatus sc = new CommandeStatus(c1, id);
	// sc.setParentId(parentId);
	// int sid = tc.toInt(currentSession().save(sc));
	// ids.add(sid);
	// tList.remove(c1);
	// if (ct.getHasChildren() > 0) {
	//
	// List<CommandeStatusTemp> cList = new ArrayList<CommandeStatusTemp>();
	// for (CommandeStatusTemp cc : temp) {
	// if (cc.getParentId() == ct.getId()) {
	// cList.add(cc);
	// }
	// }
	// List<Integer> childIds = new ArrayList<Integer>();
	// CommandeStatusTemp cst = null;
	// saveNextNode(childIds, cList, cst, tc, id, temp,
	// sid);
	// dealNodePreAndNext(childIds);
	// }
	// break;
	// }
	// }
	// } else {
	// for (CommandeStatusTemp c : tList) {
	// if (ct.getNextId() == c.getId()) {
	// ct = c;
	// CommandeStatus sc = new CommandeStatus(c, id);
	// sc.setParentId(parentId);
	// int sid = tc.toInt(currentSession().save(sc));
	// ids.add(sid);
	// tList.remove(c);
	// if (ct.getHasChildren() > 0) {
	// List<CommandeStatusTemp> cList = new ArrayList<CommandeStatusTemp>();
	// for (CommandeStatusTemp cc : temp) {
	// if (cc.getParentId() == ct.getId()) {
	// cList.add(cc);
	// }
	// }
	// List<Integer> childIds = new ArrayList<Integer>();
	// CommandeStatusTemp cst = null;
	// saveNextNode(childIds, cList, cst, tc, id, temp,
	// sid);
	// dealNodePreAndNext(childIds);
	// }
	// break;
	// }
	// }
	// }
	// }
	// }

	private void updateNext(int id, int previousID, int nextID) {
		String hql = "update CommandeStatus a set a.previousId=:previousId,a.nextId=:next where a.id=:id";
		currentSession().createQuery(hql).setParameter("id", id)
				.setParameter("next", nextID)
				.setParameter("previousId", previousID).executeUpdate();
	}

	@Override
	public void saveStChange(int mid, CommandeStatus cst, int st) {
		if (cst.getStatus() == st) {// 状态无变化
			return;
		}
		updateStChild(cst, st);
		updateParent(cst.getParentId(), st, cst.getId());
	}

	private void updateParent(int parentID, int st, Integer id) {
		if (parentID > 0 && st == 1) {// 含有父节点
			List<CommandeStatus> list = currentSession()
					.createQuery("from CommandeStatus a where a.parentId=:id")
					.setParameter("id", parentID).list();
			if (npv.isNull(list)) {
				return;
			}
			for (CommandeStatus c : list) {
				if (c.getId() == id) {
					continue;
				}
				if (c.getStatus() != 1) {
					return;
				}
			}
			currentSession()
					.createQuery(
							"update CommandeStatus a set a.status=1 where a.id=:id")
					.setParameter("id", parentID).executeUpdate();
		}
	}

	/**
	 * 更新子节点状态
	 * 
	 * @param cst
	 * @param st
	 */
	private void updateStChild(CommandeStatus cst, int st) {
		currentSession()
				.createQuery(
						"update CommandeStatus a set a.status=:status where a.id=:id")
				.setParameter("id", cst.getId()).setParameter("status", st)
				.executeUpdate();

		if (cst.getHasChildren() < 1) {
			return;
		}

		List<CommandeStatus> list = currentSession()
				.createQuery("from CommandeStatus a where a.parentId=:id")
				.setParameter("id", cst.getId()).list();
		if (npv.isNull(list)) {
			return;
		}
		for (CommandeStatus c : list) {
			updateStChild(c, st);
		}
	}

	private String zero = "0000000";

	@Override
	public String codeId() {
		String hql = "from RefData where tableName=1";
		List<RefData> list = currentSession().createQuery(hql).list();
		String now = prefix();
		String reStr = now;
		if (npv.isNull(list)) {
			RefData d = new RefData();
			d.setTableName(1);
			d.setPrefix(now);
			d.setNext(2);
			currentSession().save(d);
			reStr += zero + 1;
		} else {
			RefData d = list.get(0);
			if (d.getPrefix().equals(now)) {
				d.setNext(d.getNext() + 1);
				reStr += sixAdd3((d.getNext() - 1));
			} else {
				d.setPrefix(now);
				d.setNext(2);
				reStr += zero + 1;
			}
			currentSession().update(d);
		}
		return reStr;
	}

	private String sixAdd3(int x) {
		String str = String.valueOf(x);
		int c = 8 - str.length();
		String r = "";
		for (int i = 0; i < c; i++) {
			r += "0";
		}
		r += str;
		return r;
	}

	@Override
	public String showCodeID() {
		String hql = "from RefData where tableName=1";
		List<RefData> list = currentSession().createQuery(hql).list();

		String now = prefix();
		String reStr = now;
		if (npv.isNull(list)) {
			reStr += zero + 1;
		} else {
			RefData d = list.get(0);
			if (d.getPrefix().equals(now)) {
				reStr += sixAdd3((d.getNext() - 1));
			} else {
				reStr += zero + 1;
			}
		}
		return reStr;
	}

	@Override
	public String showCodeID2() {
		String hql = "from RefData where tableName=2";
		List<RefData> list = currentSession().createQuery(hql).list();
		String now = prefix();
		String reStr = now;
		if (npv.isNull(list)) {
			reStr += zero + 0;
		} else {
			RefData d = list.get(0);
			if (d.getPrefix().equals(now)) {
				reStr += sixAdd3((d.getNext() - 1));
			} else {
				reStr += zero + 1;
			}
		}
		return reStr;
	}

	/**
	 * 前缀 15年第3周的地3天 15033
	 * 
	 * @return
	 */
	private String prefix() {
		String now = ToolFactory.getDateTool().now("yy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int wk = c.get(Calendar.WEEK_OF_YEAR);
		String k = String.valueOf(wk);
		if (k.length() == 1) {
			k = "0" + k;
		}
		int d = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (d == 0) {
			d = 7;
		}
		return now + k + d;
	}

	// 加盟商
	// 用来组合一组逻辑或【or】条件的方法
	// Restrictions.disjunction();
	// 用来组合一组逻辑与【and】条件的方法
	// Restrictions.conjunction();
	@Override
	public int total2(MDConditions cc, List<Integer> mainTemp) {
		// 按照生产线种类,按照第三方ID(模糊查询),按照生产进度(>,<,=),按照顾客姓名
		Criteria c = mcCri2(cc, mainTemp);
		Object obj = c.setProjection(Projections.rowCount()).uniqueResult();
		if (obj != null) {
			return Integer.valueOf(obj.toString());
		}
		return 0;
	}

	@Override
	public List<MainCommande> list2(int page, int rows, MDConditions cc,
			List<Integer> mainTemp) {
		Criteria c = mcCri2(cc, mainTemp);
		c.addOrder(Order.desc("id"));
		return c.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	private Criteria mcCri2(MDConditions cc, List<Integer> mainTemp) {
		Criteria c = currentSession().createCriteria(MainCommande.class);
		c.add(Restrictions.in("id", mainTemp));
		if (cc != null) {
			if (!npv.isNull(cc.getThird())) {
				c.add(Property.forName("externalCode").like(cc.getThird(),
						MatchMode.ANYWHERE));
			}
			if (!npv.isNull(cc.getName())) {
				c.createAlias("customer", "cust");
				Disjunction dis = Restrictions.disjunction();
				dis.add(Restrictions.like("cust.firstName", cc.getName(),
						MatchMode.ANYWHERE));
				dis.add(Restrictions.like("cust.middleName", cc.getName(),
						MatchMode.ANYWHERE));
				dis.add(Restrictions.like("cust.lastName", cc.getName(),
						MatchMode.ANYWHERE));
				c.add(dis);
			}
			if (!npv.isNull(cc.getSymbol())) {
				DetachedCriteria fs = DetachedCriteria.forClass(
						FactoryStatus.class, "fs");
				fs.createAlias("slaveCommande", "sc");
				int str = 0;
				if (cc.getSt1() != null) {
					str = cc.getSt1();
				}
				if (cc.getSymbol().equals("=")) {
					fs.add(Restrictions.eq("fs.status", str));
				} else if (cc.getSymbol().equals(">")) {
					fs.add(Restrictions.gt("fs.status", str));
				} else {
					fs.add(Restrictions.lt("fs.status", str));
				}
				fs.setProjection(Projections.property("sc.mainCommande.id"));
				c.add(Property.forName("id").in(fs));
			}
			if (cc.getLineID() != null && cc.getLineID() > 0) { // 根据生产线ID获取匹配的主单ID号集合
				DetachedCriteria scd = DetachedCriteria.forClass(
						SlaveCommande.class, "scd");
				scd.setProjection(Projections.property("scd.mainCommande.id"));
				scd.createAlias("model", "m");
				scd.add(Restrictions.eq("m.line.id", cc.getLineID()));
				c.add(Property.forName("id").in(scd));
			}
		}
		return c;
	}

	// 用来组合一组逻辑或【or】条件的方法
	// Restrictions.disjunction();
	// 用来组合一组逻辑与【and】条件的方法
	// Restrictions.conjunction();
	@Override
	public int total(MDConditions cc) {
		// 按照生产线种类,按照第三方ID(模糊查询),按照生产进度(>,<,=),按照顾客姓名
		Criteria c = mcCri(cc);
		Object obj = c.setProjection(Projections.rowCount()).uniqueResult();
		if (obj != null) {
			return Integer.valueOf(obj.toString());
		}
		return 0;
	}

	@Override
	public List<MainCommande> list(int page, int rows, MDConditions cc, int i) {
		Criteria c = mcCri(cc);
		if (i == 2) {
			c.add(Restrictions.eq("type.id", 2));
		}
		c.addOrder(Order.desc("id"));
		return c.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	private Criteria mcCri(MDConditions cc) {
		Criteria c = currentSession().createCriteria(MainCommande.class);
		if (cc != null) {
			if (!npv.isNull(cc.getThird())) {
				c.add(Property.forName("externalCode").like(cc.getThird(),
						MatchMode.ANYWHERE));
			}
			if (!npv.isNull(cc.getName())) {
				c.createAlias("customer", "cust");
				Disjunction dis = Restrictions.disjunction();
				dis.add(Restrictions.like("cust.firstName", cc.getName(),
						MatchMode.ANYWHERE));
				dis.add(Restrictions.like("cust.middleName", cc.getName(),
						MatchMode.ANYWHERE));
				dis.add(Restrictions.like("cust.lastName", cc.getName(),
						MatchMode.ANYWHERE));
				c.add(dis);
			}
			if (!npv.isNull(cc.getSymbol())) {
				DetachedCriteria fs = DetachedCriteria.forClass(
						FactoryStatus.class, "fs");
				fs.createAlias("slaveCommande", "sc");
				int str = 0;
				if (cc.getSt1() != null) {
					str = cc.getSt1();
				}
				if (cc.getSymbol().equals("=")) {
					fs.add(Restrictions.eq("fs.status", str));
				} else if (cc.getSymbol().equals(">")) {
					fs.add(Restrictions.gt("fs.status", str));
				} else {
					fs.add(Restrictions.lt("fs.status", str));
				}
				fs.setProjection(Projections.property("sc.mainCommande.id"));
				c.add(Property.forName("id").in(fs));
			}
			if (cc.getLineID() != null && cc.getLineID() > 0) { // 根据生产线ID获取匹配的主单ID号集合
				DetachedCriteria scd = DetachedCriteria.forClass(
						SlaveCommande.class, "scd");
				scd.setProjection(Projections.property("scd.mainCommande.id"));
				scd.createAlias("model", "m");
				scd.add(Restrictions.eq("m.line.id", cc.getLineID()));
				c.add(Property.forName("id").in(scd));
			}
		}
		return c;
	}

	@Override
	public List<SlaveCommande> listSCList(List<MainCommande> list) {
		Criteria c = currentSession().createCriteria(SlaveCommande.class);
		c.add(Property.forName("mainCommande").in(list));
		return c.list();
	}

	@Override
	public List<FactoryStatus> listFSList(List<MainCommande> list) {
		Criteria c = currentSession().createCriteria(FactoryStatus.class);
		c.createAlias("slaveCommande", "m");
		c.add(Property.forName("m.mainCommande").in(list));
		return c.list();
	}

	@Override
	public int isInit(int id) {
		int i = -1;
		Criteria c1 = currentSession().createCriteria(SlaveCommande.class);
		List<SlaveCommande> sList = c1.add(
				Restrictions.eq("mainCommande.id", id)).list();
		if (sList == null || sList.isEmpty()) {
			return i;
		}
		Criteria c2 = currentSession().createCriteria(Worksheet.class);
		List<Worksheet> wList = c2.add(
				Restrictions.eq("slaveCommande.id", sList.get(0).getId()))
				.list();
		if (wList == null || wList.isEmpty()) {
			return i;
		}
		if (wList.get(0).getTimeDelete() != null) {
			return i;
		}
		i = wList.get(0).getId();
		return i;
	}

	@Override
	public int isInit2(int id) {
		int i = -1;
		Criteria c1 = currentSession().createCriteria(SlaveCommande.class);
		List<SlaveCommande> sList = c1.add(
				Restrictions.eq("mainCommande.id", id)).list();
		if (sList == null || sList.isEmpty()) {
			return i;
		}
		i = sList.get(0).getId();
		return i;
	}

	@Override
	public String findByCol7(int sid, int status) {
		Criteria tempc = currentSession().createCriteria(SlaveCommande.class);
		List<SlaveCommande> templ = tempc.add(Restrictions.eq("id", sid))
				.list();
		if (templ != null && !templ.isEmpty()) {
			SlaveCommande commande = templ.get(0);
			ProductionLine line = commande.getModel().getLine();
			List<ProductionLineConfig> list = currentSession()
					.createCriteria(ProductionLineConfig.class)
					.add(Restrictions.eq("line.id", line.getId()))
					.add(Restrictions.eq("sequenceNum", (status + 1))).list();
			if (list != null && !list.isEmpty()) {
				return list.get(0).getProcedure().getName();
			}
		}
		// Criteria tempc = currentSession().createCriteria(Worksheet.class);
		// List<Worksheet> templ = tempc.add(
		// Restrictions.eq("slaveCommande.id", sid)).list();
		// if (templ != null && !templ.isEmpty()) {
		// Criteria c = currentSession().createCriteria(WorksheetDetail.class);
		// List<WorksheetDetail> list = c
		// .add(Restrictions.eq("worksheet.id", templ.get(0).getId()))
		// .add(Restrictions.eq("priority", (status + 1)))
		// .add(Restrictions.eq("parentId", 0)).list();
		// if (list != null && !list.isEmpty()) {
		// return list.get(0).getMetaProcedure().getName();
		// }
		// }

		return null;
	}

	@Override
	public void codeId2() {
		String hql = "from RefData where tableName=2";
		List<RefData> list = currentSession().createQuery(hql).list();
		String now = prefix();
		String reStr = now;
		if (npv.isNull(list)) {
			RefData d = new RefData();
			d.setTableName(1);
			d.setPrefix(now);
			d.setNext(2);
			currentSession().save(d);
			reStr += zero + 1;
		} else {
			RefData d = list.get(0);
			if (d.getPrefix().equals(now)) {
				d.setNext(d.getNext() + 1);
				reStr += sixAdd3((d.getNext() - 1));
			} else {
				d.setPrefix(now);
				d.setNext(2);
				reStr += zero + 1;
			}
			currentSession().update(d);
		}

	}

	@Override
	public FranchisesCommandeRelation findFCR(MainCommande m) {
		Criteria c = currentSession().createCriteria(
				FranchisesCommandeRelation.class);
		List<FranchisesCommandeRelation> list = c.add(
				Restrictions.eq("commande.id", m.getId())).list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Integer> listByFran(List<Integer> is) {
		List<Integer> result = new ArrayList<Integer>();
		String sql = "select commande_id from franchises_commande_relation where franchises_id in(:fids)";
		List<Object> list = currentSession().createSQLQuery(sql)
				.setParameterList("fids", is).list();
		if (list != null && !list.isEmpty()) {
			for (Object obj : list) {
				result.add(Integer.valueOf(obj.toString()));
			}
			return result;
		}
		return null;
	}

}
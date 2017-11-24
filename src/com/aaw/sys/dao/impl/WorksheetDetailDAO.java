package com.aaw.sys.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.Profile;
import com.aaw.bean.Worksheet;
import com.aaw.bean.WorksheetDetail;
import com.aaw.bean.WorksheetHistory;
import com.aaw.sys.dao.IWorksheetDetailDAO;
import com.aaw.sys.form.WorkingForm;

@Repository
@SuppressWarnings("unchecked")
public class WorksheetDetailDAO extends BaseDAO<WorksheetDetail> implements
		IWorksheetDetailDAO {

	@Override
	public List<WorksheetDetail> queryListByWorksheetID(int wid, int type,
			int order) {
		String hql = "from WorksheetDetail w where w.worksheet.id=:id ";
		switch (type) {
		case 1:
			hql += " and w.parentId=0";
			break;
		case 2:
			hql += " and w.parentId!=0";
			break;
		default:
			break;
		}

		switch (order) {
		case 1:
			hql += " order by w.priority asc";
			break;
		case 2:
			hql += " order by w.priority desc";
			break;
		default:
			break;
		}
		return currentSession().createQuery(hql).setParameter("id", wid).list();
	}

	@Override
	public Object queryListByWorksheetID(int wid) {
		String hql = "from WorksheetDetail w where w.worksheet.id=:id ";
		hql += " and w.parentId=0";
		hql += " and (w.status=2";
		hql += " or w.status=9)";
		hql += " order by w.priority asc";
		return currentSession().createQuery(hql).setParameter("id", wid).list();
	}

	@Override
	public List<WorksheetDetail> queryListByPID(int pid, int order) {
		Criteria c = currentSession().createCriteria(WorksheetDetail.class);
		c.add(Restrictions.eq("parentId", pid));
		// String hql = "from WorksheetDetail w where w.parentId.id=:id ";
		switch (order) {
		case 1:
			c.addOrder(Order.asc("priority"));
			// hql += " order by w.priority asc";
			break;
		case 2:
			c.addOrder(Order.desc("priority"));
			// hql += " order by w.priority desc";
			break;
		default:
			break;
		}
		// return currentSession().createQuery(hql).setParameter("id",
		// pid).list();
		return c.list();
	}

	/**
	 * 释放资源
	 * 
	 * @param id
	 *            id
	 * @param i
	 *            状态
	 */
	@Override
	public void updateStatus(int id, int i) {
		String hql = "update WorksheetDetail w set w.status=:st where w.id=:id";
		currentSession().createQuery(hql).setParameter("st", i)
				.setParameter("id", id).executeUpdate();

	}

	@Override
	public WorksheetHistory updateTask(WorkingForm form) {
		String hql = "update WorksheetDetail w set w.status=:st,w.operator.id=:eid where id=:id";
		StringBuilder sb = new StringBuilder();
		currentSession().createQuery(hql).setParameter("st", 2)
				.setParameter("eid", form.getOperatorId())
				.setParameter("id", form.getWorksheetDetailId())
				.executeUpdate();
		List<Profile> list = form.getProList();
		if (!npv.isNull(list)) {
			String hql2 = "update WorksheetDetail w set w.status=:st,w.comments=:comments where id=:id";
			for (Profile profile : list) {
				currentSession().createQuery(hql2)
						.setParameter("id", profile.getId())
						.setParameter("comments", profile.getComments())
						.setParameter("st", 2).executeUpdate();
				sb.append(profile.getName()).append(":")
						.append(profile.getComments()).append(",");
			}
		}
		WorksheetHistory history = new WorksheetHistory();
		history.setWorksheetDetail(new WorksheetDetail(form
				.getWorksheetDetailId()));
		history.setComment(sb.toString());
		return history;
	}

	@Override
	public void updateTaskTemp(WorkingForm form) {
		List<Profile> list = form.getProList();
		if (!npv.isNull(list)) {
			WorksheetDetail detail = new WorksheetDetail();
			String hql2 = "update WorksheetDetailTemp w set w.comments=:comments where w.worksheetDetail.id=:id";
			String hql3 = "update WorksheetDetail ws set ws.status=10 where id=:id and status!=2";
			for (Profile profile : list) {
				currentSession().createQuery(hql2)
						.setParameter("id", profile.getId())
						.setParameter("comments", profile.getComments())
						.executeUpdate();
				currentSession().createQuery(hql3)
						.setParameter("id", profile.getId()).executeUpdate();
				detail = (WorksheetDetail) currentSession()
						.createCriteria(WorksheetDetail.class)
						.add(Restrictions.eq("id", profile.getId())).list()
						.get(0);
			}
			currentSession().createQuery(hql3)
					.setParameter("id", detail.getParentId()).executeUpdate();
		}

	}

	@Override
	public void endTask(int wid) {
		Criteria c = currentSession().createCriteria(WorksheetDetail.class);
		c.add(Restrictions.eq("worksheet.id", wid));
		List<WorksheetDetail> details = c.list();
		for (WorksheetDetail worksheetDetail : details) {
			String hql2 = "update WorksheetDetail w set w.status=:st where id=:id";
			currentSession().createQuery(hql2).setParameter("st", 5)
					.setParameter("id", worksheetDetail.getId())
					.executeUpdate();
		}

	}

	@Override
	public List<WorksheetDetail> findById(int rid) {
		Criteria c = currentSession().createCriteria(WorksheetDetail.class);
		c.add(Restrictions.or(Restrictions.eq("id", rid),
				Restrictions.eq("parentId", rid))).addOrder(
				Order.asc("priority"));
		return c.list();
	}

	@Override
	public Map<String, Object> queryList(int page, int rows, Integer integer) {
		Map<String, Object> map = new HashMap<String, Object>();
		Set<Integer> set = queryListNoEnd();
		List<Worksheet> list = new ArrayList<Worksheet>();
		if (!set.isEmpty()) {
			int total = totals(set, integer);
			list = mlist(page, rows, set, integer);
			map.put("total", total);
			map.put("rows", list);
		} else {
			map.put("total", 0);
			map.put("rows", list);
		}
		return map;
	}

	private List<Worksheet> mlist(int page, int rows, Set<Integer> set,
			Integer integer) {
		Criteria c = currentSession().createCriteria(Worksheet.class);
		assmConditions(c, set, integer);
		return c.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	private int totals(Set<Integer> set, Integer integer) {
		Criteria c = currentSession().createCriteria(Worksheet.class);
		assmConditions(c, set, integer);
		Object o = c.setProjection(Projections.rowCount()).uniqueResult();
		return o == null ? 0 : Integer.valueOf(o.toString());
	}

	private Criteria assmConditions(Criteria c, Set<Integer> set,
			Integer integer) {
		c.add(Restrictions.in("id", set));
		if (integer != 0) {
			c.add(Restrictions.or(Restrictions.isNull("opr"),
					Restrictions.eq("opr.id", integer)));
		} else {
			c.add(Restrictions.isNull("opr"));
		}
		return c;
	}

	private Set<Integer> queryListNoEnd() {
		Criteria c = currentSession().createCriteria(WorksheetDetail.class);
		c.add(Restrictions.ne("status", 5));
		List<WorksheetDetail> details = c.list();
		Set<Integer> integers = new HashSet<Integer>();
		if (details != null && details.size() != 0 && !details.isEmpty())
			for (WorksheetDetail detail : details) {
				integers.add(detail.getWorksheet().getId());
			}
		return integers;
	}

	@Override
	public List<WorksheetDetail> queryListByWork(int id) {
		Criteria c = currentSession().createCriteria(WorksheetDetail.class);
		Object[] i = { 2, 9 };
		c.add(Restrictions.in("status", i));
		List<WorksheetDetail> list = c.list();
		List<WorksheetDetail> had = new ArrayList<WorksheetDetail>();
		if (!list.isEmpty()) {
		}
		return had;
	}

	@Override
	public Map<String, Object> searchMore(int page, int rows, int pid,
			Integer integer) {
		Map<String, Object> map = new HashMap<String, Object>();
		int total = totals2(pid);
		List<WorksheetDetail> list = mlist2(page, rows, pid);
		List<Worksheet> list2 = new ArrayList<Worksheet>();
		for (WorksheetDetail detail : list) {
			if (isWorking(detail, integer)) {
				list2.add(detail.getWorksheet());
			}
		}
		map.put("total", total);
		map.put("rows", list2);
		return map;
	}

	public boolean isWorking(WorksheetDetail detail, Integer integer) {
		if (detail.getWorksheet().getOpr() == null
				|| detail.getWorksheet().getOpr().getId() == integer) {
			if (detail.getPriority() == 1) {
				return true;
			} else {
				String hql = "from WorksheetDetail w where w.worksheet.id=:wid and w.priority<:pid and w.parentId=0";
				List<WorksheetDetail> list = currentSession().createQuery(hql)
						.setParameter("wid", detail.getWorksheet().getId())
						.setParameter("pid", detail.getPriority()).list();
				if (list != null && !list.isEmpty()) {
					for (WorksheetDetail detail2 : list) {
						if (detail2.getStatus() != 2) {
							return false;
						}
					}
				}
			}
			return true;
		}
		return false;
	}

	private List<WorksheetDetail> mlist2(int page, int rows, int pid) {
		Criteria criteria = currentSession().createCriteria(
				WorksheetDetail.class);
		assmConditions2(criteria, pid);
		return criteria.setFirstResult((page - 1) * rows)
				.setMaxResults(page * rows).list();
	}

	private int totals2(int pid) {
		Criteria criteria = currentSession().createCriteria(
				WorksheetDetail.class);
		assmConditions2(criteria, pid);
		Object o = criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return o == null ? 0 : Integer.valueOf(o.toString());
	}

	private Criteria assmConditions2(Criteria criteria, int pid) {
		Object[] stu = { 1, 4, 9 };
		if (pid != 0) {
			criteria.add(Restrictions.eq("metaProcedure.id", pid));
			criteria.add(Restrictions.in("status", stu));
		}
		criteria.add(Restrictions.eq("parentId", 0));
		return criteria;

	}

	@Override
	public void updateStatus(Date date) {
		String hql = "update WorksheetDetail w set w.status=1 where w.status=9 and w.unlockTime<=:date";
		currentSession().createQuery(hql).setParameter("date", date)
				.executeUpdate();
	}

	@Override
	public List<WorksheetDetail> findByStatus(int i) {
		Criteria c = currentSession().createCriteria(WorksheetDetail.class);
		c.add(Restrictions.eq("status", i));
		return c.list();
	}

	@Override
	public Map<String, Object> getWorkSheetList(int page, int rows, Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int total = totals3();
		List<WorksheetDetail> list = mlist3(page, rows);
		map.put("rows", list);
		map.put("total", total);
		return map;
	}

	private List<WorksheetDetail> mlist3(int page, int rows) {
		Criteria criteria = currentSession().createCriteria(Worksheet.class);
		assmConditions3(criteria);
		return criteria.setFirstResult((page - 1) * rows)
				.setMaxResults(page * rows).list();
	}

	private int totals3() {
		Criteria criteria = currentSession().createCriteria(Worksheet.class);
		assmConditions3(criteria);
		Object o = criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return o == null ? 0 : Integer.valueOf(o.toString());
	}

	private Criteria assmConditions3(Criteria criteria) {
		criteria.add(Restrictions.isNull("timeDelete"))
				.add(Restrictions.isNull("timeFinish"))
				.add(Restrictions.isNull("timeAbort"));
		return criteria;
	}

	@Override
	public boolean checkExecute(int worksheetDetailId) {
		String hql = "from WorksheetDetail wsd where wsd.id=:wid and wsd.status=9";
		List<WorksheetDetail> list = currentSession().createQuery(hql)
				.setParameter("wid", worksheetDetailId).list();
		if (!npv.isNull(list)) {
			return true;
		}
		return false;
	}

	@Override
	public void updateIsRead(int id, int i) {
		String hql = "update WorksheetDetail w set w.isRead=:ir where w.id=:id";
		currentSession().createQuery(hql).setParameter("ir", i)
				.setParameter("id", id).executeUpdate();

	}

	@Override
	public List<WorksheetDetail> findOverTime(int wid) {
		Criteria c = currentSession().createCriteria(WorksheetDetail.class);
		c.add(Restrictions.eq("id", wid)).add(Restrictions.eq("isRead", 1));
		return c.list();
	}

	@Override
	public List<WorksheetDetail> findEnd(int wid) {
		Criteria c = currentSession().createCriteria(WorksheetDetail.class);
		c.add(Restrictions.eq("id", wid)).add(Restrictions.eq("status", 5));
		return c.list();
	}

}

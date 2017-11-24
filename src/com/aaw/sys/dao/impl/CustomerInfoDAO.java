package com.aaw.sys.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import molos.plugins.tool.ToolFactory;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.CustomerInfo;
import com.aaw.bean.DeliveryAddress;
import com.aaw.bean.Franchises;
import com.aaw.bean.MainCommande;
import com.aaw.bean.ParametresGeneralSite;
import com.aaw.bean.RefData;
import com.aaw.sys.dao.ICustomerInfoDAO;
import com.aaw.sys.service.IFranchisesService;

@Repository
@SuppressWarnings("unchecked")
public class CustomerInfoDAO extends BaseDAO<CustomerInfo> implements
		ICustomerInfoDAO {

	@Override
	public boolean deleteAndCheck(int id) {

		String hql = "select a.id from DeliveryAddress as a where a.customer.id=:id";

		List<DeliveryAddress> list = currentSession().createQuery(hql)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (!npv.isNull(list)) {
			return false;
		}
		String hql2 = "select a.id from MainCommande as a where a.customer.id=:id";
		List<MainCommande> list2 = currentSession().createQuery(hql2)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (!npv.isNull(list2)) {
			return false;
		}
		this.delete(id);
		return true;

	}

	@SuppressWarnings("unused")
	@Override
	public void updateRF(String codeId) {

		String hql = "from RefData where tableName=2";
		List<RefData> list = currentSession().createQuery(hql).list();
		String now = prefix();
		String reStr = now;
		if (npv.isNull(list)) {
			RefData d = new RefData();
			d.setTableName(2);
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

	private String zero = "0000000";

	@Override
	public String codeId() {
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
		return reStr;
	}

	@Override
	public List<CustomerInfo> list(int i) {
		Criteria c = currentSession().createCriteria(CustomerInfo.class);
		if (i != 0) {
			boolean flag = true;
			Criteria cc = currentSession().createCriteria(
					ParametresGeneralSite.class);
			List<ParametresGeneralSite> list = cc.add(
					Restrictions.eq("parameterName",
							"ControlCustomerFrachiseDisplay")).list();
			if (list != null && !list.isEmpty()) {
				ParametresGeneralSite pts = list.get(0);
				if (pts.getValueNumber() == 0) {
					flag = false;
				}
			}
			List<Integer> is = new ArrayList<Integer>();
			is.add(i);
			if (flag) {
				Franchises f = new Franchises(i);
				List<Franchises> fs = service.findByFran(f);
				for (Franchises r : fs) {
					is.add(r.getId());
				}
			}
			c.add(Restrictions.in("inputChannel", is));
		}
		return c.list();
	}

	@Resource
	private IFranchisesService service;

}
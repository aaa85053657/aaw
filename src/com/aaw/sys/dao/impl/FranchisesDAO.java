package com.aaw.sys.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import molos.plugins.tool.ToolFactory;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.Franchises;
import com.aaw.bean.FranchisesAccount;
import com.aaw.bean.FranchisesAccountAssets;
import com.aaw.bean.FranchisesAssets;
import com.aaw.bean.ParametresGeneralSite;
import com.aaw.bean.RefData;
import com.aaw.sys.dao.IFranchisesDAO;

@Repository
@SuppressWarnings({ "unchecked" })
public class FranchisesDAO extends BaseDAO<Franchises> implements
		IFranchisesDAO {

	@Override
	public boolean clickAssets(FranchisesAccount account, int as) {
		Criteria c = currentSession().createCriteria(
				FranchisesAccountAssets.class);
		List<FranchisesAccountAssets> list = c
				.add(Restrictions.eq("account.id", account.getId()))
				.add(Restrictions.eq("assets.id", as)).list();
		if (list == null || list.isEmpty()) {
			return false;
		}
		return true;
	}

	private String zero = "0000000";

	@Override
	public String showCodeID() {
		String hql = "from RefData where tableName=3";
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

	@Override
	public String codeId() {
		String hql = "from RefData where tableName=3";
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
	public List<Franchises> findByParent(int i) {
		Criteria c = currentSession().createCriteria(Franchises.class);
		return c.add(Restrictions.eq("parentId", i)).list();
	}

	@Override
	public List<FranchisesAssets> findAllAssets() {
		Criteria c = currentSession().createCriteria(FranchisesAssets.class);
		return c.list();
	}

	@Override
	public List<FranchisesAccountAssets> findAssetsByFran(Integer fid) {
		Criteria cf = currentSession().createCriteria(FranchisesAccount.class);
		List<FranchisesAccount> temp = cf.add(
				Restrictions.eq("franchises.id", fid)).list();
		if (temp == null || temp.isEmpty()) {
			return null;
		}
		Criteria c = currentSession().createCriteria(
				FranchisesAccountAssets.class);
		return c.add(Restrictions.eq("account.id", temp.get(0).getId())).list();
	}

	@Override
	public void deleteAccAss(FranchisesAccount bean) {
		String sql = "delete from franchises_account_assets where franchises_account=:fa";
		currentSession().createSQLQuery(sql).setParameter("fa", bean.getId())
				.executeUpdate();

	}

	@Override
	public FranchisesAccount findAccByFran(Franchises franchises) {
		Criteria c = currentSession().createCriteria(FranchisesAccount.class);
		List<FranchisesAccount> list = c.add(
				Restrictions.eq("franchises.id", franchises.getId())).list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<FranchisesAccountAssets> findAssetsByFranAcc(Integer id) {
		Criteria c = currentSession().createCriteria(
				FranchisesAccountAssets.class);
		return c.add(Restrictions.eq("account.id", id)).list();
	}

	@Override
	public List<Object> findAllCode() {
		String sql = "select code_id from franchises";
		List<Object> list = currentSession().createSQLQuery(sql).list();
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list;
	}

	@Override
	public Franchises findByCode(String code) {
		Criteria c = currentSession().createCriteria(Franchises.class);
		List<Franchises> list = c.add(Restrictions.eq("code", code)).list();
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public boolean clickInput() {
		Criteria c = currentSession().createCriteria(
				ParametresGeneralSite.class);
		List<ParametresGeneralSite> list = c.add(
				Restrictions.eq("parameterName", "ControlFranchiseCode"))
				.list();
		if (list != null && !list.isEmpty()
				&& list.get(0).getValueNumber() == 1) {
			return true;
		}
		return false;
	}
}
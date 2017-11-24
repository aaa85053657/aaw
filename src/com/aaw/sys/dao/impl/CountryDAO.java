package com.aaw.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.Country;
import com.aaw.bean.CustomerInfo;
import com.aaw.bean.Employee;
import com.aaw.sys.dao.ICountryDAO;

@Repository
@SuppressWarnings("unchecked")
public class CountryDAO extends BaseDAO<Country> implements ICountryDAO {

	@Override
	public boolean deleteAndCheck(int id) {
		String hql = "select a.id from Employee as a where a.country.id=:id";

		List<Employee> list = currentSession().createQuery(hql)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (!npv.isNull(list)) {
			return false;
		}
		String hql2 = "select a.id from CustomerInfo as a where a.country.id=:id";
		List<CustomerInfo> list2 = currentSession().createQuery(hql2)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (!npv.isNull(list2)) {
			return false;
		}
		this.delete(id);
		return true;
	}
}
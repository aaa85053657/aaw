package com.aaw.sys.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.Employee;
import com.aaw.bean.EmployeeProcedureConfig;
import com.aaw.bean.MainCommande;
import com.aaw.sys.dao.IEmployeeDAO;

@Repository
@SuppressWarnings("unchecked")
public class EmployeeDAO extends BaseDAO<Employee> implements IEmployeeDAO {

	@Override
	public boolean deleteAndCheck(int id) {
		String hql = "select a.id from MainCommande as a where a.seller.id=:id";
		List<MainCommande> list = currentSession().createQuery(hql)
				.setParameter("id", id).setFirstResult(0).setMaxResults(1)
				.list();
		if (npv.isNull(list)) {
			this.delete(id);
			return true;
		}
		return false;

	}

	@Override
	public List<Integer> findProceById(int id) {
		String sql = "select `meta_procedure_id` from `employee_procedure_config` where  `employee_id`=:id";
		SQLQuery query = currentSession().createSQLQuery(sql);
		return query.setParameter("id", id).list();
	}

	@Override
	public List<Integer> findById(int id) {
		String sql = "select `id` from `employee_procedure_config` where  `employee_id`=:id";
		SQLQuery query = currentSession().createSQLQuery(sql);
		return query.setParameter("id", id).list();
	}

	@Override
	public void saveProcedure(EmployeeProcedureConfig params, int[] procedures) {
		String sql = "insert into `employee_procedure_config` (`employee_id`, `meta_procedure_id`) values (:eid, :pid)";
		if (procedures != null && procedures.length != 0) {
			for (int i : procedures) {
				currentSession().createSQLQuery(sql)
						.setParameter("eid", params.getEmployee())
						.setParameter("pid", i).executeUpdate();
			}
		}

	}

	@Override
	public void delProcedure(int id) {
		String sql = "delete from `employee_procedure_config` where (`id`=:id)";
		currentSession().createSQLQuery(sql).setParameter("id", id)
				.executeUpdate();

	}

	@Override
	public Map<String, Object> mapDesc(int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		int total = total2();
		List<Employee> list = mList(page, rows);
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	private List<Employee> mList(int page, int rows) {
		int min = (page - 1) * rows;
		int max = page * rows;
		String hql = "from Employee e order by e.account.id";
		return currentSession().createQuery(hql).setFirstResult(min)
				.setMaxResults(max).list();
	}

	private int total2() {
		Criteria c = currentSession().createCriteria(Employee.class);
		Object o = c.setProjection(Projections.rowCount()).uniqueResult();
		return o == null ? 0 : Integer.valueOf(o.toString());
	}
}
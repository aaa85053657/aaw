package com.aaw.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import molos.plugins.smvc.dao.IDAO;

import com.aaw.base.service.impl.BaseService;
import com.aaw.bean.Employee;
import com.aaw.bean.EmployeeProcedureConfig;
import com.aaw.sys.dao.IEmployeeDAO;
import com.aaw.sys.service.IEmployeeService;

@Service
public class EmployeeService extends BaseService<Employee> implements
		IEmployeeService {

	@Override
	protected IEmployeeDAO getDAO() {
		return (IEmployeeDAO) super.getDAO();
	}

	@Autowired
	@Override
	protected void setDAO(@Qualifier("employeeDAO") IDAO<Employee> dao) {
		super.setDAO(dao);
	}

	@Override
	public List<Map<String, Object>> combobox() {
		List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
		List<Employee> list = list();
		for (Employee a : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", a.getId());
			map.put("text", a.getName());
			reList.add(map);
		}
		return reList;
	}

	@Override
	public boolean deleteAndCheck(int id) {
		return getDAO().deleteAndCheck(id);
	}

	@Override
	public List<Integer> findProceById(int id) {
		return getDAO().findProceById(id);
	}

	@Override
	public void saveProcedure(EmployeeProcedureConfig params, int[] procedures) {
		getDAO().saveProcedure(params, procedures);

	}

	@Override
	public void delProcedure(int id) {
		getDAO().delProcedure(id);

	}

	@Override
	public List<Integer> findById(int id) {
		return getDAO().findById(id);
	}

	@Override
	public Object mapDesc(int page, int rows) {
		return getDAO().mapDesc(page, rows);
	}
}
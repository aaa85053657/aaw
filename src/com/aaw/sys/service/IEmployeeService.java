package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.Employee;
import com.aaw.bean.EmployeeProcedureConfig;

public interface IEmployeeService extends IBaseService<Employee> {

	List<Map<String, Object>> combobox();

	boolean deleteAndCheck(int id);

	List<Integer> findProceById(int id);

	void saveProcedure(EmployeeProcedureConfig params, int[] procedures);

	void delProcedure(int id);

	List<Integer> findById(int id);

	Object mapDesc(int page, int rows);
}
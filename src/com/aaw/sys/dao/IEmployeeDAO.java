package com.aaw.sys.dao;

import java.util.List;

import com.aaw.base.dao.IBaseDAO;
import com.aaw.bean.Employee;
import com.aaw.bean.EmployeeProcedureConfig;

public interface IEmployeeDAO extends IBaseDAO<Employee> {

	boolean deleteAndCheck(int id);

	List<Integer> findProceById(int id);

	void saveProcedure(EmployeeProcedureConfig params, int[] procedures);

	void delProcedure(int id);

	List<Integer> findById(int id);

	Object mapDesc(int page, int rows);
}
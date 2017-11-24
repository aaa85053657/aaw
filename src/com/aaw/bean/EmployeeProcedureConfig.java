package com.aaw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aaw.bean.base.BaseBean;

/**
 * @author
 *
 */
@Entity
@Table(name = "employee_procedure_config")
public class EmployeeProcedureConfig extends BaseBean {
	/**
 	 *
 	 */
	@Column(name = "employee_id")
	private Integer employee;
	/**
 	 *
 	 */
	@Column(name = "meta_procedure_id")
	private Integer procedure;

	public Integer getEmployee() {
		return employee;
	}

	public void setEmployee(Integer employee) {
		this.employee = employee;
	}

	public Integer getProcedure() {
		return procedure;
	}

	public void setProcedure(Integer procedure) {
		this.procedure = procedure;
	}

	public EmployeeProcedureConfig() {
	}
}
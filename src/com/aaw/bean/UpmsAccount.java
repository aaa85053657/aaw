package com.aaw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aaw.bean.base.BaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author
 *
 */
@Entity
@Table(name = "upms_account")
public class UpmsAccount extends BaseBean {
	/**
 	 *
 	 */
	@Column(name = "name")
	private String name;
	/**
 	 *
 	 */
	@Column(name = "pazzwd")
	private String pazzwd;

	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "upms_role_id")
	private UpmsRole role;

	@OneToOne(mappedBy = "account")
	private Employee employee;

	@Transient
	private String empName;

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@JsonIgnore
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public UpmsRole getRole() {
		return role;
	}

	public void setRole(UpmsRole role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public String getPazzwd() {
		return pazzwd;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPazzwd(String pazzwd) {
		this.pazzwd = pazzwd;
	}

	public UpmsAccount() {
	}

	public UpmsAccount(Integer id) {
		this.id = id;
	}

	public UpmsAccount(String name, String pazzwd, UpmsRole role) {
		this.name = name;
		this.pazzwd = pazzwd;
		this.role = role;
	}

}
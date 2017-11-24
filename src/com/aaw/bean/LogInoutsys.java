package com.aaw.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.molos.timer.impl.DateTimeSerl;

import com.aaw.bean.base.BaseBean;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author
 *
 */
@Entity
@Table(name = "log_inoutsys")
public class LogInoutsys extends BaseBean {
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "country_id")
	private Employee employee;
	/**
 	 *
 	 */
	@Column(name = "time_creation")
	private Date timeCreation;
	/**
 	 *
 	 */
	@Column(name = "ip_from")
	private String ipFrom;
	/**
 	 *
 	 */
	@Column(name = "brower_info")
	private String browerInfo;
	/**
 	 *
 	 */
	@Column(name = "type")
	private Integer type;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeCreation() {
		return timeCreation;
	}

	public void setTimeCreation(Date timeCreation) {
		this.timeCreation = timeCreation;
	}

	public String getIpFrom() {
		return ipFrom;
	}

	public void setIpFrom(String ipFrom) {
		this.ipFrom = ipFrom;
	}

	public String getBrowerInfo() {
		return browerInfo;
	}

	public void setBrowerInfo(String browerInfo) {
		this.browerInfo = browerInfo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public LogInoutsys() {
	}
}
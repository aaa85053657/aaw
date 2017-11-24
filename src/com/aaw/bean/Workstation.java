package com.aaw.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.molos.timer.impl.DateTimeSerl;

import com.aaw.bean.base.BaseBean;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "workstation")
public class Workstation extends BaseBean {

	/**
	 *
	 */
	@Column(name = "code_id")
	private String codeId;
	/**
	 *
	 */
	@Column(name = "name")
	private String name;
	/**
	 *
	 */
	@Column(name = "comments")
	private String comments;

	@Column(name = "parameter")
	private String parameter;

	/**
 	 *
 	 */
	@Column(name = "time_creation")
	private Date timeCreation;
	/**
 	 *
 	 */
	@Column(name = "time_modification")
	private Date timeModification;
	/**
 	 *
 	 */
	@Column(name = "time_delete")
	private Date timeDelete;

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeCreation() {
		return timeCreation;
	}

	public void setTimeCreation(Date timeCreation) {
		this.timeCreation = timeCreation;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeModification() {
		return timeModification;
	}

	public void setTimeModification(Date timeModification) {
		this.timeModification = timeModification;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeDelete() {
		return timeDelete;
	}

	public void setTimeDelete(Date timeDelete) {
		this.timeDelete = timeDelete;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public Workstation() {
	}

	public Workstation(Integer id) {
		super(id);
	}

}

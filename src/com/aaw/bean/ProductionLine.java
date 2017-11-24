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
@Table(name = "production_line")
public class ProductionLine extends BaseBean {
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
	@Column(name = "averagetime")
	private Integer averagetime;
	/**
 	 *
 	 */
	@Column(name = "comments")
	private String comments;

	public String getCodeId() {
		return codeId;
	}

	public String getName() {
		return name;
	}

	public Integer getAveragetime() {
		return averagetime;
	}

	public String getComments() {
		return comments;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAveragetime(Integer averagetime) {
		this.averagetime = averagetime;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public ProductionLine() {
	}

	public ProductionLine(int id) {
		super(id);
	}
}
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
@Table(name = "country")
public class Country extends BaseBean {
	/**
 	 *
 	 */
	@Column(name = "code_id")
	private String codeId;
	/**
 	 *
 	 */
	@Column(name = "short_code")
	private String shortCode;
	/**
 	 *
 	 */
	@Column(name = "area_code")
	private String areaCode;
	/**
 	 *
 	 */
	@Column(name = "name")
	private String name;

	public String getCodeId() {
		return codeId;
	}

	public String getShortCode() {
		return shortCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getName() {
		return name;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country() {
	}
}
package com.aaw.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aaw.bean.base.BaseBean;

@Entity
@Table(name = "parametres_general_site")
public class ParametresGeneralSite extends BaseBean {

	@Column(name = "parameter_name")
	private String parameterName;

	@Column(name = "parameter_type")
	private Integer parameterType;

	@Column(name = "value_varchar")
	private String valueVarchar;

	@Column(name = "value_date")
	private Date valueDate;

	@Column(name = "value_number")
	private Integer valueNumber;

	@Column(name = "description")
	private String description;

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public Integer getParameterType() {
		return parameterType;
	}

	public void setParameterType(Integer parameterType) {
		this.parameterType = parameterType;
	}

	public String getValueVarchar() {
		return valueVarchar;
	}

	public void setValueVarchar(String valueVarchar) {
		this.valueVarchar = valueVarchar;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public Integer getValueNumber() {
		return valueNumber;
	}

	public void setValueNumber(Integer valueNumber) {
		this.valueNumber = valueNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

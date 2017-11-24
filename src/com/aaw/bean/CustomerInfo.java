package com.aaw.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

import cn.molos.timer.impl.DateTimeSerl;

import com.aaw.bean.base.BaseBean;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author
 *
 */
@Entity
@Table(name = "customer_info")
@DynamicUpdate(true)
public class CustomerInfo extends BaseBean {
	/**
 	 *
 	 */
	@Column(name = "first_name")
	private String firstName;
	/**
 	 *
 	 */
	@Column(name = "middle_name")
	private String middleName;
	/**
 	 *
 	 */
	@Column(name = "last_name")
	private String lastName;
	/**
 	 *
 	 */
	@Column(name = "sex")
	private Integer sex;
	/**
 	 *
 	 */
	@Column(name = "email")
	private String email;
	/**
 	 *
 	 */
	@Column(name = "fax")
	private String fax;
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
	/**
 	 *
 	 */
	@Column(name = "code_id")
	private String codeId;
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "country_id")
	private Country country;

	/**
 	 *
 	 */
	@Column(name = "telephone")
	private String telephone;
	/**
 	 *
 	 */
	@Column(name = "mobile")
	private String mobile;

	/**
	 *
	 */
	@Column(name = "customer_type")
	private Integer customerType;
	/**
	 *
	 */
	@Column(name = "input_channel")
	private Integer inputChannel;

	@Transient
	private String ctName;

	@Transient
	private String icName;

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public Integer getSex() {
		return sex;
	}

	public String getEmail() {
		return email;
	}

	public String getFax() {
		return fax;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeCreation() {
		return timeCreation;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeModification() {
		return timeModification;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeDelete() {
		return timeDelete;
	}

	public String getCodeId() {
		return codeId;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setTimeCreation(Date timeCreation) {
		this.timeCreation = timeCreation;
	}

	public void setTimeModification(Date timeModification) {
		this.timeModification = timeModification;
	}

	public void setTimeDelete(Date timeDelete) {
		this.timeDelete = timeDelete;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public Integer getInputChannel() {
		return inputChannel;
	}

	public void setInputChannel(Integer inputChannel) {
		this.inputChannel = inputChannel;
	}

	public String getCtName() {
		return ctName;
	}

	public void setCtName(String ctName) {
		this.ctName = ctName;
	}

	public String getIcName() {
		return icName;
	}

	public void setIcName(String icName) {
		this.icName = icName;
	}

	public CustomerInfo() {
	}
}
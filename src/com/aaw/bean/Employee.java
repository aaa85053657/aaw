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
@Table(name = "employee")
public class Employee extends BaseBean {
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
	@Column(name = "sex")
	private Integer sex;
	/**
	 *
	 */
	@Column(name = "age")
	private Integer age;
	/**
	 *
	 */
	@Column(name = "email")
	private String email;
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
	@Column(name = "is_incumbent")
	private Integer isIncumbent;
	/**
	 *
	 */
	@Column(name = "fax")
	private String fax;
	/**
	 *
	 */
	@Column(name = "address")
	private String address;
	/**
	 *
	 */
	@Column(name = "company_address")
	private String companyAddress;
	/**
	 *
	 */
	@Column(name = "company")
	private String company;
	/**
	 * 员工工牌号唯一
	 */
	@Column(name = "badge_code", updatable = false)
	private String badgeCode = new String(System.currentTimeMillis() + "");
	/**
	 * 是否允许创建订单,1允许
	 */
	@Column(name = "is_staff")
	private Integer isStaff;

	@OneToOne
	@JoinColumn(name = "upms_account_id", updatable = false)
	private UpmsAccount account;

	@OneToOne
	@JoinColumn(name = "country_id")
	private Country country;

	// /**
	// * 双向关联技能分配信息
	// */
	// @OneToMany
	// @JoinTable(name = "employee_procedure_config", inverseJoinColumns = {
	// @JoinColumn(name = "employee_id") })
	// private List<MetaProcedure> procedures;

	public String getCodeId() {
		return codeId;
	}

	public Integer getSex() {
		return sex;
	}

	public Integer getAge() {
		return age;
	}

	public String getEmail() {
		return email;
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

	public Integer getIsIncumbent() {
		return isIncumbent;
	}

	public String getFax() {
		return fax;
	}

	public String getAddress() {
		return address;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public String getCompany() {
		return company;
	}

	public Integer getIsStaff() {
		return isStaff;
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

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public void setIsIncumbent(Integer isIncumbent) {
		this.isIncumbent = isIncumbent;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setIsStaff(Integer isStaff) {
		this.isStaff = isStaff;
	}

	public UpmsAccount getAccount() {
		return account;
	}

	public void setAccount(UpmsAccount account) {
		this.account = account;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getBadgeCode() {
		return badgeCode;
	}

	public void setBadgeCode(String badgeCode) {
		this.badgeCode = badgeCode;
	}

	public Employee(String codeId, String name, Integer sex, String email, Date timeCreation, Integer isStaff) {
		super();
		this.codeId = codeId;
		this.name = name;
		this.sex = sex;
		this.email = email;
		this.timeCreation = timeCreation;
		this.isStaff = isStaff;
	}

	public Employee() {
	}

	public Employee(Integer id) {
		this.id = id;
	}

}
package com.aaw.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.aaw.bean.base.BaseBean;

/**
 * @author 加盟商账户
 */
@Entity
@Table(name = "franchises_account")
public class FranchisesAccount extends BaseBean {

	/**
	 * 账户
	 */
	@Column(name = "account")
	private String account;

	/**
	 * 密码
	 */
	@Column(name = "pazzword")
	private String pazzword;

	/**
	 * 加盟商
	 */
	@OneToOne
	@JoinColumn(name = "franchises_id")
	private Franchises franchises;

	/**
	 * 创建时间
	 */
	@Column(name = "creation_time")
	private Date createTime;

	/**
	 * 修改时间
	 */
	@Column(name = "modification_time")
	private Date modificationTime;

	/**
	 * 删除时间
	 */
	@Column(name = "deletion_time")
	private Date deleteTime;

	/**
	 * 状态
	 */
	@Column(name = "status")
	private Integer status;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPazzword() {
		return pazzword;
	}

	public void setPazzword(String pazzword) {
		this.pazzword = pazzword;
	}

	public Franchises getFranchises() {
		return franchises;
	}

	public void setFranchises(Franchises franchises) {
		this.franchises = franchises;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}

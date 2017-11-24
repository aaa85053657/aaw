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
@Table(name = "franchises_account_assets")
public class FranchisesAccountAssets extends BaseBean {

	/**
	 * 账户
	 */
	@OneToOne
	@JoinColumn(name = "franchises_account")
	private FranchisesAccount account;

	/**
	 * 权限
	 */
	@OneToOne
	@JoinColumn(name = "franchises_assets")
	private FranchisesAssets assets;

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

	public FranchisesAccount getAccount() {
		return account;
	}

	public void setAccount(FranchisesAccount account) {
		this.account = account;
	}

	public FranchisesAssets getAssets() {
		return assets;
	}

	public void setAssets(FranchisesAssets assets) {
		this.assets = assets;
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

}

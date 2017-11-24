package com.aaw.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.aaw.bean.base.BaseBean;

/**
 * @author 加盟商权限
 */
@Entity
@Table(name = "franchises_assets")
public class FranchisesAssets extends BaseBean {

	/**
	 * 权限名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 权限描述
	 */
	@Column(name = "describe")
	private String describe;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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

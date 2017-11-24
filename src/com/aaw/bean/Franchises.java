package com.aaw.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.molos.timer.impl.DateTimeSerl;

import com.aaw.bean.base.BaseBean;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author 加盟商
 */
@Entity
@Table(name = "franchises")
public class Franchises extends BaseBean {

	/**
	 * 编号
	 */
	@Column(name = "code_id")
	private String code;

	/**
	 * 名称
	 */
	@Column(name = "name")
	private String name;

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
	 * 描述
	 */
	@Column(name = "description")
	private String description;

	/**
	 * 地址
	 */
	@Column(name = "address")
	private String address;

	/**
	 * 联系人名称
	 */
	@Column(name = "contact_name")
	private String contactName;

	/**
	 * 联系人电话
	 */
	@Column(name = "contact_mobile")
	private String contactNumber;

	/**
	 * 联系人邮箱
	 */
	@Column(name = "contact_email")
	private String contactEmail;

	/**
	 * 联系人座机
	 */
	@Column(name = "contact_phone")
	private String contactPhone;

	/**
	 * 类别
	 */
	@Column(name = "category")
	private Integer category;

	/**
	 * 状态 0未激活 1激活 9删除
	 */
	@Column(name = "state")
	private Integer state;

	/**
	 * 加盟商等级
	 */
	@Column(name = "franchise_level")
	private Integer franchiseLevel;

	/**
	 * 父id
	 */
	@Column(name = "parent_id")
	private Integer parentId;

	/**
	 * 是否创建账号 1未创建 2创建
	 */
	@Column(name = "is_create_acc")
	private Integer isCreateAcc = 1;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getFranchiseLevel() {
		return franchiseLevel;
	}

	public void setFranchiseLevel(Integer franchiseLevel) {
		this.franchiseLevel = franchiseLevel;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getIsCreateAcc() {
		return isCreateAcc;
	}

	public void setIsCreateAcc(Integer isCreateAcc) {
		this.isCreateAcc = isCreateAcc;
	}

	public Franchises(Integer fid) {
		this.id = fid;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Franchises() {

	}

}

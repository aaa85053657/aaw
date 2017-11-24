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
@Table(name = "role_assets_config")
public class RoleAssetsConfig extends BaseBean {
	/**
 	 *
 	 */
	@Column(name = "upms_assets_id")
	private Integer upmsAssetsId;
	/**
 	 *
 	 */
	@Column(name = "upms_role_id")
	private Integer upmsRoleId;

	public Integer getUpmsAssetsId() {
		return upmsAssetsId;
	}

	public Integer getUpmsRoleId() {
		return upmsRoleId;
	}

	public void setUpmsAssetsId(Integer upmsAssetsId) {
		this.upmsAssetsId = upmsAssetsId;
	}

	public void setUpmsRoleId(Integer upmsRoleId) {
		this.upmsRoleId = upmsRoleId;
	}

	public RoleAssetsConfig(Integer upmsAssetsId, Integer upmsRoleId) {
		super();
		this.upmsAssetsId = upmsAssetsId;
		this.upmsRoleId = upmsRoleId;
	}

	public RoleAssetsConfig() {
	}
}
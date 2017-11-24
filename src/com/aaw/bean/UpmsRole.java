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
@Table(name = "upms_role")
public class UpmsRole extends BaseBean {
	// /**
	// * 双向关联资源信息
	// */
	// @OneToMany(fetch = FetchType.EAGER)
	// @JoinTable(name = "role_assets_config", inverseJoinColumns = {
	// @JoinColumn(name = "id") }, joinColumns = { @JoinColumn(name =
	// "upms_role_id") })
	// private List<UpmsAssets> assets;
	/**
	 *
	 */
	@Column(name = "name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UpmsRole(String name) {
		super();
		this.name = name;
	}

	public UpmsRole() {
	}

	public UpmsRole(int roleId) {
		this.id = roleId;
	}
}
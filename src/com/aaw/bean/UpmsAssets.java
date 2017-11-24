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
@Table(name = "upms_assets")
public class UpmsAssets extends BaseBean {

	/**
 	 *
 	 */
	@Column(name = "module_name")
	private String moduleName;
	/**
 	 *
 	 */
	@Column(name = "module_path")
	private String modulePath;
	/**
 	 *
 	 */
	@Column(name = "type")
	private Integer type;
	/**
 	 *
 	 */
	@Column(name = "parent")
	private Integer parent;
	/**
 	 *
 	 */
	@Column(name = "icon")
	private String icon;
	/**
 	 *
 	 */
	@Column(name = "codogram")
	private String codogram;
	/**
 	 *
 	 */
	@Column(name = "order_value")
	private Integer orderValue;

	public String getModuleName() {
		return moduleName;
	}

	public String getModulePath() {
		return modulePath;
	}

	public Integer getType() {
		return type;
	}

	public Integer getParent() {
		return parent;
	}

	public String getIcon() {
		return icon;
	}

	public String getCodogram() {
		return codogram;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setCodogram(String codogram) {
		this.codogram = codogram;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public UpmsAssets() {
	}
}
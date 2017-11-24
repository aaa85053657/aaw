package com.aaw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.aaw.bean.base.BaseBean;

/**
 * @author
 *
 */
@Entity
@Table(name = "product_attribute")
public class ProductAttribute extends BaseBean {
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
	@Column(name = "comments")
	private String comments;

	/**
	 * 属性种类
	 */
	@Column(name = "attribute_type")
	private Integer attributeType;

	/**
	 * 组件
	 */
	@OneToOne
	@JoinColumn(name = "component_id")
	private ProductComponent component;

	@Column(name = "path")
	private String path;

	public String getCodeId() {
		return codeId;
	}

	public String getName() {
		return name;
	}

	public String getComments() {
		return comments;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(Integer attributeType) {
		this.attributeType = attributeType;
	}

	public ProductComponent getComponent() {
		return component;
	}

	public void setComponent(ProductComponent component) {
		this.component = component;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ProductAttribute() {
	}
}
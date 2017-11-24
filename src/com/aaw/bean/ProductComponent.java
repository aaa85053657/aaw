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
@Table(name = "product_component")
public class ProductComponent extends BaseBean {
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

	public ProductComponent() {
	}

	public ProductComponent(Integer id) {
		super(id);
	}
}
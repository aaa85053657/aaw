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
@Table(name = "product_element")
public class ProductElement extends BaseBean {
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
	 *
	 */
	@Column(name = "samplepath")
	private String samplepath;
	/**
	 *
	 */
	@Column(name = "pixel")
	private String pixel;
	/**
	 *
	 */
	@OneToOne
	@JoinColumn(name = "product_attribute_id")
	private ProductAttribute attribute;

	public ProductAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(ProductAttribute attribute) {
		this.attribute = attribute;
	}

	public String getCodeId() {
		return codeId;
	}

	public String getName() {
		return name;
	}

	public String getComments() {
		return comments;
	}

	public String getSamplepath() {
		return samplepath;
	}

	public String getPixel() {
		return pixel;
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

	public void setSamplepath(String samplepath) {
		this.samplepath = samplepath;
	}

	public void setPixel(String pixel) {
		this.pixel = pixel;
	}

	public ProductElement() {
	}

	public ProductElement(int id, String codeId, String name, String comments,
			String samplepath, String pixel, ProductAttribute attribute) {
		super();
		this.id = id;
		this.codeId = codeId;
		this.name = name;
		this.comments = comments;
		this.samplepath = samplepath;
		this.pixel = pixel;
		this.attribute = attribute;
	}

	public ProductElement(int id) {
		super(id);
	}
}
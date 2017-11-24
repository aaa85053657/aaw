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
@Table(name = "product_model_config")
public class ProductModelConfig extends BaseBean {
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "product_component_id")
	private ProductComponent component;
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "product_model_id")
	private ProductModel model;
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "product_element_id")
	private ProductElement element;
	/**
	 * 1左 2右 3左右
	 */
	@Column(name = "position")
	private Integer position;

	public ProductComponent getComponent() {
		return component;
	}

	public void setComponent(ProductComponent component) {
		this.component = component;
	}

	public ProductModel getModel() {
		return model;
	}

	public void setModel(ProductModel model) {
		this.model = model;
	}

	public ProductElement getElement() {
		return element;
	}

	public void setElement(ProductElement element) {
		this.element = element;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public ProductModelConfig() {
	}

	public ProductModelConfig(int componentID, int position, int eleID,
			int modeID) {
		this.component = new ProductComponent(componentID);
		this.position = position;
		this.element = new ProductElement(eleID);
		this.model = new ProductModel(modeID);

	}
}
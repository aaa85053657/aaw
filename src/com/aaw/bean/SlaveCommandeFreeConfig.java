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
@Table(name = "slave_commande_free_config")
public class SlaveCommandeFreeConfig extends BaseBean {
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "second_commande_id")
	private SlaveCommande slaveCommande;
	/**
 	 *
 	 */
	@Column(name = "product_element_left")
	private String elementLeft;

	/**
	 *
	 */
	@Column(name = "product_element_right")
	private String elementRight;

	@OneToOne
	@JoinColumn(name = "product_attribute_id")
	private ProductAttribute attribute;

	/**
	 *
	 */
	@OneToOne
	@JoinColumn(name = "product_component_id")
	private ProductComponent component;

	public SlaveCommande getSlaveCommande() {
		return slaveCommande;
	}

	public void setSlaveCommande(SlaveCommande slaveCommande) {
		this.slaveCommande = slaveCommande;
	}

	public ProductComponent getComponent() {
		return component;
	}

	public void setComponent(ProductComponent component) {
		this.component = component;
	}

	public String getElementLeft() {
		return elementLeft;
	}

	public void setElementLeft(String elementLeft) {
		this.elementLeft = elementLeft;
	}

	public String getElementRight() {
		return elementRight;
	}

	public void setElementRight(String elementRight) {
		this.elementRight = elementRight;
	}

	public ProductAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(ProductAttribute attribute) {
		this.attribute = attribute;
	}

	public SlaveCommandeFreeConfig() {
	}
}
package com.aaw.bean;

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
@Table(name = "slave_commande_config")
public class SlaveCommandeConfig extends BaseBean {
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "second_commande_id")
	private SlaveCommande slaveCommande;
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "product_element_left_id")
	private ProductElement elementLeft;

	/**
	 *
	 */
	@OneToOne
	@JoinColumn(name = "product_element_right_id")
	private ProductElement elementRight;

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

	public ProductElement getElementLeft() {
		return elementLeft;
	}

	public void setElementLeft(ProductElement elementLeft) {
		this.elementLeft = elementLeft;
	}

	public ProductElement getElementRight() {
		return elementRight;
	}

	public void setElementRight(ProductElement elementRight) {
		this.elementRight = elementRight;
	}

	public SlaveCommandeConfig() {
	}
}
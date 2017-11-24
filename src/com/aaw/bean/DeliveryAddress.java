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
@Table(name = "delivery_address")
public class DeliveryAddress extends BaseBean {
	/**
 	 *
 	 */
	@Column(name = "address")
	private String address;
	/**
 	 *
 	 */
	@Column(name = "poste_code")
	private String posteCode;
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "customer_info_id")
	private CustomerInfo customer;

	public String getAddress() {
		return address;
	}

	public String getPosteCode() {
		return posteCode;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPosteCode(String posteCode) {
		this.posteCode = posteCode;
	}

	public CustomerInfo getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerInfo customer) {
		this.customer = customer;
	}

	public DeliveryAddress() {
	}
}
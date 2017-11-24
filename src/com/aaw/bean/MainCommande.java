package com.aaw.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.molos.timer.impl.DateTimeSerl;

import com.aaw.bean.base.BaseBean;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author
 *
 */
@Entity
@Table(name = "main_commande")
public class MainCommande extends BaseBean {
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "commande_priority_id")
	private CommandePriority priority;

	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "commande_type_id")
	private CommandeType type;
	/**
	 * 邮寄地址，需要根据选择的客户来确定
	 */
	@OneToOne
	@JoinColumn(name = "delivery_address_id")
	private DeliveryAddress address;
	/**
 	 *
 	 */
	@Column(name = "code_id", updatable = false)
	private String codeId;
	/**
	 * 第三ID
	 */
	@Column(name = "external_code")
	private String externalCode;
	/**
 	 *
 	 */
	@Column(name = "time_creation")
	private Date timeCreation;
	/**
 	 *
 	 */
	@Column(name = "time_modification")
	private Date timeModification;
	/**
 	 *
 	 */
	@Column(name = "time_delete")
	private Date timeDelete;
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "customer_info_id")
	private CustomerInfo customer;
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee seller;

	/**
	 * 型号
	 */
	@Transient
	private String col1;
	/**
	 * 进度
	 */
	@Transient
	private String col2;
	/**
	 * 生产线
	 */
	@Transient
	private String col3;
	/**
	 * 工序状态
	 */
	@Transient
	private Integer col4;
	/**
	 * 订单状态(1.信息不全 2默认状态 3状态变更 4发往工厂 )
	 */
	@Column(name = "order_status")
	private Integer orderStatus;
	/**
	 * 加工单状态
	 */
	@Transient
	private Integer col6;

	/**
	 * 当前阶段
	 */
	@Transient
	private String col7;

	@Transient
	private String col8;

	/**
	 * 加盟商id
	 */
	@Transient
	private Franchises fid;

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3;
	}

	public String getExternalCode() {
		return externalCode;
	}

	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}

	public DeliveryAddress getAddress() {
		return address;
	}

	public void setAddress(DeliveryAddress address) {
		this.address = address;
	}

	public CommandePriority getPriority() {
		return priority;
	}

	public void setPriority(CommandePriority priority) {
		this.priority = priority;
	}

	public CommandeType getType() {
		return type;
	}

	public void setType(CommandeType type) {
		this.type = type;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeCreation() {
		return timeCreation;
	}

	public void setTimeCreation(Date timeCreation) {
		this.timeCreation = timeCreation;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeModification() {
		return timeModification;
	}

	public void setTimeModification(Date timeModification) {
		this.timeModification = timeModification;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeDelete() {
		return timeDelete;
	}

	public void setTimeDelete(Date timeDelete) {
		this.timeDelete = timeDelete;
	}

	public CustomerInfo getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerInfo customer) {
		this.customer = customer;
	}

	public Employee getSeller() {
		return seller;
	}

	public void setSeller(Employee seller) {
		this.seller = seller;
	}

	public MainCommande() {
	}

	public MainCommande(int id) {
		super(id);
	}

	public Integer getCol4() {
		return col4;
	}

	public void setCol4(Integer col4) {
		this.col4 = col4;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getCol6() {
		return col6;
	}

	public void setCol6(Integer col6) {
		this.col6 = col6;
	}

	public String getCol7() {
		return col7;
	}

	public void setCol7(String col7) {
		this.col7 = col7;
	}

	public String getCol8() {
		return col8;
	}

	public void setCol8(String col8) {
		this.col8 = col8;
	}

	public Franchises getFid() {
		return fid;
	}

	public void setFid(Franchises fid) {
		this.fid = fid;
	}

}
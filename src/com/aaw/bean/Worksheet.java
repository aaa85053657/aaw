package com.aaw.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.molos.timer.impl.DateTimeSerl;

import com.aaw.bean.base.BaseBean;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author
 *
 */
@Entity
@Table(name = "worksheet")
public class Worksheet extends BaseBean {
	/**
	 * 序列号
	 */
	@Column(name = "serial_number")
	private String serialNumber;
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
	@JoinColumn(name = "slave_commande_id")
	private SlaveCommande slaveCommande;

	/**
	 * 完成时间
	 */
	@Column(name = "time_finish")
	private Date timeFinish;
	/**
	 * 发货时间
	 */
	@Column(name = "time_delivery")
	private Date timeDelivery;
	/**
	 * 顾客签收时间
	 */
	@Column(name = "time_receive")
	private Date timeReceive;
	/**
	 * 加工单终止时间
	 */
	@Column(name = "time_abort")
	private Date timeAbort;

	/**
	 * 当前操作人
	 */
	@OneToOne
	@JoinColumn(name = "opr_employee")
	private Employee opr;

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

	public SlaveCommande getSlaveCommande() {
		return slaveCommande;
	}

	public void setSlaveCommande(SlaveCommande slaveCommande) {
		this.slaveCommande = slaveCommande;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeFinish() {
		return timeFinish;
	}

	public void setTimeFinish(Date timeFinish) {
		this.timeFinish = timeFinish;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeDelivery() {
		return timeDelivery;
	}

	public void setTimeDelivery(Date timeDelivery) {
		this.timeDelivery = timeDelivery;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeReceive() {
		return timeReceive;
	}

	public void setTimeReceive(Date timeReceive) {
		this.timeReceive = timeReceive;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeAbort() {
		return timeAbort;
	}

	public void setTimeAbort(Date timeAbort) {
		this.timeAbort = timeAbort;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Employee getOpr() {
		return opr;
	}

	public void setOpr(Employee opr) {
		this.opr = opr;
	}

	public Worksheet() {
	}

	public Worksheet(int id) {
		super(id);
	}

}
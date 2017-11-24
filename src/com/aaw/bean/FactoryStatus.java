package com.aaw.bean;

import java.util.Date;

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
@Table(name = "factory_status")
public class FactoryStatus extends BaseBean {

	/**
	 * 订单？
	 */
	@OneToOne
	@JoinColumn(name = "slave_commande_id")
	private SlaveCommande slaveCommande;

	/**
	 * 加工单？
	 */
	@OneToOne
	@JoinColumn(name = "worksheet_id")
	private Worksheet worksheet;

	/**
	 * 最后一次修改时间
	 */
	@Column(name = "modification_times")
	private Date modificationTime;
	/**
	 * 订单当前完成状态
	 */
	private int status = 0;

	/**
	 * 总工序数
	 */
	@Column(name = "status_count")
	private int statusCount = 0;
	/**
	 * 本环节当前状态
	 */
	@Column(name = "current_status")
	private Integer currentStatus;
	/**
	 * 下一工序编号
	 */
	@Column(name = "next_proceduce")
	private Integer nextProceduce;

	/**
	 * 加工单（5.创建加工单 6开始加工 7终止加工 8删除加工 9重置）
	 */
	@Column(name = "work_status")
	private Integer workStatus;

	/**
	 * 当前工序操作状态(10.下一道工序未开始，11下一道工序已开始 12完成)
	 */
	@Column(name = "opt_status")
	private Integer optStatus;

	public SlaveCommande getSlaveCommande() {
		return slaveCommande;
	}

	public void setSlaveCommande(SlaveCommande slaveCommande) {
		this.slaveCommande = slaveCommande;
	}

	public Worksheet getWorksheet() {
		return worksheet;
	}

	public void setWorksheet(Worksheet worksheet) {
		this.worksheet = worksheet;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatusCount() {
		return statusCount;
	}

	public void setStatusCount(int statusCount) {
		this.statusCount = statusCount;
	}

	public Integer getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Integer currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Integer getNextProceduce() {
		return nextProceduce;
	}

	public void setNextProceduce(Integer nextProceduce) {
		this.nextProceduce = nextProceduce;
	}

	public Integer getOptStatus() {
		return optStatus;
	}

	public void setOptStatus(Integer optStatus) {
		this.optStatus = optStatus;
	}

	public Integer getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(Integer workStatus) {
		this.workStatus = workStatus;
	}

}
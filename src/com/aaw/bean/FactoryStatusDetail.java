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
@Table(name = "factory_status_detail")
public class FactoryStatusDetail extends BaseBean {

	@OneToOne
	@JoinColumn(name = "factory_status_id")
	private FactoryStatus factoryStatus;

	/**
	 * 最后一次修改时间
	 */
	@Column(name = "modification_times")
	private Date modificationTime;
	/**
	 * 订单当前完成状态
	 */
	@Column(name = "status")
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

	@OneToOne
	@JoinColumn(name = "worksheet_detail_id")
	private WorksheetDetail worksheetDetail;

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

	public FactoryStatus getFactoryStatus() {
		return factoryStatus;
	}

	public void setFactoryStatus(FactoryStatus factoryStatus) {
		this.factoryStatus = factoryStatus;
	}

	public WorksheetDetail getWorksheetDetail() {
		return worksheetDetail;
	}

	public void setWorksheetDetail(WorksheetDetail worksheetDetail) {
		this.worksheetDetail = worksheetDetail;
	}

}
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

@Entity
@Table(name = "worksheet_exception")
public class WorksheetException extends BaseBean {
	@OneToOne
	@JoinColumn(name = "worksheet_detail_id")
	private WorksheetDetail worksheetDetail;
	@Column(name = "roll_back")
	private String rollBack;
	@Column(name = "date_creation")
	private Date createTime;
	@Column(name = "exception_reason")
	private String exceptionReason;
	@Column(name = "other_reason")
	private String otherReason;

	public WorksheetDetail getWorksheetDetail() {
		return worksheetDetail;
	}

	public void setWorksheetDetail(WorksheetDetail worksheetDetail) {
		this.worksheetDetail = worksheetDetail;
	}

	public String getRollBack() {
		return rollBack;
	}

	public void setRollBack(String rollBack) {
		this.rollBack = rollBack;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getExceptionReason() {
		return exceptionReason;
	}

	public void setExceptionReason(String exceptionReason) {
		this.exceptionReason = exceptionReason;
	}

	public String getOtherReason() {
		return otherReason;
	}

	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}

	public WorksheetException() {
	}

	public WorksheetException(Integer id) {
		this.id = id;
	}

	public WorksheetException(WorksheetDetail worksheetDetail, String rollBack,
			Date createTime, String exceptionReason, String otherReason) {
		this.worksheetDetail = worksheetDetail;
		this.rollBack = rollBack;
		this.createTime = createTime;
		this.exceptionReason = exceptionReason;
		this.otherReason = otherReason;
	}

}

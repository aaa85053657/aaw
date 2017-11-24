package com.aaw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.aaw.bean.base.BaseBean;

@Entity
@Table(name = "worksheet_history")
public class WorksheetHistory extends BaseBean {
	@OneToOne
	@JoinColumn(name = "worksheet_detail_id")
	private WorksheetDetail worksheetDetail;
	@Column(name = "comment")
	private String comment;

	public WorksheetDetail getWorksheetDetail() {
		return worksheetDetail;
	}

	public void setWorksheetDetail(WorksheetDetail worksheetDetail) {
		this.worksheetDetail = worksheetDetail;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public WorksheetHistory(WorksheetDetail worksheetDetail, String comment) {
		super();
		this.worksheetDetail = worksheetDetail;
		this.comment = comment;
	}

	public WorksheetHistory() {

	}

	public WorksheetHistory(Integer id) {
		this.id = id;
	}

}

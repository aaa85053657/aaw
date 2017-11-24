package com.aaw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aaw.bean.base.BaseBean;

@Entity
@Table(name = "worksheet_detail_temp")
public class WorksheetDetailTemp extends BaseBean {
	@OneToOne
	@JoinColumn(name = "worksheet_detail_id")
	private WorksheetDetail worksheetDetail;
	@Column(name = "comments")
	private String comments;

	@Transient
	private int flag = 0;

	public WorksheetDetail getWorksheetDetail() {
		return worksheetDetail;
	}

	public void setWorksheetDetail(WorksheetDetail worksheetDetail) {
		this.worksheetDetail = worksheetDetail;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public WorksheetDetailTemp(WorksheetDetail worksheetDetail,
			String comments, int flag) {
		this.worksheetDetail = worksheetDetail;
		this.comments = comments;
		this.flag = flag;
	}

	public WorksheetDetailTemp() {
	}

	public WorksheetDetailTemp(Integer id) {
		this.id = id;
	}

}

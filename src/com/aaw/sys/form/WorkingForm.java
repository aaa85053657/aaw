package com.aaw.sys.form;

import java.util.List;

import com.aaw.bean.Profile;

/**
 * 保存任务处理的Form转换对象
 * 
 * @author molos
 *
 */
public class WorkingForm {

	private int WorksheetDetailId;
	private int operatorId;
	/**
	 * 状态,1刚创建即处理中 2处理成功 3处理异常（失败）
	 */
	private int status = 1;

	private List<Profile> proList;

	public int getWorksheetDetailId() {
		return WorksheetDetailId;
	}

	public void setWorksheetDetailId(int worksheetDetailId) {
		WorksheetDetailId = worksheetDetailId;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Profile> getProList() {
		return proList;
	}

	public void setProList(List<Profile> proList) {
		this.proList = proList;
	}

	public WorkingForm() {
	}
}

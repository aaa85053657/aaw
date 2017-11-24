package com.aaw.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aaw.bean.base.BaseBean;

@Entity
@Table(name = "worksheet_detail")
public class WorksheetDetail extends BaseBean {
	@OneToOne
	@JoinColumn(name = "worksheet_id")
	private Worksheet worksheet;
	@OneToOne
	@JoinColumn(name = "meta_procedure_id")
	private MetaProcedure metaProcedure;
	@OneToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;
	@Column(name = "priority")
	private int priority;
	@Column(name = "parent_id")
	private int parentId;
	/**
	 * 状态,1刚创建 2处理成功 3处理异常（失败） 4回滚 5终止 9操作中 10操作等待中
	 */
	@Column(name = "status")
	private int status = 1;
	@Column(name = "comments")
	private String comments;

	/**
	 * 结束加工时间
	 * 
	 */
	@Column(name = "end_time")
	private Date endTime;
	/**
	 * 操作员 选择，默认与登陆用户相同
	 */
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee operator;

	@Column(name = "is_read")
	private Integer isRead;

	@Column(name = "creation_time")
	private Date creationTime;

	@Column(name = "modification_time")
	private Date modificationTime;

	@Column(name = "unlock_time")
	private Date unlockTime;

	@Transient
	private int flag = 0;

	public Employee getOperator() {
		return operator;
	}

	public void setOperator(Employee operator) {
		this.operator = operator;
	}

	public Worksheet getWorksheet() {
		return worksheet;
	}

	public void setWorksheet(Worksheet worksheet) {
		this.worksheet = worksheet;
	}

	public MetaProcedure getMetaProcedure() {
		return metaProcedure;
	}

	public void setMetaProcedure(MetaProcedure metaProcedure) {
		this.metaProcedure = metaProcedure;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public WorksheetDetail() {
	}

	public WorksheetDetail(Worksheet worksheet, MetaProcedure metaProcedure,
			Profile profile, int priority, int parentId, int status,
			String comments) {
		super();
		this.worksheet = worksheet;
		this.metaProcedure = metaProcedure;
		this.profile = profile;
		this.priority = priority;
		this.parentId = parentId;
		this.status = status;
		this.comments = comments;
	}

	public WorksheetDetail(Worksheet worksheet, MetaProcedure metaProcedure,
			int priority, int parentId, int status, String comments) {
		super();
		this.worksheet = worksheet;
		this.metaProcedure = metaProcedure;
		this.priority = priority;
		this.parentId = parentId;
		this.status = status;
		this.comments = comments;
	}

	public WorksheetDetail(int worksheet, int metaProcedure, int profile,
			int priority, int parentId, int status, String comments) {
		this(new Worksheet(worksheet), new MetaProcedure(metaProcedure),
				new Profile(profile), priority, parentId, status, comments);
	}

	public WorksheetDetail(int worksheet, int metaProcedure, int priority,
			int parentId, int status, String comments) {
		this(new Worksheet(worksheet), new MetaProcedure(metaProcedure),
				priority, parentId, status, comments);
	}

	public WorksheetDetail(Integer id) {
		this.id = id;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	public Date getUnlockTime() {
		return unlockTime;
	}

	public void setUnlockTime(Date unlockTime) {
		this.unlockTime = unlockTime;
	}

}

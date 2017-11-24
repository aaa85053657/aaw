package com.aaw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aaw.bean.base.BaseBean;

/**
 * @author
 *
 */
@Entity
@Table(name = "commande_status_temp")
public class CommandeStatusTemp extends BaseBean {

	/**
	 * 状态名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 父状态ID，定义树状结构的标志
	 */
	@Column(name = "parent_id")
	private Integer parentId = 0;
	/**
	 * 是否发送邮件1 发送 0不发送
	 */
	@Column(name = "is_send_mail")
	private Integer isSendMail;
	/**
	 * 备注
	 */
	@Column(name = "comments")
	private String comments;

	/**
	 * 上一个环节的ID号，同级状态的开始状态
	 */
	@Column(name = "previous_id")
	private Integer previousId = 0;

	/**
	 * 下一个环节的ID号，同级状态的结束状态
	 */
	@Column(name = "next_id")
	private Integer nextId = 0;

	/**
	 * 默认为0，表示没有子节点
	 */
	@Column(name = "has_children")
	private Integer hasChildren = 0;

	@Column(name = "group_id")
	private Integer groupId;

	public Integer getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(Integer hasChildren) {
		this.hasChildren = hasChildren;
	}

	public String getName() {
		return name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public Integer getIsSendMail() {
		return isSendMail;
	}

	public String getComments() {
		return comments;
	}

	public Integer getPreviousId() {
		return previousId;
	}

	public Integer getNextId() {
		return nextId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public void setIsSendMail(Integer isSendMail) {
		this.isSendMail = isSendMail;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setPreviousId(Integer previousId) {
		this.previousId = previousId;
	}

	public void setNextId(Integer nextId) {
		this.nextId = nextId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public CommandeStatusTemp() {
	}
}
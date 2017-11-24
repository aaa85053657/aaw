package com.aaw.bean;

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
@Table(name = "commande_status")
public class CommandeStatus extends BaseBean {

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

	@OneToOne
	@JoinColumn(name = "main_commande_id")
	private MainCommande commande;

	/**
	 * 0、执行中 1、 成功、2、失败
	 */
	@Column(name = "status")
	private int status = 0;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public MainCommande getCommande() {
		return commande;
	}

	public void setCommande(MainCommande commande) {
		this.commande = commande;
	}

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

	public CommandeStatus() {
	}

	public CommandeStatus(String name, Integer parentId, Integer isSendMail,
			String comments, Integer previousId, Integer nextId,
			Integer hasChildren, MainCommande commande) {
		super();
		this.name = name;
		this.parentId = parentId;
		this.isSendMail = isSendMail;
		this.comments = comments;
		this.previousId = previousId;
		this.nextId = nextId;
		this.hasChildren = hasChildren;
		this.commande = commande;
	}

	public CommandeStatus(String name, Integer parentId, Integer isSendMail,
			String comments, Integer previousId, Integer nextId,
			Integer hasChildren, int commandeID) {
		super();
		this.name = name;
		this.parentId = parentId;
		this.isSendMail = isSendMail;
		this.comments = comments;
		this.previousId = previousId;
		this.nextId = nextId;
		this.hasChildren = hasChildren;
		this.commande = new MainCommande(commandeID);
	}

	public CommandeStatus(CommandeStatusTemp temp, int commandeID) {
		super();
		this.id = null;
		this.name = temp.getName();
		this.parentId = temp.getParentId();
		this.isSendMail = temp.getIsSendMail();
		this.comments = temp.getComments();
		this.previousId = temp.getPreviousId();
		this.nextId = temp.getNextId();
		this.hasChildren = temp.getHasChildren();
		this.commande = new MainCommande(commandeID);
	}
}
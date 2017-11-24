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
@Table(name = "commande_type")
public class CommandeType extends BaseBean {
	/**
 	 *
 	 */
	@Column(name = "name")
	private String name;
	/**
 	 *
 	 */
	@Column(name = "comments")
	private String comments;

	@OneToOne
	@JoinColumn(name = "group_id")
	private CommandeStatusGroup csg;

	public String getName() {
		return name;
	}

	public String getComments() {
		return comments;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public CommandeStatusGroup getCsg() {
		return csg;
	}

	public void setCsg(CommandeStatusGroup csg) {
		this.csg = csg;
	}

	public CommandeType() {
	}
}
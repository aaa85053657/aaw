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
@Table(name = "commande_flow")
public class CommandeFlow extends BaseBean {
	/**
 	 *
 	 */
	@Column(name = "main_commande_id")
	private Integer mainCommandeId;
	/**
 	 *
 	 */
	@Column(name = "commande_status_id")
	private Integer commandeStatusId;
	/**
 	 *
 	 */
	@Column(name = "status")
	private Integer status;

	public Integer getMainCommandeId() {
		return mainCommandeId;
	}

	public Integer getCommandeStatusId() {
		return commandeStatusId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setMainCommandeId(Integer mainCommandeId) {
		this.mainCommandeId = mainCommandeId;
	}

	public void setCommandeStatusId(Integer commandeStatusId) {
		this.commandeStatusId = commandeStatusId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public CommandeFlow() {
	}
}
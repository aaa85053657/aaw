package com.aaw.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.aaw.bean.base.BaseBean;

/**
 * @author 加盟商
 */
@Entity
@Table(name = "franchises_commande_relation")
public class FranchisesCommandeRelation extends BaseBean {

	/**
	 * 加盟商
	 */
	@OneToOne
	@JoinColumn(name = "franchises_id")
	private Franchises franchises;

	/**
	 * 订单
	 */
	@OneToOne
	@JoinColumn(name = "commande_id")
	private MainCommande commande;

	/**
	 * 创建时间
	 */
	@Column(name = "creation_time")
	private Date createTime;

	/**
	 * 修改时间
	 */
	@Column(name = "modification_time")
	private Date modificationTime;

	/**
	 * 删除时间
	 */
	@Column(name = "deletion_time")
	private Date deleteTime;

	public Franchises getFranchises() {
		return franchises;
	}

	public void setFranchises(Franchises franchises) {
		this.franchises = franchises;
	}

	public MainCommande getCommande() {
		return commande;
	}

	public void setCommande(MainCommande commande) {
		this.commande = commande;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public FranchisesCommandeRelation() {

	}

}

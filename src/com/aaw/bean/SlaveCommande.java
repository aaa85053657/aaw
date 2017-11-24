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
@Table(name = "slave_commande")
public class SlaveCommande extends BaseBean {
	/**
 	 *
 	 */
	@Column(name = "code_id")
	private String codeId;
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "main_commande_id")
	private MainCommande mainCommande;
	/**
 	 *
 	 */
	@Column(name = "comments")
	private String comments;
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "product_model_id")
	private ProductModel model;
	/**
 	 *
 	 */
	@Column(name = "is_start")
	private Integer isStart = 0;

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public MainCommande getMainCommande() {
		return mainCommande;
	}

	public void setMainCommande(MainCommande mainCommande) {
		this.mainCommande = mainCommande;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public ProductModel getModel() {
		return model;
	}

	public void setModel(ProductModel model) {
		this.model = model;
	}

	public Integer getIsStart() {
		return isStart;
	}

	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}

	public SlaveCommande() {
	}

	public SlaveCommande(int id) {
		super(id);
	}

}
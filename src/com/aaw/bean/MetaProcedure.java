package com.aaw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aaw.bean.base.BaseBean;

/**
 * @author
 *
 */
@Entity
@Table(name = "meta_procedure")
public class MetaProcedure extends BaseBean {
	/**
 	 *
 	 */
	@Column(name = "code_id")
	private String codeId;
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
	@Transient
	private int sequenceNum;

	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public String getCodeId() {
		return codeId;
	}

	public String getName() {
		return name;
	}

	public String getComments() {
		return comments;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public MetaProcedure() {
	}

	public MetaProcedure(int cfgID, String codeId, String name,
			String comments, int sequenceNum) {
		super(cfgID);
		this.codeId = codeId;
		this.name = name;
		this.comments = comments;
		this.sequenceNum = sequenceNum;
	}

	public MetaProcedure(Integer procedureID) {
		super(procedureID);
	}
}
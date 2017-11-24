package com.aaw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aaw.bean.base.BaseBean;

/**
 * @author
 *
 */
@Entity
@Table(name = "profile")
public class Profile extends BaseBean {
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
	/**
 	 *
 	 */
	@Column(name = "value_default")
	private Integer valueDefault;
	/**
 	 *
 	 */
	@Column(name = "value_min")
	private Integer valueMin;
	/**
 	 *
 	 */
	@Column(name = "value_max")
	private Integer valueMax;
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "profile_type_id")
	private ProfileType type;

	@Transient
	private int priority;

	public String getCodeId() {
		return codeId;
	}

	public String getName() {
		return name;
	}

	public String getComments() {
		return comments;
	}

	public Integer getValueMin() {
		return valueMin;
	}

	public Integer getValueMax() {
		return valueMax;
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

	public Integer getValueDefault() {
		return valueDefault;
	}

	public void setValueDefault(Integer valueDefault) {
		this.valueDefault = valueDefault;
	}

	public void setValueMin(Integer valueMin) {
		this.valueMin = valueMin;
	}

	public void setValueMax(Integer valueMax) {
		this.valueMax = valueMax;
	}

	public ProfileType getType() {
		return type;
	}

	public void setType(ProfileType type) {
		this.type = type;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Profile() {
	}

	public Profile(Integer id, String codeId, String name, String comments,
			Integer valueDefault, Integer valueMin, Integer valueMax,
			ProfileType type, int priority) {
		super(id);
		this.codeId = codeId;
		this.name = name;
		this.comments = comments;
		this.valueDefault = valueDefault;
		this.valueMin = valueMin;
		this.valueMax = valueMax;
		this.type = type;
		this.priority = priority;
	}

	public Profile(Integer profileID) {
		super(profileID);
	}

}
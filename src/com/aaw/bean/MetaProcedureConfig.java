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
@Table(name = "meta_procedure_config")
public class MetaProcedureConfig extends BaseBean {
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "meta_procedure_id")
	private MetaProcedure procedure;
	/**
 	 *
 	 */
	@Column(name = "priority")
	private Integer priority;

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public MetaProcedure getProcedure() {
		return procedure;
	}

	public void setProcedure(MetaProcedure procedure) {
		this.procedure = procedure;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public MetaProcedureConfig() {
	}

	public MetaProcedureConfig(Profile profile, MetaProcedure procedure,
			Integer priority) {
		super();
		this.profile = profile;
		this.procedure = procedure;
		this.priority = priority;
	}

	public MetaProcedureConfig(Integer profileID, Integer procedureID,
			Integer priority) {
		super();
		this.profile = new Profile(profileID);
		this.procedure = new MetaProcedure(procedureID);
		this.priority = priority;
	}

}
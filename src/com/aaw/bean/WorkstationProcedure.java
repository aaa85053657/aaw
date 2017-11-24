package com.aaw.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.molos.timer.impl.DateTimeSerl;

import com.aaw.bean.base.BaseBean;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "workstation_procedure")
public class WorkstationProcedure extends BaseBean {

	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "workstation_id")
	private Workstation workstation;
	/**
	 *
	 */
	@OneToOne
	@JoinColumn(name = "production_line_id")
	private ProductionLine line;
	/**
	 *
	 */
	@OneToOne
	@JoinColumn(name = "meta_procedure_id")
	private MetaProcedure procedure;

	/**
 	 *
 	 */
	@Column(name = "time_creation")
	private Date timeCreation = new Date();
	/**
 	 *
 	 */
	@Column(name = "time_modification")
	private Date timeModification;
	/**
 	 *
 	 */
	@Column(name = "time_delete")
	private Date timeDelete;

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeCreation() {
		return timeCreation;
	}

	public void setTimeCreation(Date timeCreation) {
		this.timeCreation = timeCreation;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeModification() {
		return timeModification;
	}

	public void setTimeModification(Date timeModification) {
		this.timeModification = timeModification;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getTimeDelete() {
		return timeDelete;
	}

	public void setTimeDelete(Date timeDelete) {
		this.timeDelete = timeDelete;
	}

	public Workstation getWorkstation() {
		return workstation;
	}

	public void setWorkstation(Workstation workstation) {
		this.workstation = workstation;
	}

	public ProductionLine getLine() {
		return line;
	}

	public void setLine(ProductionLine line) {
		this.line = line;
	}

	public MetaProcedure getProcedure() {
		return procedure;
	}

	public void setProcedure(MetaProcedure procedure) {
		this.procedure = procedure;
	}

	public WorkstationProcedure() {

	}

	public WorkstationProcedure(Workstation workstation, ProductionLine line,
			MetaProcedure procedure) {
		super();
		this.workstation = workstation;
		this.line = line;
		this.procedure = procedure;
	}

	public WorkstationProcedure(int workstationID, int lineID, int procedureID) {
		super();
		this.workstation = new Workstation(workstationID);
		this.line = new ProductionLine(lineID);
		this.procedure = new MetaProcedure(procedureID);
	}

}

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
@Table(name = "production_line_config")
public class ProductionLineConfig extends BaseBean {
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "meta_procedure_id")
	private MetaProcedure procedure;
	/**
 	 *
 	 */
	@OneToOne
	@JoinColumn(name = "production_line_id")
	private ProductionLine line;
	/**
 	 *
 	 */
	@Column(name = "sequence_num")
	private Integer sequenceNum;

	public MetaProcedure getProcedure() {
		return procedure;
	}

	public void setProcedure(MetaProcedure procedure) {
		this.procedure = procedure;
	}

	public ProductionLine getLine() {
		return line;
	}

	public void setLine(ProductionLine line) {
		this.line = line;
	}

	public Integer getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(Integer sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public ProductionLineConfig(MetaProcedure procedure, ProductionLine line,
			Integer sequenceNum) {
		this.procedure = procedure;
		this.line = line;
		this.sequenceNum = sequenceNum;
	}

	public ProductionLineConfig(int procedureID, int lineID, int sequenceNum) {
		this.procedure = new MetaProcedure(procedureID);
		this.line = new ProductionLine(lineID);
		this.sequenceNum = sequenceNum;
	}

	public ProductionLineConfig() {
	}
}
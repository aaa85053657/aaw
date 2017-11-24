package com.aaw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aaw.bean.base.BaseBean;

@Entity
@Table(name = "ref_data")
public class RefData extends BaseBean {
	@Column(name = "table_name")
	private int tableName;
	@Column(name = "prefix")
	private String prefix;
	@Column(name = "next_sequence")
	private int next;

	public int getTableName() {
		return tableName;
	}

	public void setTableName(int tableName) {
		this.tableName = tableName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

}

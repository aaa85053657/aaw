package com.aaw.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.molos.timer.impl.DateTimeSerl;

import com.aaw.bean.base.BaseBean;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author
 *
 */
@Entity
@Table(name = "dellivery_information")
public class DelliveryVer extends BaseBean {
	/**
 	 *
 	 */
	@Column(name = "current_ver")
	private String currentVer;
	/**
 	 *
 	 */
	@Column(name = "last_ver")
	private String lastVer;

	@Column(name = "creation_time")
	private Date creationTime;

	@Column(name = "file_delivery_info")
	private String fileInfo;

	public String getCurrentVer() {
		return currentVer;
	}

	public void setCurrentVer(String currentVer) {
		this.currentVer = currentVer;
	}

	public String getLastVer() {
		return lastVer;
	}

	public void setLastVer(String lastVer) {
		this.lastVer = lastVer;
	}

	@JsonSerialize(using = DateTimeSerl.class)
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
	}

	public DelliveryVer() {
	}
}
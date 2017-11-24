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
@Table(name = "log_exception")
public class LogException extends BaseBean {
	/**
 	 *
 	 */
	@Column(name = "level")
	private Integer level;
	/**
 	 *
 	 */
	@Column(name = "content")
	private String content;
	/**
 	 *
 	 */
	@Column(name = "request_addr")
	private String requestAddr;

	public Integer getLevel() {
		return level;
	}

	public String getContent() {
		return content;
	}

	public String getRequestAddr() {
		return requestAddr;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setRequestAddr(String requestAddr) {
		this.requestAddr = requestAddr;
	}

	public LogException() {
	}
}
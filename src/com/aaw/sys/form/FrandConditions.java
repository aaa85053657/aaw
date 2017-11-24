package com.aaw.sys.form;

public class FrandConditions {
	/**
	 * 生产线ID
	 */
	private Integer level;
	/**
	 * 第三方ID(模糊查询)
	 */
	private String fname;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public FrandConditions() {
	}
}

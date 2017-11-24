package com.aaw.sys.form;

public class MDConditions {
	// 按照生产线种类,按照第三方ID(模糊查询),按照生产进度(>,<,=),按照顾客姓名
	/**
	 * 生产线ID
	 */
	private Integer lineID;
	/**
	 * 第三方ID(模糊查询)
	 */
	private String third;
	/**
	 * 生产进度; 当前数量，不问负数则为前台传入
	 */
	private Integer st1 = -1;
	// /**
	// * 生产进度; 1/3
	// */
	// private String st2;
	/**
	 * (>,<,=)
	 */
	private String symbol;

	/**
	 * 顾客姓名
	 */
	private String name;

	public Integer getLineID() {
		return lineID;
	}

	public void setLineID(Integer lineID) {
		this.lineID = lineID;
	}

	public String getThird() {
		return third;
	}

	public void setThird(String third) {
		this.third = third;
	}

	public Integer getSt1() {
		return st1;
	}

	public void setSt1(Integer st1) {
		this.st1 = st1;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MDConditions() {
	}
}

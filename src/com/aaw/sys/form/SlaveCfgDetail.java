package com.aaw.sys.form;

public class SlaveCfgDetail {
	private String componentName;
	private int componentID;
	private String attrName;
	private String elementLeft;
	private String elementRight;
	private int eid;

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public int getComponentID() {
		return componentID;
	}

	public void setComponentID(int componentID) {
		this.componentID = componentID;
	}

	public String getElementLeft() {
		return elementLeft;
	}

	public void setElementLeft(String elementLeft) {
		this.elementLeft = elementLeft;
	}

	public String getElementRight() {
		return elementRight;
	}

	public void setElementRight(String elementRight) {
		this.elementRight = elementRight;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public SlaveCfgDetail() {
	}
}

package com.aaw.sys.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SlaveCfgChoice {
	private String componentName;
	private int componentID;
	private String attrName;
	private List<Map<String, Object>> elementLeft = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> elementRight = new ArrayList<Map<String, Object>>();
	private int type;
	private String elementRightMain;
	private String elementLeftMain;
	private Integer attrNameId;

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

	public List<Map<String, Object>> getElementLeft() {
		return elementLeft;
	}

	public void setElementLeft(List<Map<String, Object>> elementLeft) {
		this.elementLeft = elementLeft;
	}

	public List<Map<String, Object>> getElementRight() {
		return elementRight;
	}

	public void setElementRight(List<Map<String, Object>> elementRight) {
		this.elementRight = elementRight;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getElementRightMain() {
		return elementRightMain;
	}

	public void setElementRightMain(String elementRightMain) {
		this.elementRightMain = elementRightMain;
	}

	public String getElementLeftMain() {
		return elementLeftMain;
	}

	public void setElementLeftMain(String elementLeftMain) {
		this.elementLeftMain = elementLeftMain;
	}

	public Integer getAttrNameId() {
		return attrNameId;
	}

	public void setAttrNameId(Integer attrNameId) {
		this.attrNameId = attrNameId;
	}

	public SlaveCfgChoice() {
	}
}

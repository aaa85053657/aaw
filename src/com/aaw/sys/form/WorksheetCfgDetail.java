package com.aaw.sys.form;

public class WorksheetCfgDetail {
	private int componentID;
	private int elementID;
	private int attributeID;
	private int position;
	private int cfgID;
	private String componentName;
	private String elementName;
	private String attributeName;
	private int modelID;

	public int getModelID() {
		return modelID;
	}

	public void setModelID(int modelID) {
		this.modelID = modelID;
	}

	public int getComponentID() {
		return componentID;
	}

	public void setComponentID(int componentID) {
		this.componentID = componentID;
	}

	public int getElementID() {
		return elementID;
	}

	public void setElementID(int elementID) {
		this.elementID = elementID;
	}

	public int getAttributeID() {
		return attributeID;
	}

	public void setAttributeID(int attributeID) {
		this.attributeID = attributeID;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getCfgID() {
		return cfgID;
	}

	public void setCfgID(int cfgID) {
		this.cfgID = cfgID;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public WorksheetCfgDetail() {
	}

	public WorksheetCfgDetail(int componentID, int elementID, int attributeID,
			int position, int cfgID, String componentName, String elementName,
			String attributeName, int modelID) {
		super();
		this.componentID = componentID;
		this.elementID = elementID;
		this.attributeID = attributeID;
		this.position = position;
		this.cfgID = cfgID;
		this.componentName = componentName;
		this.elementName = elementName;
		this.attributeName = attributeName;
		this.modelID = modelID;
	}

}

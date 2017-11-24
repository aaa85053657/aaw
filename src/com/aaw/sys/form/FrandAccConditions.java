package com.aaw.sys.form;

import java.util.List;

public class FrandAccConditions {

	private String account;

	private String pazzword;

	private Integer status;

	private List<Integer> assets;

	private Integer id;

	private Integer fid;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPazzword() {
		return pazzword;
	}

	public void setPazzword(String pazzword) {
		this.pazzword = pazzword;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Integer> getAssets() {
		return assets;
	}

	public void setAssets(List<Integer> assets) {
		this.assets = assets;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public FrandAccConditions() {
	}
}

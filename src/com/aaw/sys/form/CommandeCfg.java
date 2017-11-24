package com.aaw.sys.form;

import java.util.List;

import com.aaw.bean.SlaveCommande;
import com.aaw.bean.SlaveCommandeConfig;
import com.aaw.bean.SlaveCommandeFreeConfig;

public class CommandeCfg {
	private SlaveCommande commande;
	private List<SlaveCommandeConfig> configs;
	private List<SlaveCommandeFreeConfig> configs2;

	public SlaveCommande getCommande() {
		return commande;
	}

	public void setCommande(SlaveCommande commande) {
		this.commande = commande;
	}

	public List<SlaveCommandeConfig> getConfigs() {
		return configs;
	}

	public void setConfigs(List<SlaveCommandeConfig> configs) {
		this.configs = configs;
	}

	public List<SlaveCommandeFreeConfig> getConfigs2() {
		return configs2;
	}

	public void setConfigs2(List<SlaveCommandeFreeConfig> configs2) {
		this.configs2 = configs2;
	}

}

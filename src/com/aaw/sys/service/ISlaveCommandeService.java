package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.SlaveCommande;

public interface ISlaveCommandeService extends IBaseService<SlaveCommande>{

	List<Map<String, Object>> combobox(int id);

	boolean delCheck(int id);

}
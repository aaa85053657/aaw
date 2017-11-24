package com.aaw.sys.service;

import java.util.List;
import java.util.Map;

import com.aaw.base.service.IBaseService;
import com.aaw.bean.SlaveCommandeConfig;
import com.aaw.sys.form.SlaveCfgDetail;

public interface ISlaveCommandeConfigService extends
		IBaseService<SlaveCommandeConfig> {

	Map<String, Object> slaveDetail(int id);

	Map<String, Object> modelElems(int id, int cid, int aid);

	List<SlaveCfgDetail> queryListByCommandeId(int id);

	Map<String, Object> slaveDetail2(int id);

	List<SlaveCommandeConfig> findBySC(int id);
}
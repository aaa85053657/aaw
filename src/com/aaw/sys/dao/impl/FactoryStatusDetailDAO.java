package com.aaw.sys.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.FactoryStatusDetail;
import com.aaw.sys.dao.IFactoryStatusDetailDAO;

@SuppressWarnings("unchecked")
@Repository
public class FactoryStatusDetailDAO extends BaseDAO<FactoryStatusDetail>
		implements IFactoryStatusDetailDAO {

	@Override
	public List<FactoryStatusDetail> findByFSID(int id) {
		Criteria c = currentSession().createCriteria(FactoryStatusDetail.class);
		c.add(Restrictions.eq("factoryStatus.id", id));
		return c.list();
	}

	@Override
	public FactoryStatusDetail findByWDID(int did) {
		Criteria c=currentSession().createCriteria(FactoryStatusDetail.class);
		c.add(Restrictions.eq("worksheetDetail.id", did)).add(Restrictions.eq("currentStatus", 2));
		List<FactoryStatusDetail> details=c.list();
		if(!npv.isNull(details)){
			return details.get(0);
		}
		return null;
	}

}

package com.aaw.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.WorksheetDetailTemp;
import com.aaw.sys.dao.IWorksheetDetailTempDAO;

@SuppressWarnings("unchecked")
@Repository
public class WorksheetDetailTempDAO extends BaseDAO<WorksheetDetailTemp>
		implements IWorksheetDetailTempDAO {

	@Override
	public WorksheetDetailTemp findByDetail(Integer id) {
		String hql = "from WorksheetDetailTemp where worksheetDetail.id=:wid";
		List<WorksheetDetailTemp> list = currentSession().createQuery(hql)
				.setParameter("wid", id).list();
		if (!npv.isNull(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteByWDID(Integer id) {
		String hql="delete from WorksheetDetailTemp where worksheetDetail.id=:wid";
		currentSession().createQuery(hql).setParameter("wid", id).executeUpdate();
	}

}

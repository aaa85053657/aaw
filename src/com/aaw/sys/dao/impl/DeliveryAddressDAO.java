package com.aaw.sys.dao.impl;

import java.util.List;

import com.aaw.base.dao.impl.BaseDAO;
import com.aaw.bean.DeliveryAddress;
import com.aaw.sys.dao.IDeliveryAddressDAO;

import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class DeliveryAddressDAO extends BaseDAO<DeliveryAddress> implements
		IDeliveryAddressDAO {

	@Override
	public List<DeliveryAddress> combobox(int id) {
		String hql = "from DeliveryAddress a where a.customer.id=:id";
		return currentSession().createQuery(hql).setParameter("id", id).list();
	}
}
package com.hallocasa.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOCurrency;
import com.hallocasa.entities.EntityCurrency;
import com.hallocasa.jpaservices.i.AppPersistenceServices;

@Stateless
public class DAOCurrency implements IDAOCurrency {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	@Override
	public List<EntityCurrency> find() {
		return appPersistenceServices.executeNamedQuery(EntityCurrency.QUERY_FIND_ALL, new Object[] {},
				EntityCurrency.class);
	}

}

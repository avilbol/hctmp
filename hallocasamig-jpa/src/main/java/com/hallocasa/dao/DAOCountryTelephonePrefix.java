package com.hallocasa.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOCountryTelephonePrefix;
import com.hallocasa.entities.EntityCountryTelephonePrefix;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.vo.Country;

/**
 * DAO for class {@link Country}
 */
@Stateless
public class DAOCountryTelephonePrefix implements IDAOCountryTelephonePrefix {

	@EJB
	private AppPersistenceServices appPersistenceServices;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityCountryTelephonePrefix> find() {
		return appPersistenceServices.executeNamedQuery(EntityCountryTelephonePrefix.QUERY_FIND_ALL, new Object[] {},
				EntityCountryTelephonePrefix.class);
	}

}

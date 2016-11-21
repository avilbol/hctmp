package com.hallocasa.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOCountry;
import com.hallocasa.entities.EntityCountry;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.vo.Country;

/**
 * DAO for class {@link Country}
 */
@Stateless
public class DAOCountry implements IDAOCountry {

	@EJB
	private AppPersistenceServices appPersistenceServices;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityCountry> find() {
		return appPersistenceServices.executeNamedQuery(EntityCountry.QUERY_FIND_ALL, new Object[] {},
				EntityCountry.class);
	}

}

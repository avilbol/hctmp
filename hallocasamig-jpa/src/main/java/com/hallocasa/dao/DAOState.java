package com.hallocasa.dao;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOState;
import com.hallocasa.entities.EntityState;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.vo.State;

/**
 * DAO for class {@link State}
 */
@Stateless
public class DAOState implements IDAOState {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityState> findByCountryId(Integer countryId) {
		String query = EntityState.QUERY_FIND_BY_COUNTRY_ID;
		List<Object> paramList = new LinkedList<Object>();
		paramList.add(countryId);
		return appPersistenceServices.executeNamedQuery(query, 
				paramList.toArray(), EntityState.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityState> findByCountriesId(List<Integer> countryIdList) {
		String query = EntityState.QUERY_FIND_BY_COUNTRIES_ID;
		List<Object> paramList = new LinkedList<Object>();
		paramList.add(countryIdList);
		return appPersistenceServices.executeNamedQuery(query, 
				paramList.toArray(), EntityState.class);
	}
}

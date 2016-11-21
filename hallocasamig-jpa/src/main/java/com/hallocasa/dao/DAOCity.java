package com.hallocasa.dao;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOCity;
import com.hallocasa.entities.EntityCity;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.vo.City;

/**
 * DAO for class {@link City}
 */
@Stateless
public class DAOCity implements IDAOCity {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityCity> findByStateId(Integer stateId) {
		String query = EntityCity.QUERY_FIND_BY_STATE_ID;
		List<Object> paramList = new LinkedList<Object>();
		paramList.add(stateId);
		return appPersistenceServices.executeNamedQuery(query, 
				paramList.toArray(), EntityCity.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityCity> findByStatesId(List<Integer> stateIdList) {
		String query = EntityCity.QUERY_FIND_BY_STATES_ID;
		List<Object> paramList = new LinkedList<Object>();
		paramList.add(stateIdList);
		return appPersistenceServices.executeNamedQuery(query, 
				paramList.toArray(), EntityCity.class);
	}
}

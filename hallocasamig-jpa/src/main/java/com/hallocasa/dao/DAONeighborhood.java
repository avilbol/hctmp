package com.hallocasa.dao;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAONeighborhood;
import com.hallocasa.entities.EntityNeighborhood;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.vo.Neighborhood;

/**
 * DAO for class {@link Neighborhood}
 */
@Stateless
public class DAONeighborhood implements IDAONeighborhood {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	@Override
	public List<EntityNeighborhood> findByCityId(Integer cityId) {
		String query = EntityNeighborhood.QUERY_FIND_BY_CITY_ID;
		List<Object> paramList = new LinkedList<Object>();
		paramList.add(cityId);
		return appPersistenceServices.executeNamedQuery(query, 
				paramList.toArray(), EntityNeighborhood.class);
	}
	
	@Override
	public List<EntityNeighborhood> findByCityIds(List<Integer> cityIds) {
		String query = EntityNeighborhood.QUERY_FIND_BY_CITY_IDS;
		List<Object> paramList = new LinkedList<Object>();
		paramList.add(cityIds);
		return appPersistenceServices.executeNamedQuery(query, 
				paramList.toArray(), EntityNeighborhood.class);
	}

	@Override
	public List<EntityNeighborhood> findGenericUse() {
		String query = EntityNeighborhood.QUERY_FIND_GENERIC_USE;
		return appPersistenceServices.executeNamedQuery(query, 
				new Object[] {}, EntityNeighborhood.class);
	}
}

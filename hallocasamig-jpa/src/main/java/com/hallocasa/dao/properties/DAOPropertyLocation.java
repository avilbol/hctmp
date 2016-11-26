package com.hallocasa.dao.properties;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.properties.IDAOPropertyLocation;
import com.hallocasa.entities.properties.EntityPropertyLocation;
import com.hallocasa.jpaservices.i.AppPersistenceServices;

/**
 * DAO for class {@link EntityPropertyLocation}
 */
@Stateless
public class DAOPropertyLocation implements IDAOPropertyLocation {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityPropertyLocation> find() {
		String query = EntityPropertyLocation.QUERY_FIND_ALL;
		return appPersistenceServices.executeNamedQuery(query, new Object[]{}, EntityPropertyLocation.class);
	}
}

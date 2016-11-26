package com.hallocasa.dao.properties;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.properties.IDAOPropertyType;
import com.hallocasa.entities.properties.EntityPropertyType;
import com.hallocasa.jpaservices.i.AppPersistenceServices;

/**
 * DAO for class {@link EntityPropertyType}
 */
@Stateless
public class DAOPropertyType implements IDAOPropertyType {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityPropertyType> find() {
		String query = EntityPropertyType.QUERY_FIND_ALL;
		return appPersistenceServices.executeNamedQuery(query, new Object[]{}, EntityPropertyType.class);
	}
}

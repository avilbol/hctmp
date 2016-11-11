package com.hallocasa.dao.properties;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.properties.IDAOPropertyField;
import com.hallocasa.entities.properties.EntityPropertyField;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.vo.properties.PropertyField;

/**
 * DAO for class {@link PropertyField}
 */
@Stateless
public class DAOPropertyField implements IDAOPropertyField {

	@EJB
	private AppPersistenceServices appPersistenceServices;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<EntityPropertyField> findByFilterId(Integer filterId) {
		String query = EntityPropertyField.QUERY_FIND_BY_FILTER;
		List<Object> paramList = new LinkedList<Object>();
		paramList.add(filterId);
		return appPersistenceServices.executeSingleNamedQuery(query,
				new Object[]{paramList}, EntityPropertyField.class);
	}

}

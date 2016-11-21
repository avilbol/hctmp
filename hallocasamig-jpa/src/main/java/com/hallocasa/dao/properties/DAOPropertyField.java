package com.hallocasa.dao.properties;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.properties.IDAOPropertyField;
import com.hallocasa.entities.EntityHcFilter;
import com.hallocasa.entities.properties.EntityPropertyField;
import com.hallocasa.jpaservices.i.AppPersistenceServices;
import com.hallocasa.vo.hcfilter.properties.PropertyKey;
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
		return appPersistenceServices.executeSingleNamedQuery(query, paramList.toArray(), EntityPropertyField.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityPropertyField> find() {
		String query = EntityPropertyField.QUERY_FIND_ALL;
		return appPersistenceServices.executeNamedQuery(query, new Object[] {}, EntityPropertyField.class);
	}

	@Override
	public List<EntityPropertyField> findByPropertyKeys(PropertyKey propertyKey) {
		String query = EntityPropertyField.QUERY_FIND_IN;
		List<Object> paramList = new LinkedList<Object>();
		paramList.add(propertyKey.getPropertyType().getId());
		paramList.add(propertyKey.getPropertyLocation().getId());
		paramList.add(propertyKey.getPropertyProposal().getId());
		paramList.add(propertyKey.getCountry().getId());
		List<Object> idList = appPersistenceServices.executeNativeQuery(EntityPropertyField.NATIVE_QUERY_FIND_BY_PK,
				paramList.toArray());
		if(idList.isEmpty()){
			return new LinkedList<EntityPropertyField>();
		}
		return appPersistenceServices.executeNamedQuery(query, new Object[]{idList}, 
				EntityPropertyField.class);
	}

}

package com.hallocasa.dao.i.properties;

import java.util.Optional;

import com.hallocasa.entities.properties.EntityPropertyField;
import com.hallocasa.vo.properties.PropertyField;

/**
 * Contract for DAO of class {@link PropertyField}
 */
public interface IDAOPropertyField {

	/**
	 * Search the property field related with id of specific filter
	 * @param filterId
	 * 		The filter id to match
	 * @return
	 * 		The property field matching the filter identifier, empty otherwise
	 */
	Optional<EntityPropertyField> findByFilterId(Integer filterId);
	
}

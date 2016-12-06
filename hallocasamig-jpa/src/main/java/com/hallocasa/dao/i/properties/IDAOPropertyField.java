package com.hallocasa.dao.i.properties;

import java.util.List;
import java.util.Optional;

import com.hallocasa.entities.properties.EntityPropertyField;
import com.hallocasa.vo.hcfilter.properties.PropertyKey;
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
	
	
	/**
	 * Search the property fields
	 * @param filterId
	 * 		The filter id to match
	 * @return
	 * 		The property fields
	 */
	List<EntityPropertyField> find();
	
	/**
	 * Search the property fields that allows to a property key set
	 * @param propertyKey
	 * 		The property key related
	 * @return
	 * 		The property fields that match the property key
	 */
	List<EntityPropertyField> findByPropertyKeys(PropertyKey propertyKey);

	/**
	 * Search a property field with specific id
	 * @param id
	 * 		the id of property field to search
	 * @return
	 * 		the property field with specified id, empty if not found
	 */
	Optional<EntityPropertyField> findById(Integer id);
}

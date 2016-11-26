package com.hallocasa.dao.i.properties;

import java.util.List;
import java.util.Optional;

import com.hallocasa.entities.EntityUser;
import com.hallocasa.entities.properties.EntityProperty;
import com.hallocasa.vo.hcfilter.PropertyFilterRequest;

public interface IDAOProperty {

	/**
	 * Update (or create) a property in the system
	 * @param property
	 * 		The property to update or create
	 */
	void save(EntityProperty property);
	
	/**
	 * Delete the property with the specified id
	 * @param propertyId
	 * 		Id of property to delete
	 */
	void delete(String propertyId);
	
	/**
	 * Find the basic properties
	 * @return
	 * 		The basic properties (?)
	 */
	List<EntityProperty> findBasic();
	
	/**
	 * Find the basic properties with the specified filters
	 * @param request
	 * 		The request with filters to apply
	 * @return
	 * 		The properties that match the filters
	 */
	List<EntityProperty> findBasic(PropertyFilterRequest request);
	
	/**
	 * Find the basic properties corresponding to specified user
	 * @param entityUser
	 * 		The user which the properties allow
	 * @return
	 * 		The properties of that user
	 */
	List<EntityProperty> findByUser(EntityUser entityUser);
	
	/**
	 * Find a detailed property by its id
	 * @param id
	 * @return
	 */
	Optional<EntityProperty> findById(String id);
}

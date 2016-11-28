package com.hallocasa.services.properties;

import java.util.List;
import java.util.Optional;

import com.hallocasa.vo.hcfilter.PropertyFilterRequest;
import com.hallocasa.vo.hcfilter.properties.Property;

/**
 * Contract for class {@link Property}
 * @author juan
 *
 */
public interface PropertyService {

	/**
	 * Update (or create) a property in the system
	 * @param property
	 * 		The property to update or create
	 */
	void save(Property property);
	
	/**
	 * Find the basic properties with specific user id
	 * @param userId
	 * 		The user id to use as filter
	 * @return
	 * 		The basic properties that belongs to specific user
	 */
	List<Property> findByUser(Integer userId);
	
	/**
	 * Find the basic properties
	 * @return
	 * 		The basic properties (?)
	 */
	List<Property> findBasic();
	
	/**
	 * Find the properties with the specified filters
	 * @param request
	 * 		The request with filters to apply
	 * @return
	 * 		The properties that match the filters
	 */
	List<Property> find(PropertyFilterRequest request);
	
	/**
	 * Find a detailed property by its id
	 * @param id
	 * @return
	 */
	Optional<Property> findById(String id);
	
	/**
	 * Delete the property with the specified id
	 * @param propertyId
	 * 		Id of property to delete
	 */
	void delete(String propertyId);
}

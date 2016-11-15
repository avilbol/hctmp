package com.hallocasa.services.properties;

import java.util.List;

import com.hallocasa.vo.hcfilter.HcRequest;
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
	 * Find the basic properties
	 * @return
	 * 		The basic properties (?)
	 */
	List<Property> findBasic();
	
	/**
	 * Find the basic properties with the specified filters
	 * @param request
	 * 		The request with filters to apply
	 * @return
	 * 		The properties that match the filters
	 */
	List<Property> findBasic(HcRequest request);
	
	/**
	 * Find a detailed property by its id
	 * @param id
	 * @return
	 */
	Property findById(String id);
}

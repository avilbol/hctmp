package com.hallocasa.services.properties;

import java.util.List;

import com.hallocasa.entities.properties.EntityProperty;
import com.hallocasa.vo.hcfilter.properties.Property;

public interface PropertyCommonsService {

	/**
	 * Transform a property entity list, into a property value object list
	 * @param entityList
	 * 		Entity list
	 * @return
	 * 		Value object list
	 */
	public List<Property> toValueObjectList(List<EntityProperty> entityList);
	
	/**
	 * Find the list of properties,searching by its ids
	 * @param propertyIdList
	 * 			Identifiers to search
	 * @param orderBy
	 * 			Specify attributes that apply to sort in query
	 * @param asc
	 * 			Specify if the sort is ascending (true), or descending (false)	
	 * @return
	 * 			The list of properties, filtered and sorted
	 */
	public List<EntityProperty> getPropertyListBy(List<String> propertyIdList, List<String> orderBy, boolean asc);
	
}

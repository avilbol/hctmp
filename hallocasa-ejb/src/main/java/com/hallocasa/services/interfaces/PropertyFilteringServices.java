package com.hallocasa.services.interfaces;

import java.util.List;

import com.hallocasa.commons.vo.properties.filters.PropertyFilter;
import com.hallocasa.dataentities.app.properties.PropertyFieldValue;

/**
 * Interface for the service related with filters of properties
 * @author Alexander Villamil
 */
public interface PropertyFilteringServices {

	/**
	 * Returns the ids of eligible properties for filter
	 * @param filter
	 * 		The filter to use
	 * @return
	 * 		The ids of properties that match with filter
	 */
	public List<String> loadIdsForFiltering(PropertyFilter filter);
	
	
	public List<PropertyFieldValue> loadPropertyFieldValues(List<Integer> propertyIdList);
	
}

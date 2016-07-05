package com.hallocasa.services.interfaces;

import java.util.List;

import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.commons.vo.properties.filters.PropertyFieldFilter;
import com.hallocasa.commons.vo.properties.filters.PropertyFilter;

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
	
	
	public List<Object[]> loadPropertyFieldEligible(List<String> propertyIdList
			, List<PropertyFieldFilter> pfFilter);
	
	/**
	 * Load the properties applying a filter before
	 * @param propertyFilter
	 * 		Filter that properties that match in order to be returned
	 * @return
	 * 		Property list that match the filter proposed
	 */
	public List<PropertyVO> loadProperties(PropertyFilter propertyFilter);
	
	/**
	 * Load Scheme for property filter
	 * @return
	 */
	public PropertyFilter getFilterScheme();
	
}

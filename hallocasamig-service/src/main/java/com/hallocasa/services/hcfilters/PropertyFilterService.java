package com.hallocasa.services.hcfilters;

import java.util.List;

import com.hallocasa.vo.hcfilter.properties.PropertyFilter;

/**
 * Service for provide system property filters
 */
public interface PropertyFilterService {

	/**
	 * Generates the filter list, filtering by a group of natures (if apply)
	 * @param filterByNature
	 * 		Specify if filtering by natures
	 * @param filterNatureIdList
	 * 		Filter nature list to specify as a filter
	 * @return	
	 * 		The filter list matching the natures specified
	 */
	List<PropertyFilter> loadPropertyFilterList(boolean filterByNature, List<Integer> filterNatureIdList);
	
}

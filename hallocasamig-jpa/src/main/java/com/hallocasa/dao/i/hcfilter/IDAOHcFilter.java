package com.hallocasa.dao.i.hcfilter;

import java.util.List;

import com.hallocasa.entities.EntityHcFilter;
import com.hallocasa.vo.hcfilter.HcFilter;
import com.hallocasa.vo.hcfilter.properties.PropertyDropdownFilterSubmission;

/**
 * Contract for DAO of class {@link HcFilter}
 */
public interface IDAOHcFilter {

	/**
	 * Retrieves the application filter list with the filterNatureList specified
	 * @param filterNatureIdList
	 * @return the application filter list
	 */
	List<EntityHcFilter> find(List<Integer> filterNatureIdList);
	
	/**
	 * Retrieves the application filter list
	 * @return the application filter list
	 */
	List<EntityHcFilter> find();

	/**
	 * Return the filter list that match with specified dropdown options
	 * @param filterList
	 * 		Specified dropdown options
	 * @return
	 * 		The filter list that match with dropdown options
	 */
	List<EntityHcFilter> findByPropertyKeys(List<PropertyDropdownFilterSubmission> filterList);

	
}

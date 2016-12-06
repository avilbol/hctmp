package com.hallocasa.services.properties;

import java.util.List;

import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.options.DropdownOption;
import com.hallocasa.vo.properties.PropertyField;

public interface PropertyListerService {

	/**
	 * Generates the list of options for a specific property field, given a parent property
	 * field series
	 * @param fieldId
	 * 		Property field
	 * @param propertyFieldList
	 * 		Parent property field series
	 * @return
	 * 		Options for property field
	 */
	List<DropdownOption> listFieldOptions(Integer fieldId, List<PropertyField> propertyFieldList);
	
	/**
	 * Generates the list of options for a specific property filter, given a parent property filter
	 * series
	 * @param filterId
	 * 		Property filter
	 * @param filterSubmissionList
	 * 		Parent property filter series
	 * @return
	 * 		Options for property filter
	 */
	List<DropdownOption> listFilterOptions(Integer filterId, 
			List<PropertyFilterSubmission> filterSubmissionList);
}

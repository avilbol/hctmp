package com.hallocasa.services.hcfilters.listers;

import java.util.List;

import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.hcfilter.properties.PropertyKey;
import com.hallocasa.vo.options.DropdownOption;
import com.hallocasa.vo.properties.PropertyField;

public interface HcLister {

	/**
	 * List the options that correspond to filter parent list and options specified
	 * @param filterList
	 * 			The parent filter list specified
	 * @return
	 * 			The options that apply
	 */
	List<DropdownOption> loadFilterOptions(List<PropertyFilterSubmission> filterList);
	
	/**
	 * List the options that correspond to property fields and values specified
	 * @param fieldList
	 * 			The property field list with specified selected values
	 * @return
	 * 			The options that apply
	 */
	List<DropdownOption> loadFieldOptions(PropertyKey propertyKey, List<PropertyField> fieldList);
}

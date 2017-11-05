package com.hallocasa.services.hcfilters.filterworkers;

import java.util.List;
import java.util.Map;

import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.options.DropdownOption;

public class PropertyTypeGroupWorker implements FilterWorker {

	@Override
	public Integer addParams(PropertyFilterSubmission filterSubmission, Map<String, Object> params,
			Integer attrNumber) {
		Integer counter = attrNumber;
		for(DropdownOption option : filterSubmission.getSelectedFilterOptions()){
			params.put(String.valueOf(counter++), option.getOptionId());
		}
		return counter;
	}

	@Override
	public String loadParametersQuery(PropertyFilterSubmission filterSubmission, 
			Integer attrNumber) {
		return "ptype_group_exists ";
	}

	@Override
	public String loadJoinQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		String joinQuery = " left join  (" + 
				" select id as ptype_id, group_id as ptype_group_id from property_type" + 
				") ptypejoin" + 
				" on ptypejoin.property_type_id = property_type_id" + 
				" left join  (" + 
				" select 1 as ptype_group_exists, id as ptype_group_id from property_type_group "
				+ "WHERE id IN %1$s" + 
				") ptypegroupjoin" + 
				" on ptypejoin.property_type_id = ptypegroupjoin.id ";
		List<DropdownOption> dropdownOptionList = filterSubmission.getSelectedFilterOptions();
		String resultParamSchema = WorkerUtils.loadCondition(dropdownOptionList, attrNumber);
		return String.format(joinQuery, resultParamSchema);
	}
	
	@Override
	public void validate(PropertyFilterSubmission filterSubmission) {
		// Nothing to do
	}

	@Override
	public String loadWhereQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		String lwStr =  "ptype_group_exists = 1 ";
		return String.format(lwStr, attrNumber);
	}

}

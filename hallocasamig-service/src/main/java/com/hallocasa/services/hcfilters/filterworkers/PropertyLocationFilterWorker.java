package com.hallocasa.services.hcfilters.filterworkers;

import java.util.List;
import java.util.Map;

import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.options.DropdownOption;

public class PropertyLocationFilterWorker implements FilterWorker {

	@Override
	public String loadParametersQuery(PropertyFilterSubmission filterSubmission, 
			Integer attrNumber) {
		return " property_location_id";
	}

	@Override
	public String loadJoinQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		return "";
	}

	@Override
	public String loadWhereQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		String ljStr = " property_location_id IN %1$s";
		List<DropdownOption> dropdownOptionList = filterSubmission.getSelectedFilterOptions();
		String resultParamSchema = WorkerUtils.loadCondition(dropdownOptionList, attrNumber);
		return String.format(ljStr, resultParamSchema);
	}

	@Override
	public Integer addParams(PropertyFilterSubmission filterSubmission, Map<String, Object> params, Integer attrNumber) {
		Integer counter = attrNumber;
		for(DropdownOption option : filterSubmission.getSelectedFilterOptions()){
			params.put(String.valueOf(counter++), option.getOptionId());
		}
		return counter;
	}
	
	@Override
	public void validate(PropertyFilterSubmission filterSubmission) {
	}
}

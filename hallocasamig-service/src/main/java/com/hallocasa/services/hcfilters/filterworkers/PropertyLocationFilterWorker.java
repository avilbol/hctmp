package com.hallocasa.services.hcfilters.filterworkers;

import java.util.List;

import com.hallocasa.vo.hcfilter.properties.PropertyDropdownFilterSubmission;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.options.DropdownOption;

public class PropertyLocationFilterWorker implements FilterWorker {

	@Override
	public String loadParametersQuery(PropertyFilterSubmission filterSubmission) {
		return " property_location_id,";
	}

	@Override
	public String loadJoinQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		return "";
	}

	@Override
	public String loadWhereQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		String ljStr = " property_location_id IN %1$s";
		PropertyDropdownFilterSubmission submission = (PropertyDropdownFilterSubmission) filterSubmission;
		List<DropdownOption> dropdownOptionList = submission.getSelectedFilterOptions();
		String resultParamSchema = WorkerUtils.loadCondition(dropdownOptionList, attrNumber);
		return String.format(ljStr, resultParamSchema);
	}

}

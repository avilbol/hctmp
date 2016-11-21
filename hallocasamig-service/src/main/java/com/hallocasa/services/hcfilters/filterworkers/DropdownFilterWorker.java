package com.hallocasa.services.hcfilters.filterworkers;

import java.util.List;

import com.hallocasa.vo.hcfilter.properties.PropertyDropdownFilterSubmission;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.options.DropdownOption;

public class DropdownFilterWorker implements FilterWorker {

	@Override
	public String loadParametersQuery(PropertyFilterSubmission filterSubmission) {
		String lpStr = " case pf%1$dexist when 1 then 1 else 0 end as pf%1$d," + 
				" case pf%1$dmatch when 1 then 1 else 0 end as pf%1$dmatch, ";
		return String.format(lpStr, filterSubmission.getFilter()
				.getPropertyField().getId());
	}

	@Override
	public String loadJoinQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		PropertyDropdownFilterSubmission submission = (PropertyDropdownFilterSubmission) filterSubmission;
		String ljStr = " left join "
		  + "(select true as p%1$dexists, property_id from property_field_value "
		  + " where property_field_id=%1$d) pf%1$d"
		  + " on pf%1$d.property_id = p0.property_id"
		  +" left join"
		  +" (select true as pf%1$dmatch, property_id from property_field_value "
		  +"	where property_field_id=%1$d and identifier IN %2$s GROUP BY property_id) "
		  +"	pf%1$dfilter"
		  +" on pf%1$dfilter.property_id = p0.property_id";
		List<DropdownOption> dropdownOptionList = submission.getSelectedFilterOptions();
		String resultParamSchema = WorkerUtils.loadCondition(dropdownOptionList, attrNumber);
		return String.format(ljStr, filterSubmission.getFilter()
				.getPropertyField().getId(), resultParamSchema);
	}

	@Override
	public String loadWhereQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		String lwStr =  " (pf%1$d = 0 or pf%1$dmatch = 1)";
		return String.format(lwStr, filterSubmission.getFilter()
				.getPropertyField().getId());
	}
}

package com.hallocasa.services.hcfilters.filterworkers;

import java.util.Map;

import com.hallocasa.utils.constants.exceptions.BadRequestException;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;

public class BooleanFilterWorker implements FilterWorker {

	@Override
	public String loadParametersQuery(PropertyFilterSubmission filterSubmission, 
			Integer attrNumber) {
		String lpStr = " case pf%1$dexists when 1 then 1 else 0 end as pf%1$d," + 
				" case pf%1$dmatch when 1 then 1 else 0 end as pf%1$dmatch ";
		return String.format(lpStr, filterSubmission.getPropertyFilter()
				.getPropertyField().getId());
	}

	@Override
	public String loadJoinQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		String ljStr = " left join "
		  + "(select true as pf%1$dexists, property_id from property_field_value "
		  + " where property_field_id=%1$d GROUP BY property_id) pf%1$d"
		  + " on pf%1$d.property_id = p0.property_id"
		  +" left join"
		  +" (select true as pf%1$dmatch, property_id from property_field_value "
		  +"	where property_field_id=%1$d and text = %2$s GROUP BY property_id) pf%1$dfilter"
		  +" on pf%1$dfilter.property_id = p0.property_id";
		String checkStr = "?" + (attrNumber);
		return String.format(ljStr, filterSubmission.getPropertyFilter()
				.getPropertyField().getId(), checkStr);
	}

	@Override
	public String loadWhereQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		String lwStr =  " (pf%1$d = 1 and pf%1$dmatch = 1)";
		return String.format(lwStr, filterSubmission.getPropertyFilter()
				.getPropertyField().getId());
	}

	@Override
	public Integer addParams(PropertyFilterSubmission filterSubmission, Map<String, Object> params, Integer attrNumber) {
		Integer counter = attrNumber;
		params.put(String.valueOf(counter++), filterSubmission.isApply() ? "1" : "0");
		return counter;
	}
	
	@Override
	public void validate(PropertyFilterSubmission filterSubmission) {
	}
}

package com.hallocasa.services.hcfilters.filterworkers;

import com.hallocasa.vo.hcfilter.RangeFieldPresentation;
import com.hallocasa.vo.hcfilter.RangeFilterType;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.hcfilter.properties.PropertyRangeFilterSubmission;

public class RangeFilterWorker implements FilterWorker {

	@Override
	public String loadParametersQuery(PropertyFilterSubmission filterSubmission) {
		String lpStr = " case pf%1$dexist when 1 then 1 else 0 end as pf%1$d," + 
				" case pf%1$dmatch when 1 then 1 else 0 end as pf%1$dmatch, ";
		return String.format(lpStr, filterSubmission.getPropertyFilter()
				.getPropertyField().getId());
	}

	@Override
	public String loadJoinQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		PropertyRangeFilterSubmission submission = (PropertyRangeFilterSubmission) filterSubmission;
		String ljStr = " left join "
		  + " (select true as p%1$dexists, property_id from property_field_value "
		  + " where property_field_id=%1$d) pf%1$d"
		  + " on pf%1$d.property_id = p0.property_id"
		  +" left join"
		  +" (select true as pf%1$dmatch, property_id from property_field_value "
		  +"	where property_field_id=%1$d and %2$s GROUP BY property_id) pf%1$dfilter"
		  +" on pf%1$dfilter.property_id = p0.property_id";
		RangeFilterType filterType = (RangeFilterType) submission.getPropertyFilter().getFilter().getFilterType();
		RangeFieldPresentation presentation = filterType.getRangeFieldPresentation();
		String sqldataType = WorkerUtils.loadSqlDataType(presentation);
		StringBuilder condition = new StringBuilder("");
		if(submission.getMinValue() != null){
			condition.append(String.format("CAST(text AS %1$s) >= %2$d",
					sqldataType,attrNumber++));
		}
		boolean addAnd = !condition.toString().equals("");
		if(submission.getMaxValue() != null){
			condition.append(String.format("%1$s CAST(text AS %2$s) >= %3$d",
					addAnd ? " AND " : "", sqldataType, attrNumber++));
		}
		return String.format(ljStr, filterSubmission.getPropertyFilter()
				.getPropertyField().getId(), condition.toString());
	}

	@Override
	public String loadWhereQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		String lwStr =  " (pf%1$d = 0 or pf%1$dmatch = 1)";
		return String.format(lwStr, filterSubmission.getPropertyFilter()
				.getPropertyField().getId());
	}

}

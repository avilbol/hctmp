package com.hallocasa.services.hcfilters.filterworkers;

import java.util.Map;

import com.hallocasa.utils.constants.exceptions.BadRequestException;
import com.hallocasa.vo.hcfilter.HcFilterTypeEntry;
import com.hallocasa.vo.hcfilter.RangeFieldPresentation;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;

public class RangeFilterWorker implements FilterWorker {

	@Override
	public String loadParametersQuery(PropertyFilterSubmission filterSubmission, 
			Integer attrNumber) {
		String lpStr = " case pf%1$dexists when 1 then 1 else 0 end as pf%1$d," + 
				" case pf%1$dmatch when 1 then 1 else 0 end as pf%1$dmatch";
		return String.format(lpStr, filterSubmission.getPropertyFilter()
				.getPropertyField().getId());
	}

	@Override
	public String loadJoinQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		String ljStr = " left join "
		  + " (select true as pf%1$dexists, property_id from property_field_value "
		  + " where property_field_id=%1$d GROUP BY property_id) pf%1$d"
		  + " on pf%1$d.property_id = p0.property_id"
		  +" left join"
		  +" (select true as pf%1$dmatch, property_id from property_field_value "
		  +"	where property_field_id=%1$d and %2$s GROUP BY property_id) pf%1$dfilter"
		  +" on pf%1$dfilter.property_id = p0.property_id";
		HcFilterTypeEntry filterType = filterSubmission.getPropertyFilter().getFilter().getFilterType();
		RangeFieldPresentation presentation = filterType.getRangeFieldPresentation();
		String sqldataType = WorkerUtils.loadSqlDataType(presentation);
		StringBuilder condition = new StringBuilder("");
		if(filterSubmission.getMinValue() != null || filterSubmission.getMinDateValue() != null){
			condition.append(String.format("CAST(text AS %1$s) >= ?%2$d",
					sqldataType,attrNumber++));
		}
		boolean addAnd = !condition.toString().equals("");
		if(filterSubmission.getMaxValue() != null || filterSubmission.getMaxDateValue() != null){
			condition.append(String.format("%1$s CAST(text AS %2$s) <= ?%3$d",
					addAnd ? " AND " : "", sqldataType, attrNumber++));
		}
		return String.format(ljStr, filterSubmission.getPropertyFilter()
				.getPropertyField().getId(), condition.toString());
	}

	@Override
	public String loadWhereQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		String lwStr =  " (pf%1$d = 1 and pf%1$dmatch = 1)";
		return String.format(lwStr, filterSubmission.getPropertyFilter()
				.getPropertyField().getId());
	}

	@Override
	public Integer addParams(PropertyFilterSubmission filterSubmission, Map<String, Object> params, Integer attrNumber) {
		HcFilterTypeEntry filterType = filterSubmission.getPropertyFilter().getFilter().getFilterType();
		RangeFieldPresentation presentation = filterType.getRangeFieldPresentation();
		Integer counter = attrNumber;
		if(filterSubmission.getMinValue() != null || filterSubmission.getMinDateValue() != null){
			params.put(String.valueOf(counter++), WorkerUtils.getMinCastedValue(filterSubmission, presentation));
		}
		if(filterSubmission.getMaxValue() != null || filterSubmission.getMaxDateValue() != null){
			params.put(String.valueOf(counter++), WorkerUtils.getMaxCastedValue(filterSubmission, presentation));
		}
		return counter;
	}
	
	@Override
	public void validate(PropertyFilterSubmission filterSubmission) {
		RangeFieldPresentation presentation = filterSubmission.getPropertyFilter()
				.getFilter().getFilterType().getRangeFieldPresentation();
		boolean invalidDatePresentation = presentation.equals(RangeFieldPresentation.DATE)
				&& (filterSubmission.getMinDateValue() == null && filterSubmission.getMaxDateValue() == null);
		boolean invalidCrcyPresentation = presentation.equals(RangeFieldPresentation.CURRENCY)
				&& (filterSubmission.getMinCrcyValue() == null && filterSubmission.getMaxCrcyValue() == null);
		boolean invalidRangePresentation = (presentation.equals(RangeFieldPresentation.DOUBLE) 
				|| presentation.equals(RangeFieldPresentation.INTEGER))
				&& filterSubmission.getMinValue() == null && filterSubmission.getMaxValue() == null;
		String msg = "";
		if(invalidDatePresentation){
			msg = "If you want to use date range filters, you must send 'minDateValue' or "
					+ "maxDateValue attributes";
		}
		if(invalidCrcyPresentation){
			msg = "If you want to use currency range filters, you must send 'minCrcyValue' or "
					+ "maxCrcyValue attributes";
		}
		if(invalidRangePresentation){
			msg = "If you want to use basic range filters, you must send 'minValue' or "
					+ "maxValue attributes";
		}
		if(invalidDatePresentation || invalidCrcyPresentation || invalidRangePresentation){
			throw new BadRequestException(msg);
		}
	}
}

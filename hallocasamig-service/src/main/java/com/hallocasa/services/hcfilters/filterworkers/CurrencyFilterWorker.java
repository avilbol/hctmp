package com.hallocasa.services.hcfilters.filterworkers;

import java.util.Map;

import com.hallocasa.utils.constants.exceptions.BadRequestException;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;

public class CurrencyFilterWorker implements FilterWorker {

	@Override
	public Integer addParams(PropertyFilterSubmission filterSubmission, Map<String, Object> params,
			Integer attrNumber) {
		Integer counter = attrNumber;
		params.put(String.valueOf(counter++), loadCrcyId(filterSubmission));
		if(filterSubmission.getMinCrcyValue() != null){
			params.put(String.valueOf(counter++), filterSubmission.getMinCrcyValue().getAmmount());
		}
		if(filterSubmission.getMaxCrcyValue() != null){
			params.put(String.valueOf(counter++), filterSubmission.getMaxCrcyValue().getAmmount());
		}
		return counter;
	}

	@Override
	public String loadParametersQuery(PropertyFilterSubmission filterSubmission) {
		String lpStr = "case pf%1$dexists when 1 then 1 else 0 end as pf%1$d,"
				+" pf%1$dcrcy.currency_value * rate_exchange as currency_value";
		return String.format(lpStr, filterSubmission.getPropertyFilter()
					.getPropertyField().getId());
	}

	@Override
	public String loadJoinQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		String ljStr = " left join  ("
				+" select true as pf%1$dexists, property_id from property_field_value"  
				+" where property_field_id=%1$d GROUP BY property_id) pf%1$d"
				+" on pf%1$d.property_id = p0.property_id"
				+" left join ("
				+" select property_id, data1 as currency_id, data2 as currency_value from property_field_value"
				+" where property_field_id=%1$d GROUP BY property_id ) pf%1$dcrcy"	
				+" on pf%1$dcrcy.property_id = p0.property_id"
				+" left join"
				+" currency_exchange_data cedata"
				+" on currency_to_id = ?%2$d and currency_from_id=pf%1$dcrcy.currency_id";
		return String.format(ljStr, filterSubmission.getPropertyFilter()
				.getPropertyField().getId(), attrNumber);
		
	}

	@Override
	public String loadWhereQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		attrNumber++;
		String lwStr =  "pf%1$d = 1 AND ";
		String lwStrFormatted = String.format(lwStr, filterSubmission.getPropertyFilter()
				.getPropertyField().getId());
		StringBuilder condition = new StringBuilder(lwStrFormatted);
		if(filterSubmission.getMinCrcyValue() != null){
			condition.append(String.format("currency_value >= ?%1$d", attrNumber++));
		}
		boolean addAnd = !condition.toString().equals("");
		if(filterSubmission.getMaxCrcyValue() != null){
			condition.append(String.format(" %1$s currency_value <= ?%2$d", 
					addAnd ? "AND" : "", attrNumber++));
		}
		return condition.toString();
	}

	
	private Integer loadCrcyId(PropertyFilterSubmission filterSubmission){
		Integer minCrcyId = filterSubmission.getMinCrcyValue().getCurrency().getId();
		Integer maxCrcyId = filterSubmission.getMaxCrcyValue().getCurrency().getId();
		if(minCrcyId != null && maxCrcyId != null && !minCrcyId.equals(maxCrcyId)){
			throw new BadRequestException("You cannot send a crcy filter range with different currency values!");
		}
		if(minCrcyId == null && maxCrcyId == null){
			throw new BadRequestException("In this filter you are forced to send at least one range (min or max)");
		}
		return minCrcyId == null ? maxCrcyId : minCrcyId;
	}
}

package com.hallocasa.services.hcfilters.filterworkers;

import java.util.Map;

import com.hallocasa.utils.constants.exceptions.BadRequestException;
import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.options.DropdownOption;

public class RoiFilterWorker implements FilterWorker {

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
		String formula = "(monthly_rent * ((100 - maflandlord - admflandlord) / 100) * 12) "
				+ "* 100 / market_price";
		String lpStr = "case maflandlord_exists when 1 then 1 else 0 end as maflandlord_exists," 
				+ " case admflandlord_exists when 1 then 1 else 0 end as admflandlord_exists," 
				+ " case market_price_exists when 1 then 1 else 0 end as market_price_exists," 
				+ " case monthly_rent_exists when 1 then 1 else 0 end as monthly_rent_exists," 
				+ " %1$s as aroi," 
				+ " cast(((%1$s) - 1) / 5 as unsigned integer) + 1 as interv," 
				+ " case when((%1$s) %%5 = 0 ) then 1 else 0 end as exact,"
				+ " case when(cast(((%1$s) - 1) / 5 as unsigned integer) + 1) = ?%2$d "
				+ " then 1 else 0 end as intervmatch,"
				+ " case when(cast(((%1$s) - 1) / 5 as unsigned integer) ) = ?%2$d "
				+ " then 1 else 0 end as intervdownmatch ";
		return String.format(lpStr, formula, attrNumber);
	}

	@Override
	public String loadJoinQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		return " left join  (" + 
				" select true as maflandlord_exists, property_id from property_field_value  " + 
				" where property_field_id=57 GROUP BY property_id) maflandlordexist" + 
				" on maflandlordexist.property_id = p0.property_id" + 
				" left join (" + 
				" select cast(text as decimal(15,2)) as maflandlord, property_id from property_field_value  " + 
				" where property_field_id=57 GROUP BY property_id) maflandlord_data" + 
				" on maflandlord_data.property_id = p0.property_id" + 
				" left join  (" + 
				" select true as admflandlord_exists, property_id from property_field_value  " + 
				" where property_field_id=58 GROUP BY property_id) admflandlordexist" + 
				" on admflandlordexist.property_id = p0.property_id" + 
				" left join (" + 
				" select cast(text as decimal(15,2)) as admflandlord, property_id from property_field_value  " + 
				" where property_field_id=58 GROUP BY property_id) admflandlord_data" + 
				" on admflandlord_data.property_id = p0.property_id" + 
				" left join  (" + 
				" select true as market_price_exists, property_id from property_field_value  " + 
				" where property_field_id=5 GROUP BY property_id) marketpriceexist" + 
				" on marketpriceexist.property_id = p0.property_id" + 
				" left join (" + 
				" select cast(data2 as decimal(15,2)) as market_price, property_id from property_field_value  " + 
				" where property_field_id=5 GROUP BY property_id) marketprice_data" + 
				" on marketprice_data.property_id = p0.property_id" + 
				" left join  (" + 
				" select true as monthly_rent_exists, property_id from property_field_value  " + 
				" where property_field_id=60 GROUP BY property_id) monthlyrentexist" + 
				" on monthlyrentexist.property_id = p0.property_id" + 
				" left join (" + 
				" select cast(data2 as decimal(15,2)) as monthly_rent, property_id from property_field_value  " + 
				" where property_field_id=60 GROUP BY property_id) monthly_rent_data" + 
				" on monthly_rent_data.property_id = p0.property_id";	
	}
	
	@Override
	public void validate(PropertyFilterSubmission filterSubmission) {
		if(filterSubmission.getSelectedFilterOptions() == null || 
				filterSubmission.getSelectedFilterOptions().isEmpty()){
			throw new BadRequestException("If you want to use roi filter, "
					+ "you must send non-empty 'selectedFilterOptions' attribute");
		}
	}

	@Override
	public String loadWhereQuery(PropertyFilterSubmission filterSubmission, Integer attrNumber) {
		String lwStr =  "maflandlord_exists and admflandlord_exists " 
				+ " and market_price_exists and monthly_rent_exists and" 
				+ " ((exact= 1 and (intervmatch = 1 or intervdownmatch = 1))" 
				+ " or (exact = 0 and intervmatch = 1)) ";
		return String.format(lwStr, attrNumber);
	}
}

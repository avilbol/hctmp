package com.hallocasa.services.hcfilters.filterworkers;

import java.util.List;

import com.hallocasa.vo.hcfilter.RangeFieldPresentation;
import com.hallocasa.vo.options.DropdownOption;

public class WorkerUtils {

	public static String loadSqlDataType(RangeFieldPresentation rfp) {
		switch (rfp) {
			case CURRENCY:
				return "DECIMAL(10,2)";
			case DATE:
				return "DATE";
			case DOUBLE:
				return "DECIMAL(10,2)";
			case INTEGER:
				return "UNSIGNED INTEGER";
			default:
				return null;
		}
	}
	
	public static String loadCondition(List<DropdownOption> options, Integer attrNumber){
		StringBuilder listParamSchema = new StringBuilder("(");
		Integer counter = 0;
		while(counter < options.size()){
			listParamSchema.append("?" + (attrNumber++) + ",");
			counter++;
		}
		return listParamSchema.append(")").toString()
				.replaceAll(",\\)", "\\)");
	}
}

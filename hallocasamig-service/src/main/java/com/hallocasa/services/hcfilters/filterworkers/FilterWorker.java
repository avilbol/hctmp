package com.hallocasa.services.hcfilters.filterworkers;

import java.util.Map;

import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;

/**
 * Contract that indicate how to build filter queries
 */
public interface FilterWorker {
	
	public Integer addParams(PropertyFilterSubmission filterSubmission, Map<String, Object> params, 
			Integer attrNumber);

	public String loadParametersQuery(PropertyFilterSubmission filterSubmission);
	
	public String loadJoinQuery(PropertyFilterSubmission filterSubmission, 
			Integer attrNumber);
	
	public String loadWhereQuery(PropertyFilterSubmission filterSubmission, 
			Integer attrNumber);
	
}

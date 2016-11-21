package com.hallocasa.services.hcfilters.filterworkers;

import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;

/**
 * Contract that indicate how to build filter queries
 */
public interface FilterWorker {

	public String loadParametersQuery(PropertyFilterSubmission filterSubmission);
	
	public String loadJoinQuery(PropertyFilterSubmission filterSubmission, 
			Integer attrNumber);
	
	public String loadWhereQuery(PropertyFilterSubmission filterSubmission, 
			Integer attrNumber);
	
}

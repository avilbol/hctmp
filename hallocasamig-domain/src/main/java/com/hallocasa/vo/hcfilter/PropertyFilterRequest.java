package com.hallocasa.vo.hcfilter;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.hcfilter.properties.PropertyFilterSubmission;
import com.hallocasa.vo.resultrequest.ResultRequest;

/**
 * Hallocasa request, value object to obtain data
 * @author Alexander Villamil 
 * *
 */
public class PropertyFilterRequest implements Serializable {

	private static final long serialVersionUID = -6665149200769287476L;

	private List<PropertyFilterSubmission> filterList;
	
	private ResultRequest resultRequest;

	public List<PropertyFilterSubmission> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<PropertyFilterSubmission> filterList) {
		this.filterList = filterList;
	}

	public ResultRequest getResultRequest() {
		if(resultRequest == null){
			resultRequest = new ResultRequest();
		}
		return resultRequest;
	}

	public void setResultRequest(ResultRequest resultRequest) {
		this.resultRequest = resultRequest;
	}
}

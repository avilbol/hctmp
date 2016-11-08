package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class DropdownFilter extends HcFilter implements ValueObject, Serializable{

	private static final long serialVersionUID = -888610469382329581L;
	
	private boolean useStaticFilterOptions;
	private boolean forceAllFilterOptions;
	private String apiOperation;
	
	public boolean isUseStaticFilterOptions() {
		return useStaticFilterOptions;
	}
	public void setUseStaticFilterOptions(boolean useStaticFilterOptions) {
		this.useStaticFilterOptions = useStaticFilterOptions;
	}
	public String getApiOperation() {
		return apiOperation;
	}
	public void setApiOperation(String apiOperation) {
		this.apiOperation = apiOperation;
	}
	public boolean isForceAllFilterOptions() {
		return forceAllFilterOptions;
	}
	public void setForceAllFilterOptions(boolean forceAllFilterOptions) {
		this.forceAllFilterOptions = forceAllFilterOptions;
	}
}

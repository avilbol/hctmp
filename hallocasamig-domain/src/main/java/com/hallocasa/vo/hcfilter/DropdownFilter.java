package com.hallocasa.vo.hcfilter;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.i.ValueObject;

public class DropdownFilter extends HcFilter implements ValueObject, Serializable{

	private static final long serialVersionUID = -888610469382329581L;
	
	private boolean useStaticFilterOptions;
	private List<HcFilterOption> filterOptions;
	public boolean isUseStaticFilterOptions() {
		return useStaticFilterOptions;
	}
	public void setUseStaticFilterOptions(boolean useStaticFilterOptions) {
		this.useStaticFilterOptions = useStaticFilterOptions;
	}
	public List<HcFilterOption> getFilterOptions() {
		return filterOptions;
	}
	public void setFilterOptions(List<HcFilterOption> filterOptions) {
		this.filterOptions = filterOptions;
	}
}

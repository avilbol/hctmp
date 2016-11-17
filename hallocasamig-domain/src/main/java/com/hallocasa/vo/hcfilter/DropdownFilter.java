package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.options.DropdownOptionGroup;

public class DropdownFilter extends HcFilter implements ValueObject, Serializable{

	private static final long serialVersionUID = -888610469382329581L;
	
	private boolean useStaticFilterOptions;
	private boolean forceAllFilterOptions;
	private DropdownOptionGroup dropdownOptionGroup;
	
	public boolean isUseStaticFilterOptions() {
		return useStaticFilterOptions;
	}
	public void setUseStaticFilterOptions(boolean useStaticFilterOptions) {
		this.useStaticFilterOptions = useStaticFilterOptions;
	}
	public boolean isForceAllFilterOptions() {
		return forceAllFilterOptions;
	}
	public void setForceAllFilterOptions(boolean forceAllFilterOptions) {
		this.forceAllFilterOptions = forceAllFilterOptions;
	}
	public DropdownOptionGroup getDropdownOptionGroup() {
		return dropdownOptionGroup;
	}
	public void setDropdownOptionGroup(DropdownOptionGroup dropdownOptionGroup) {
		this.dropdownOptionGroup = dropdownOptionGroup;
	}
}

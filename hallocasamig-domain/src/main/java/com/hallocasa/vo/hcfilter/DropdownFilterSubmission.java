package com.hallocasa.vo.hcfilter;

import java.util.List;

import com.hallocasa.vo.properties.PropertyFieldOption;

public class DropdownFilterSubmission extends HcFilterSubmission {

	private static final long serialVersionUID = -392574647713969135L;

	private List<PropertyFieldOption> selectedFilterOptions;

	public List<PropertyFieldOption> getSelectedFilterOptions() {
		return selectedFilterOptions;
	}

	public void setSelectedFilterOptions(List<PropertyFieldOption> selectedFilterOptions) {
		this.selectedFilterOptions = selectedFilterOptions;
	}
}

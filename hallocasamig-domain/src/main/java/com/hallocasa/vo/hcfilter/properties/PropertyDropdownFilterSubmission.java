package com.hallocasa.vo.hcfilter.properties;

import java.util.List;

import com.hallocasa.vo.options.DropdownOption;

public class PropertyDropdownFilterSubmission extends PropertyFilterSubmission {

	private static final long serialVersionUID = -392574647713969135L;

	private List<DropdownOption> selectedFilterOptions;

	public List<DropdownOption> getSelectedFilterOptions() {
		return selectedFilterOptions;
	}

	public void setSelectedFilterOptions(List<DropdownOption> selectedFilterOptions) {
		this.selectedFilterOptions = selectedFilterOptions;
	}
}

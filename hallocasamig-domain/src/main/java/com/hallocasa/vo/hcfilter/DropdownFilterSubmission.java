package com.hallocasa.vo.hcfilter;

import java.util.List;

import com.hallocasa.vo.options.DropdownOption;

public class DropdownFilterSubmission extends HcFilterSubmission {

	private static final long serialVersionUID = -392574647713969135L;

	private List<DropdownOption> selectedFilterOptions;

	public List<DropdownOption> getSelectedFilterOptions() {
		return selectedFilterOptions;
	}

	public void setSelectedFilterOptions(List<DropdownOption> selectedFilterOptions) {
		this.selectedFilterOptions = selectedFilterOptions;
	}
}

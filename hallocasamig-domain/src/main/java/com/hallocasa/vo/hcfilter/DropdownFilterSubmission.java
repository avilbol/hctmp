package com.hallocasa.vo.hcfilter;

import java.util.List;

public class DropdownFilterSubmission extends HcFilterSubmission {

	private static final long serialVersionUID = -392574647713969135L;

	private List<HcFilterOption> selectedFilterOptions;

	public List<HcFilterOption> getSelectedFilterOptions() {
		return selectedFilterOptions;
	}

	public void setSelectedFilterOptions(List<HcFilterOption> selectedFilterOptions) {
		this.selectedFilterOptions = selectedFilterOptions;
	}
}

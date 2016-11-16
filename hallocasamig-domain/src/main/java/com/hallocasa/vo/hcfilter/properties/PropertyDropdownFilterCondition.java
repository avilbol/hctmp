package com.hallocasa.vo.hcfilter.properties;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.hcfilter.DropdownFilterCondition;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.options.DropdownOption;

/**
 * @author avillamil
 */
public class PropertyDropdownFilterCondition extends DropdownFilterCondition implements ValueObject, Serializable {

	private static final long serialVersionUID = 6125757017766000275L;

	private List<DropdownOption> selectedOptions;

	public List<DropdownOption> getSelectedOptions() {
		return selectedOptions;
	}

	public void setSelectedOptions(List<DropdownOption> selectedOptions) {
		this.selectedOptions = selectedOptions;
	}
}

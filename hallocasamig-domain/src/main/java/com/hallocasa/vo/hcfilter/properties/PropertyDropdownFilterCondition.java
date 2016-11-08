package com.hallocasa.vo.hcfilter.properties;

import java.util.List;

import com.hallocasa.vo.hcfilter.DropdownFilterCondition;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.properties.PropertyFieldOption;

/**
 * @author avillamil
 */
public class PropertyDropdownFilterCondition extends DropdownFilterCondition implements ValueObject {

	private static final long serialVersionUID = 6125757017766000275L;

	private List<PropertyFieldOption> selectedOptions;

	public List<PropertyFieldOption> getSelectedOptions() {
		return selectedOptions;
	}

	public void setSelectedOptions(List<PropertyFieldOption> selectedOptions) {
		this.selectedOptions = selectedOptions;
	}
}

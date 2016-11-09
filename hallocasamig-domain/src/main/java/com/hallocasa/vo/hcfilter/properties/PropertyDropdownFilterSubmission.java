package com.hallocasa.vo.hcfilter.properties;

import com.hallocasa.vo.hcfilter.DropdownFilterSubmission;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.properties.PropertyField;

public class PropertyDropdownFilterSubmission extends DropdownFilterSubmission implements ValueObject {

	private static final long serialVersionUID = 3988678965646453756L;
	private PropertyField propertyField;

	public PropertyField getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(PropertyField propertyField) {
		this.propertyField = propertyField;
	}
}

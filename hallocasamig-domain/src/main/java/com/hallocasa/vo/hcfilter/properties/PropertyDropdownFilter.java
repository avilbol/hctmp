package com.hallocasa.vo.hcfilter.properties;

import com.hallocasa.vo.hcfilter.DropdownFilter;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.properties.PropertyField;

public class PropertyDropdownFilter extends DropdownFilter implements ValueObject {

	private static final long serialVersionUID = -1148398123591545023L;

	private PropertyField propertyField;

	public PropertyField getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(PropertyField propertyField) {
		this.propertyField = propertyField;
	}
}

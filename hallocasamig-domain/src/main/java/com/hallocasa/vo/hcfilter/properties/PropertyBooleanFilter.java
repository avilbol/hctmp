package com.hallocasa.vo.hcfilter.properties;

import com.hallocasa.vo.hcfilter.BooleanFilter;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.properties.PropertyField;

public class PropertyBooleanFilter extends BooleanFilter implements ValueObject {

	private static final long serialVersionUID = 6103119086860621333L;
	
	private PropertyField propertyField;

	public PropertyField getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(PropertyField propertyField) {
		this.propertyField = propertyField;
	}
}

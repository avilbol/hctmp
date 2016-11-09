package com.hallocasa.vo.hcfilter.properties;

import com.hallocasa.vo.hcfilter.RangeFilter;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.properties.PropertyField;

public class PropertyRangeFilter extends RangeFilter implements ValueObject{

	private static final long serialVersionUID = 2468150895804601170L;
	
	private PropertyField propertyField;

	public PropertyField getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(PropertyField propertyField) {
		this.propertyField = propertyField;
	}
}

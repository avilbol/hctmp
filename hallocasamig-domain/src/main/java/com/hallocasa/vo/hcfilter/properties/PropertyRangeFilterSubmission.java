package com.hallocasa.vo.hcfilter.properties;

import com.hallocasa.vo.hcfilter.RangeFilterSubmission;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.properties.PropertyField;

public class PropertyRangeFilterSubmission extends RangeFilterSubmission implements ValueObject {

	private static final long serialVersionUID = -6798517780709225299L;
	private PropertyField propertyField;

	public PropertyField getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(PropertyField propertyField) {
		this.propertyField = propertyField;
	}
}

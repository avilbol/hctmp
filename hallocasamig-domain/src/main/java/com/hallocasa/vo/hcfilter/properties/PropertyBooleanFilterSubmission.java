package com.hallocasa.vo.hcfilter.properties;

import com.hallocasa.vo.hcfilter.BooleanFilterSubmission;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.properties.PropertyField;

public class PropertyBooleanFilterSubmission extends BooleanFilterSubmission implements ValueObject{

	private static final long serialVersionUID = 3484789726405963767L;
	private PropertyField propertyField;

	public PropertyField getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(PropertyField propertyField) {
		this.propertyField = propertyField;
	}

}

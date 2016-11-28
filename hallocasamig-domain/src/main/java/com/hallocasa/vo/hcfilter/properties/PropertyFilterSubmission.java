package com.hallocasa.vo.hcfilter.properties;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class PropertyFilterSubmission implements Serializable, ValueObject{

	private static final long serialVersionUID = 3624461578129545243L;
	
	private PropertyFilter propertyFilter;

	public PropertyFilter getPropertyFilter() {
		return propertyFilter;
	}

	public void setPropertyFilter(PropertyFilter propertyFilter) {
		this.propertyFilter = propertyFilter;
	}
}

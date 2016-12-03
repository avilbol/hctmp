package com.hallocasa.vo.hcfilter.properties;

import java.io.Serializable;

import com.hallocasa.vo.hcfilter.HcFilterEntry;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.properties.PropertyField;

public class PropertyFilterEntry implements ValueObject, Serializable {

	private static final long serialVersionUID = 6103119086860621333L;
	
	private HcFilterEntry filter;
	
	private PropertyField propertyField;
	
	public PropertyFilterEntry() {
		super();
	}

	public PropertyFilterEntry(HcFilterEntry filter, PropertyField propertyField) {
		super();
		this.filter = filter;
		this.propertyField = propertyField;
	}
	
	public HcFilterEntry getFilter() {
		return filter;
	}

	public void setFilter(HcFilterEntry filter) {
		this.filter = filter;
	}

	public PropertyField getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(PropertyField propertyField) {
		this.propertyField = propertyField;
	}
}

package com.hallocasa.vo.hcfilter.properties;

import java.io.Serializable;

import com.hallocasa.vo.hcfilter.HcFilter;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.properties.PropertyField;

/**
 * VO representing a filter of property list
 * @author avillamil
 */
public class PropertyFilter implements ValueObject, Serializable {

	private static final long serialVersionUID = 6103119086860621333L;
	
	private HcFilter filter;
	
	private PropertyField propertyField;
	
	public PropertyFilter() {
		super();
	}

	public PropertyFilter(HcFilter filter, PropertyField propertyField) {
		super();
		this.filter = filter;
		this.propertyField = propertyField;
	}
	
	public HcFilter getFilter() {
		return filter;
	}

	public void setFilter(HcFilter filter) {
		this.filter = filter;
	}

	public PropertyField getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(PropertyField propertyField) {
		this.propertyField = propertyField;
	}
}

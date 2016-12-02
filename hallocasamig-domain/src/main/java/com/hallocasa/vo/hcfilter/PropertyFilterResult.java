package com.hallocasa.vo.hcfilter;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.hcfilter.properties.Property;
import com.hallocasa.vo.i.ValueObject;

public class PropertyFilterResult implements ValueObject, Serializable{

	private static final long serialVersionUID = 4508583287121095603L;

	private Long count;
	
	private List<Property> propertyList;

	public PropertyFilterResult() {
		super();
	}

	public PropertyFilterResult(Long count, List<Property> propertyList) {
		super();
		this.count = count;
		this.propertyList = propertyList;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	} 
}

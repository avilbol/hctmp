package com.hallocasa.vo.hcfilter.properties;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class PropertyFilterSubmission implements Serializable, ValueObject{

	private static final long serialVersionUID = 3624461578129545243L;
	
	private PropertyFilter filter;

	public PropertyFilter getFilter() {
		return filter;
	}

	public void setFilter(PropertyFilter filter) {
		this.filter = filter;
	}
}

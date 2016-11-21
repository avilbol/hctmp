package com.hallocasa.vo.hcfilter.properties;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class PropertyBooleanFilterSubmission extends PropertyFilterSubmission implements ValueObject, Serializable{
	
	private static final long serialVersionUID = -7095386578286489377L;

	private boolean apply;

	public boolean isApply() {
		return apply;
	}

	public void setApply(boolean apply) {
		this.apply = apply;
	}
}

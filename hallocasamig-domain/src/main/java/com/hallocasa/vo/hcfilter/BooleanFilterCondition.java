package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class BooleanFilterCondition extends HcFilterCondition implements ValueObject, Serializable{

	private static final long serialVersionUID = -1583839438588132488L;
	private boolean apply;
	
	public boolean isApply() {
		return apply;
	}
	public void setApply(boolean apply) {
		this.apply = apply;
	}
}

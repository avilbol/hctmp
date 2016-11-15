package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * @author avillamil
 */
public class FilterShowingStep implements ValueObject, Serializable{

	private static final long serialVersionUID = -3650358021030315986L;
	private FilterOperationStep beforeStep;
	private HcFilterCondition condition;
	private FilterOperationStep afterStep;
	
	public FilterOperationStep getBeforeStep() {
		return beforeStep;
	}
	public void setBeforeStep(FilterOperationStep beforeStep) {
		this.beforeStep = beforeStep;
	}
	public HcFilterCondition getCondition() {
		return condition;
	}
	public void setCondition(HcFilterCondition condition) {
		this.condition = condition;
	}
	public FilterOperationStep getAfterStep() {
		return afterStep;
	}
	public void setAfterStep(FilterOperationStep afterStep) {
		this.afterStep = afterStep;
	}
}
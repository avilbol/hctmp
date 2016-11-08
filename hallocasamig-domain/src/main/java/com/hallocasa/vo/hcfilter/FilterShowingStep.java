package com.hallocasa.vo.hcfilter;

/**
 * @author avillamil
 */
public class FilterShowingStep {

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
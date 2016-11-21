package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * @author avillamil
 */
public class FilterShowingStep implements ValueObject, Serializable{

	private static final long serialVersionUID = -3650358021030315986L;
	private FilterOperationStep sequenceBefore;
	private HcFilterCondition filterCondition;
	private FilterOperationStep sequenceAfter;
	public FilterOperationStep getSequenceBefore() {
		return sequenceBefore;
	}
	public void setSequenceBefore(FilterOperationStep sequenceBefore) {
		this.sequenceBefore = sequenceBefore;
	}
	public HcFilterCondition getFilterCondition() {
		return filterCondition;
	}
	public void setFilterCondition(HcFilterCondition filterCondition) {
		this.filterCondition = filterCondition;
	}
	public FilterOperationStep getSequenceAfter() {
		return sequenceAfter;
	}
	public void setSequenceAfter(FilterOperationStep sequenceAfter) {
		this.sequenceAfter = sequenceAfter;
	}
}
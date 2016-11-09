package com.hallocasa.vo.hcfilter;

/**
 * @author avillamil
 */
public class FilterListingStep {

	private FilterOperationStep sequenceBefore;
	private HcFilter filter;
	private FilterOperationStep sequenceAfter;
	
	public HcFilter getFilter() {
		return filter;
	}
	public void setFilter(HcFilter filter) {
		this.filter = filter;
	}
	public FilterOperationStep getSequenceBefore() {
		return sequenceBefore;
	}
	public void setSequenceBefore(FilterOperationStep sequenceBefore) {
		this.sequenceBefore = sequenceBefore;
	}
	public FilterOperationStep getSequenceAfter() {
		return sequenceAfter;
	}
	public void setSequenceAfter(FilterOperationStep sequenceAfter) {
		this.sequenceAfter = sequenceAfter;
	}
}
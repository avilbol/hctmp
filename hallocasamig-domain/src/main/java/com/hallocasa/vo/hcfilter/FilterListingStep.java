package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * @author avillamil
 */
public class FilterListingStep implements ValueObject, Serializable {

	private static final long serialVersionUID = -92603156244059923L;
	private FilterOperationStep sequenceBefore;
	private HcFilter sequenceFilter;
	private FilterOperationStep sequenceAfter;
	
	public HcFilter getSequenceFilter() {
		return sequenceFilter;
	}
	public void setSequenceFilter(HcFilter sequenceFilter) {
		this.sequenceFilter = sequenceFilter;
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
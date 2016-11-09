package com.hallocasa.vo.hcfilter;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.i.ValueObject;

public class HcFilterRequestSourceSubmision implements Serializable, ValueObject {

	private static final long serialVersionUID = 3624461534229545243L;

	private HcFilter filterToFill;
	private List<HcFilter> parentFilters;
	
	public HcFilter getFilterToFill() {
		return filterToFill;
	}
	
	public void setFilterToFill(HcFilter filterToFill) {
		this.filterToFill = filterToFill;
	}
	
	public List<HcFilter> getParentFilters() {
		return parentFilters;
	}
	
	public void setParentFilters(List<HcFilter> parentFilters) {
		this.parentFilters = parentFilters;
	}
}

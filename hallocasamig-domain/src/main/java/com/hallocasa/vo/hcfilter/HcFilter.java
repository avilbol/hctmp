package com.hallocasa.vo.hcfilter;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.i.ValueObject;

/**
 * VO representing a filter to use in a search system
 * @author avillamil
 */
public class HcFilter implements Serializable, ValueObject{

	private static final long serialVersionUID = 3624461578129545243L;

	private Integer id;	
	private String name;
	private HcFilterType filterType;
	private List<HcFilter> parentFilters;
	private List<FilterApplySequenceElement> obtainSourceSeq;
	private HcFilterShowingSchema showingSeq;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HcFilterType getFilterType() {
		return filterType;
	}
	public void setFilterType(HcFilterType filterType) {
		this.filterType = filterType;
	}
	public List<HcFilter> getParentFilters() {
		return parentFilters;
	}
	public void setParentFilters(List<HcFilter> parentFilters) {
		this.parentFilters = parentFilters;
	}
	public List<FilterApplySequenceElement> getObtainSourceSeq() {
		return obtainSourceSeq;
	}
	public void setObtainSourceSeq(List<FilterApplySequenceElement> obtainSourceSeq) {
		this.obtainSourceSeq = obtainSourceSeq;
	}
	public HcFilterShowingSchema getShowingSeq() {
		return showingSeq;
	}
	public void setShowingSeq(HcFilterShowingSchema showingSeq) {
		this.showingSeq = showingSeq;
	}
}

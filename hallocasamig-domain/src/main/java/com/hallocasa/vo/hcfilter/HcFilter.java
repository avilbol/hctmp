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
	private HcFilterNature filterNature;
	private List<FilterShowingStep> showingStepList;
	private List<FilterListingStep> listingStepList;
	private ShowChoice choice;
	
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
	public List<FilterShowingStep> getShowingStepList() {
		return showingStepList;
	}
	public void setShowingStepList(List<FilterShowingStep> showingStepList) {
		this.showingStepList = showingStepList;
	}
	public List<FilterListingStep> getListingStepList() {
		return listingStepList;
	}
	public void setListingStepList(List<FilterListingStep> listingStepList) {
		this.listingStepList = listingStepList;
	}
	public ShowChoice getChoice() {
		return choice;
	}
	public void setChoice(ShowChoice choice) {
		this.choice = choice;
	}
	public HcFilterNature getFilterNature() {
		return filterNature;
	}
	public void setFilterNature(HcFilterNature filterNature) {
		this.filterNature = filterNature;
	}
}

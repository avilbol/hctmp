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
	private String lang;
	private HcFilterType filterType;
	private HcFilterNature filterNature;
	private List<FilterShowingStep> showingStepList;
	private HcFilter parentFilter;
	private ShowChoice choice;
	private Boolean usePropertyField;
	private FilterWorkerOption filterWorkerOption;
	
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
	public HcFilter getParentFilter() {
		return parentFilter;
	}
	public void setParentFilter(HcFilter parentFilter) {
		this.parentFilter = parentFilter;
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
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public Boolean getUsePropertyField() {
		return usePropertyField;
	}
	public void setUsePropertyField(Boolean usePropertyField) {
		this.usePropertyField = usePropertyField;
	}
	public FilterWorkerOption getFilterWorkerOption() {
		return filterWorkerOption;
	}
	public void setFilterWorkerOption(FilterWorkerOption filterWorkerOption) {
		this.filterWorkerOption = filterWorkerOption;
	}
}

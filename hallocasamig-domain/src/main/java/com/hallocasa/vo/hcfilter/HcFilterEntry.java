package com.hallocasa.vo.hcfilter;

import java.io.Serializable;
import java.util.List;

import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.options.DropdownOptionGroup;

/**
 * Filter entry with all important data configuration related
 */
public class HcFilterEntry implements Serializable, ValueObject {

	private static final long serialVersionUID = 3624461578129545243L;

	private Integer id;
	private String name;
	private String lang;
	private HcFilterTypeEntry filterType;
	private HcFilterNature filterNature;
	private List<FilterShowingStep> showingStepList;
	private HcFilter parentFilter;
	private ShowChoice choice;
	private Boolean usePropertyField;
	private FilterWorkerOption filterWorkerOption;
	private Double minValue;
	private Double maxValue;
	private Boolean minValueDateInPast;
	private Boolean minValueDateInFuture;
	private Boolean maxValueDateInPast;
	private Boolean maxValueDateInFuture;
	private boolean useStaticFilterOptions;
	private boolean forceAllFilterOptions;
	private DropdownOptionGroup dropdownOptionGroup;
	private String yesText;
	private String noText;

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

	public HcFilterTypeEntry getFilterType() {
		return filterType;
	}

	public void setFilterType(HcFilterTypeEntry filterType) {
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

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public Boolean getMinValueDateInPast() {
		return minValueDateInPast;
	}

	public void setMinValueDateInPast(Boolean minValueDateInPast) {
		this.minValueDateInPast = minValueDateInPast;
	}

	public Boolean getMinValueDateInFuture() {
		return minValueDateInFuture;
	}

	public void setMinValueDateInFuture(Boolean minValueDateInFuture) {
		this.minValueDateInFuture = minValueDateInFuture;
	}

	public Boolean getMaxValueDateInPast() {
		return maxValueDateInPast;
	}

	public void setMaxValueDateInPast(Boolean maxValueDateInPast) {
		this.maxValueDateInPast = maxValueDateInPast;
	}

	public Boolean getMaxValueDateInFuture() {
		return maxValueDateInFuture;
	}

	public void setMaxValueDateInFuture(Boolean maxValueDateInFuture) {
		this.maxValueDateInFuture = maxValueDateInFuture;
	}

	public boolean isUseStaticFilterOptions() {
		return useStaticFilterOptions;
	}

	public void setUseStaticFilterOptions(boolean useStaticFilterOptions) {
		this.useStaticFilterOptions = useStaticFilterOptions;
	}

	public boolean isForceAllFilterOptions() {
		return forceAllFilterOptions;
	}

	public void setForceAllFilterOptions(boolean forceAllFilterOptions) {
		this.forceAllFilterOptions = forceAllFilterOptions;
	}

	public DropdownOptionGroup getDropdownOptionGroup() {
		return dropdownOptionGroup;
	}

	public void setDropdownOptionGroup(DropdownOptionGroup dropdownOptionGroup) {
		this.dropdownOptionGroup = dropdownOptionGroup;
	}

	public String getYesText() {
		return yesText;
	}

	public void setYesText(String yesText) {
		this.yesText = yesText;
	}

	public String getNoText() {
		return noText;
	}

	public void setNoText(String noText) {
		this.noText = noText;
	}
}

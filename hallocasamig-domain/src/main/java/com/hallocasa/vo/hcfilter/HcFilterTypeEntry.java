package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * Filter type entry with all important data configuration related
 */
public class HcFilterTypeEntry implements Serializable, ValueObject {

	private static final long serialVersionUID = 8385629228688826281L;
	private Integer id;
	private String name;
	private HcFilterTypeNature filterTypeNature;
	private Boolean useSlider;
	private Boolean onlyFrom;
	private Boolean onlyTo;
	private Boolean validateMin;
	private Boolean validateMax;
	private RangeFieldPresentation rangeFieldPresentation;
	private Boolean allowMultiple;
	private Boolean useSearch;
	private Boolean useSort;
	private Boolean useSelectAll;
	private Boolean useRemoteList;
	private Boolean useLinks;
	private Boolean useYesNoDropdown;
	private Boolean useCheckbox;
	private Boolean useRadio;
	private Boolean useText;
	private Boolean toggle;
	private Boolean sortSign;

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

	public HcFilterTypeNature getFilterTypeNature() {
		return filterTypeNature;
	}

	public void setFilterTypeNature(HcFilterTypeNature filterTypeNature) {
		this.filterTypeNature = filterTypeNature;
	}

	public Boolean getUseSlider() {
		return useSlider;
	}

	public void setUseSlider(Boolean useSlider) {
		this.useSlider = useSlider;
	}

	public Boolean getOnlyFrom() {
		return onlyFrom;
	}

	public void setOnlyFrom(Boolean onlyFrom) {
		this.onlyFrom = onlyFrom;
	}

	public Boolean getOnlyTo() {
		return onlyTo;
	}

	public void setOnlyTo(Boolean onlyTo) {
		this.onlyTo = onlyTo;
	}

	public Boolean getValidateMin() {
		return validateMin;
	}

	public void setValidateMin(Boolean validateMin) {
		this.validateMin = validateMin;
	}

	public Boolean getValidateMax() {
		return validateMax;
	}

	public void setValidateMax(Boolean validateMax) {
		this.validateMax = validateMax;
	}

	public RangeFieldPresentation getRangeFieldPresentation() {
		return rangeFieldPresentation;
	}

	public void setRangeFieldPresentation(RangeFieldPresentation rangeFieldPresentation) {
		this.rangeFieldPresentation = rangeFieldPresentation;
	}

	public Boolean getAllowMultiple() {
		return allowMultiple;
	}

	public void setAllowMultiple(Boolean allowMultiple) {
		this.allowMultiple = allowMultiple;
	}

	public Boolean getUseSearch() {
		return useSearch;
	}

	public void setUseSearch(Boolean useSearch) {
		this.useSearch = useSearch;
	}

	public Boolean getUseSort() {
		return useSort;
	}

	public void setUseSort(Boolean useSort) {
		this.useSort = useSort;
	}

	public Boolean getUseSelectAll() {
		return useSelectAll;
	}

	public void setUseSelectAll(Boolean useSelectAll) {
		this.useSelectAll = useSelectAll;
	}

	public Boolean getUseRemoteList() {
		return useRemoteList;
	}

	public void setUseRemoteList(Boolean useRemoteList) {
		this.useRemoteList = useRemoteList;
	}

	public Boolean getUseLinks() {
		return useLinks;
	}

	public void setUseLinks(Boolean useLinks) {
		this.useLinks = useLinks;
	}

	public Boolean getUseYesNoDropdown() {
		return useYesNoDropdown;
	}

	public void setUseYesNoDropdown(Boolean useYesNoDropdown) {
		this.useYesNoDropdown = useYesNoDropdown;
	}

	public Boolean getUseCheckbox() {
		return useCheckbox;
	}

	public void setUseCheckbox(Boolean useCheckbox) {
		this.useCheckbox = useCheckbox;
	}

	public Boolean getUseRadio() {
		return useRadio;
	}

	public void setUseRadio(Boolean useRadio) {
		this.useRadio = useRadio;
	}

	public Boolean getUseText() {
		return useText;
	}

	public void setUseText(Boolean useText) {
		this.useText = useText;
	}

	public Boolean getToggle() {
		return toggle;
	}

	public void setToggle(Boolean toggle) {
		this.toggle = toggle;
	}

	public Boolean getSortSign() {
		return sortSign;
	}

	public void setSortSign(Boolean sortSign) {
		this.sortSign = sortSign;
	}
}

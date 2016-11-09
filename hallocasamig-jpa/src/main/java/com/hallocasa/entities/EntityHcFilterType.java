package com.hallocasa.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.persistence.converters.HcBooleanConverter;
import com.hallocasa.persistence.converters.HcFilterTypeNatureConverter;
import com.hallocasa.persistence.converters.RangeFieldPresentationConverter;
import com.hallocasa.vo.hcfilter.HcFilterTypeNature;
import com.hallocasa.vo.hcfilter.RangeFieldPresentation;

@Entity
@Table(name = "filter_type")
public class EntityHcFilterType implements HallocasaEntity {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name varchar")
	private String name;
	
	@Convert(converter=HcFilterTypeNatureConverter.class)
	@Column(name = "filter_type_nature")
	private HcFilterTypeNature filterTypeNature;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "use_slider")
	private Boolean useSlider;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "validate_min")
	private Boolean validateMin;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "validate_max")
	private Boolean validateMax;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "allow_multiple")
	private Boolean allowMultiple;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "use_search")
	private Boolean useSearch;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "use_sort")
	private Boolean useSort;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "use_select_all")
	private Boolean useSelectAll;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "use_remote_list")
	private Boolean useRemoteList;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "use_links")
	private Boolean useLinks;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "use_yes_no_dropdown")
	private Boolean useYesNoDropdown;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "useCheckbox")
	private Boolean useCheckbox;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "useRadio")
	private Boolean useRadio;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "useText")
	private Boolean useText;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "toggle")
	private Boolean toggle;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "sort_sign")
	private Boolean sortSign;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "only_from")
	private Boolean onlyFrom;
	
	@Convert(converter=HcBooleanConverter.class)
	@Column(name = "only_to")
	private Boolean onlyTo;
	
	@Convert(converter=RangeFieldPresentationConverter.class)
	@Column(name = "range_field_presentation")
	private RangeFieldPresentation rangeFieldPresentation;
	
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

	public RangeFieldPresentation getRangeFieldPresentation() {
		return rangeFieldPresentation;
	}

	public void setRangeFieldPresentation(RangeFieldPresentation rangeFieldPresentation) {
		this.rangeFieldPresentation = rangeFieldPresentation;
	}
}

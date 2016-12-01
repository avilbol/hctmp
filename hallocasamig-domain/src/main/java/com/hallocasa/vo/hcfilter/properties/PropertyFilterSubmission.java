package com.hallocasa.vo.hcfilter.properties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hallocasa.vo.CurrencyAmmount;
import com.hallocasa.vo.i.ValueObject;
import com.hallocasa.vo.options.DropdownOption;


public class PropertyFilterSubmission implements Serializable, ValueObject {

	private static final long serialVersionUID = 3624461578129545243L;

	private boolean apply;
	
	private PropertyFilterEntry propertyFilter;

	private Double minValue;
	private Double maxValue;

	private Date minDateValue;
	private Date maxDateValue;
	
	private CurrencyAmmount minCrcyValue;
	private CurrencyAmmount maxCrcyValue;

	private List<DropdownOption> selectedFilterOptions;

	public boolean isApply() {
		return apply;
	}

	public void setApply(boolean apply) {
		this.apply = apply;
	}
	
	public List<DropdownOption> getSelectedFilterOptions() {
		return selectedFilterOptions;
	}

	public void setSelectedFilterOptions(List<DropdownOption> selectedFilterOptions) {
		this.selectedFilterOptions = selectedFilterOptions;
	}
	
	public PropertyFilterEntry getPropertyFilter() {
		return propertyFilter;
	}

	public void setPropertyFilter(PropertyFilterEntry propertyFilter) {
		this.propertyFilter = propertyFilter;
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

	public Date getMinDateValue() {
		return minDateValue;
	}

	public void setMinDateValue(Date minDateValue) {
		this.minDateValue = minDateValue;
	}

	public Date getMaxDateValue() {
		return maxDateValue;
	}

	public void setMaxDateValue(Date maxDateValue) {
		this.maxDateValue = maxDateValue;
	}

	public CurrencyAmmount getMinCrcyValue() {
		return minCrcyValue;
	}

	public void setMinCrcyValue(CurrencyAmmount minCrcyValue) {
		this.minCrcyValue = minCrcyValue;
	}

	public CurrencyAmmount getMaxCrcyValue() {
		return maxCrcyValue;
	}

	public void setMaxCrcyValue(CurrencyAmmount maxCrcyValue) {
		this.maxCrcyValue = maxCrcyValue;
	}
}

package com.hallocasa.vo.hcfilter.properties;

import java.io.Serializable;
import java.util.Date;

import com.hallocasa.vo.i.ValueObject;

public class PropertyRangeFilterSubmission extends PropertyFilterSubmission implements ValueObject, Serializable{
	
	private static final long serialVersionUID = -7095382938286489377L;

	private Double minValue;
	private Double maxValue;
	
	private Date minDateValue;
	private Date maxDateValue;
	
	
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
}

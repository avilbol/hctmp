package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class RangeFilter extends HcFilter implements ValueObject, Serializable{

	private static final long serialVersionUID = -1588291146006557531L;
	private Double minValue;
	private Double maxValue;
	private Boolean minValueDateInPast;
	private Boolean minValueDateInFuture;
	private Boolean maxValueDateInPast;
	private Boolean maxValueDateInFuture;
	
	public Double getMinValue() {
		return minValue;
	}
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	public Double getMaxValue() {
		return maxValue;
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
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
}

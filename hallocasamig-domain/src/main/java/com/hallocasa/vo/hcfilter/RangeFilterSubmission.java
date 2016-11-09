package com.hallocasa.vo.hcfilter;

import java.io.Serializable;
import java.util.Date;

import com.hallocasa.vo.i.ValueObject;

public class RangeFilterSubmission extends HcFilterSubmission implements ValueObject, Serializable{
	
	private static final long serialVersionUID = -7095382938286489377L;

	private double minValue;
	private double maxValue;
	
	private Date minDateValue;
	private Date maxDateValue;
	
	public double getMinValue() {
		return minValue;
	}
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}
	public double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(double maxValue) {
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

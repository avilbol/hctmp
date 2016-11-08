package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class RangeFilterSubmission extends HcFilterSubmission implements ValueObject, Serializable{
	
	private static final long serialVersionUID = -7095382938286489377L;

	private double minValue;
	private double maxValue;
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
}

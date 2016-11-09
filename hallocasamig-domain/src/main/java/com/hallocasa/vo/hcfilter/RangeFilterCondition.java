package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * Condition applied to filter, in order to validate some of his properties
 * or get the source list according to their values
 * @author avillamil
 */
public class RangeFilterCondition  extends HcFilterCondition implements ValueObject, Serializable {

	private static final long serialVersionUID = 671012053903252721L;
	private RangeOperand minOperand;
	private RangeOperand maxOperand;
	private double minValue;
	private double maxValue;
	private double minDateValue;
	private double maxDateValue;
	private boolean applyMinConstraint;
	private boolean applyMaxConstraint;
	
	public RangeOperand getMinOperand() {
		return minOperand;
	}
	public void setMinOperand(RangeOperand minOperand) {
		this.minOperand = minOperand;
	}
	public RangeOperand getMaxOperand() {
		return maxOperand;
	}
	public void setMaxOperand(RangeOperand maxOperand) {
		this.maxOperand = maxOperand;
	}
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
	public boolean isApplyMinConstraint() {
		return applyMinConstraint;
	}
	public void setApplyMinConstraint(boolean applyMinConstraint) {
		this.applyMinConstraint = applyMinConstraint;
	}
	public boolean isApplyMaxConstraint() {
		return applyMaxConstraint;
	}
	public void setApplyMaxConstraint(boolean applyMaxConstraint) {
		this.applyMaxConstraint = applyMaxConstraint;
	}
	public double getMinDateValue() {
		return minDateValue;
	}
	public void setMinDateValue(double minDateValue) {
		this.minDateValue = minDateValue;
	}
	public double getMaxDateValue() {
		return maxDateValue;
	}
	public void setMaxDateValue(double maxDateValue) {
		this.maxDateValue = maxDateValue;
	}
}

package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * VO que representa un tipo de filtro rango
 * @author avillamil
 */
public class RangeFilterType extends HcFilterType implements Serializable, ValueObject {

	private static final long serialVersionUID = 4253281090779378505L;
	private Boolean useSlider;
	private Boolean onlyFrom;
	private Boolean onlyTo;
	private Boolean validateMin;
	private Boolean validateMax;
	private RangeFieldPresentation rangeFieldPresentation;

	public Boolean isUseSlider() {
		return useSlider;
	}

	public void setUseSlider(Boolean useSlider) {
		this.useSlider = useSlider;
	}

	public Boolean isValidateMin() {
		return validateMin;
	}

	public void setValidateMin(Boolean validateMin) {
		this.validateMin = validateMin;
	}

	public Boolean isValidateMax() {
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

	public Boolean isOnlyFrom() {
		return onlyFrom;
	}

	public void setOnlyFrom(Boolean onlyFrom) {
		this.onlyFrom = onlyFrom;
	}

	public Boolean isOnlyTo() {
		return onlyTo;
	}

	public void setOnlyTo(Boolean onlyTo) {
		this.onlyTo = onlyTo;
	}
}

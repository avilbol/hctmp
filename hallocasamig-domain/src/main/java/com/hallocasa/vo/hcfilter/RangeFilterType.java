package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * VO que representa un tipo de filtro rango
 * @author avillamil
 */
public class RangeFilterType extends HcFilterType implements Serializable, ValueObject {

	private static final long serialVersionUID = 4253281090779378505L;
	private boolean useSlider;
	private boolean validateMin;
	private boolean validateMax;

	public boolean isUseSlider() {
		return useSlider;
	}

	public void setUseSlider(boolean useSlider) {
		this.useSlider = useSlider;
	}

	public boolean isValidateMin() {
		return validateMin;
	}

	public void setValidateMin(boolean validateMin) {
		this.validateMin = validateMin;
	}

	public boolean isValidateMax() {
		return validateMax;
	}

	public void setValidateMax(boolean validateMax) {
		this.validateMax = validateMax;
	}
}

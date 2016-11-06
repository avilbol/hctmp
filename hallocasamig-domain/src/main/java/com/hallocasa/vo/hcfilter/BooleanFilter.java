package com.hallocasa.vo.hcfilter;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * VO representing a boolean (yes/no) filter to use in a search system
 * @author avillamil
 */
public class BooleanFilter extends HcFilter implements ValueObject, Serializable {
	
	private static final long serialVersionUID = -6356538012502235301L;
	
	private String yesText;
	private String noText;
	
	public String getYesText() {
		return yesText;
	}
	public void setYesText(String yesText) {
		this.yesText = yesText;
	}
	public String getNoText() {
		return noText;
	}
	public void setNoText(String noText) {
		this.noText = noText;
	}
}

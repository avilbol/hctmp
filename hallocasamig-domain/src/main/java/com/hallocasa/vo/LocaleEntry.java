package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * Vo representing an application locale entry managed in system
 * @author avillamil
 */
public class LocaleEntry  implements ValueObject, Serializable {

	private static final long serialVersionUID = 1L;
	
	private String pnemonic;
	private String locale;
	private String langKey;
	private String langValue;
	
	public String getPnemonic() {
		return pnemonic;
	}
	
	public void setPnemonic(String pnemonic) {
		this.pnemonic = pnemonic;
	}
	
	public String getLocale() {
		return locale;
	}
	
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	public String getLangKey() {
		return langKey;
	}
	
	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}
	
	public String getLangValue() {
		return langValue;
	}
	
	public void setLangValue(String langValue) {
		this.langValue = langValue;
	}
}

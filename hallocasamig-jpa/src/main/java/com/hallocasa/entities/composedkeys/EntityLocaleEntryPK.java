package com.hallocasa.entities.composedkeys;

/**
 * Composite primary key for locale entry element
 * @author avilbol
 */
public class EntityLocaleEntryPK {

	private String pnemonic;
	private String locale;
	
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
}

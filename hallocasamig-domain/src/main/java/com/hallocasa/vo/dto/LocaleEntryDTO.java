package com.hallocasa.vo.dto;

/**
 * Data transfer object for locale entry, supporting locales managed bu hallocasa
 * @author avilbol
 */
public class LocaleEntryDTO {

	private String pnemonic;
	private String langKey;
	private String en;
	private String es;
	private String de;
	
	public String getPnemonic() {
		return pnemonic;
	}

	public void setPnemonic(String pnemonic) {
		this.pnemonic = pnemonic;
	}

	public String getLangKey() {
		return langKey;
	}

	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}

	public String getEn() {
		return en;
	}
	
	public void setEn(String en) {
		this.en = en;
	}
	
	public String getEs() {
		return es;
	}
	
	public void setEs(String es) {
		this.es = es;
	}
	
	public String getDe() {
		return de;
	}
	
	public void setDe(String de) {
		this.de = de;
	}
}

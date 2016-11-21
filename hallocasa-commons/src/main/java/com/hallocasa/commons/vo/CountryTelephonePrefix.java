package com.hallocasa.commons.vo;

import java.io.Serializable;

import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value Object for telephone prefix (for countries)
 *
 * @author Alexander Villamil
 * @since 1.7
 */
public class CountryTelephonePrefix implements Serializable, ValueObject {

	private static final long serialVersionUID = -1515313751946340689L;
	
	private Integer prefix;
	
	private String name;
	
	private String lang;
	
	public CountryTelephonePrefix() {
		super();
	}

	public Integer getPrefix() {
		return prefix;
	}

	public void setPrefix(Integer prefix) {
		this.prefix = prefix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
}

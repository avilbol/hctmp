package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * Value object for preferred settings (currencies and locales) in a country
 * @author Alexander
 */
public class PreferredSetting implements ValueObject, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Query for find all registries
	 */
	public static final String QUERY_FIND = "EntityPreferredSetting.find";
	
	/**
	 * Language locale preferred in country
	 */
	private String locale;

	/**
	 * Country of preferred settings
	 */
	private Country country;
	
	/**
	 * Currency for the country
	 */
	private Currency firstCurrency;
	
	/**
	 * Second possible currency for the country
	 */
	private Currency secondCurrency;

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Currency getFirstCurrency() {
		return firstCurrency;
	}

	public void setFirstCurrency(Currency firstCurrency) {
		this.firstCurrency = firstCurrency;
	}

	public Currency getSecondCurrency() {
		return secondCurrency;
	}

	public void setSecondCurrency(Currency secondCurrency) {
		this.secondCurrency = secondCurrency;
	}
}
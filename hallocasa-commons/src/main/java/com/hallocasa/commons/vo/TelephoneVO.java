package com.hallocasa.commons.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.hallocasa.commons.validation.NotEmpty;
import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value Object for telephone
 *
 * @author Alexander Villamil
 * @since 1.7
 */
public class TelephoneVO implements Serializable, ValueObject {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -7420932080600290247L;

	/**
	 * Instance variables 
	 */
    private Long id;
	
	/**
	 * Country prefix 
	 */
    @NotNull
	private CountryTelephonePrefix countryTelephonePrefix;
	
	/**
	 * Telephone number
	 */
	@NotNull
	@NotEmpty
	private String number;

	
	
	public TelephoneVO() {
		super();
	}
	
	public CountryTelephonePrefix getCountryTelephonePrefix() {
		return countryTelephonePrefix;
	}

	public void setCountryTelephonePrefix(CountryTelephonePrefix countryTelephonePrefix) {
		this.countryTelephonePrefix = countryTelephonePrefix;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}

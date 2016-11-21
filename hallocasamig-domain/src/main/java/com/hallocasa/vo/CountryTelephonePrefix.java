package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * Value Object for telephone prefix (for countries)
 *
 * @author Alexander Villamil
 * @since 1.7
 */
public class CountryTelephonePrefix  implements Serializable, ValueObject {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -7420932080600290247L;
	
	/**
	 * identifier
	 */
	private Long id;
	
	/**
	 * Prefix number
	 */
	private Integer prefix;
	
	/**
	 * Prefix description
	 */
	private String name;
	
	/**
	 * Lang
	 */
	private String lang;
	
	public CountryTelephonePrefix() {
		super();
	}
	
	public CountryTelephonePrefix(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

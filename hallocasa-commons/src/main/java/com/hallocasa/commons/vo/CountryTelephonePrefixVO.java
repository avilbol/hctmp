package com.hallocasa.commons.vo;

import java.io.Serializable;

import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value Object for telephone prefix (for countries)
 *
 * @author Alexander Villamil
 * @since 1.7
 */
public class CountryTelephonePrefixVO  implements Serializable, ValueObject {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -7420932080600290247L;
	
	/**
	 * Instance variables 
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
	
	public CountryTelephonePrefixVO() {
		super();
	}
	
	public CountryTelephonePrefixVO(Long id) {
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
}

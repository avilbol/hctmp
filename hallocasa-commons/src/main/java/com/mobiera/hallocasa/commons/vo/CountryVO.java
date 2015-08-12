package com.mobiera.hallocasa.commons.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.mobiera.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * The persistent class for the country database table.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Country", propOrder = {
		"id",
		"countryName",
		"defaultLanguage",
		"code",
		"countryIndicative"
		
})
public class CountryVO implements Serializable, ValueObject {

	private static final long serialVersionUID = 1L;
	/**
	 * Name of the id property
	 */
	public static final String id_ = "id";
	/**
	 * Name of the country name property
	 */
	public static final String countryName_ = "countryName";
	public static final String defaultLanguage_ = "defaultLanguage";
	public static final String countryIndicative_ = "countryIndicative";

	private Long id;
	private String countryName;
	private LanguageVO defaultLanguage;
	private String code;
	private String countryIndicative;

	/**
	 * Constructor
	 */
	public CountryVO() {
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 */
	public CountryVO(Long id) {
		super();
		this.id = id;
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param countryName
	 * @param defaultLanguage
	 * @param code
	 * @param countryIndicative
	 */
	public CountryVO(Long id, String countryName, LanguageVO defaultLanguage,
			String code, String countryIndicative) {
		super();
		this.id = id;
		this.countryName = countryName;
		this.defaultLanguage = defaultLanguage;
		this.code = code;
		this.countryIndicative = countryIndicative;
	}

	/**
	 * Getter for id
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for id
	 * 
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for countryName
	 * 
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * Setter for countryName
	 * 
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * Getter for defaultLanguage
	 * 
	 * @return the defaultLanguage
	 */
	public LanguageVO getDefaultLanguage() {
		return defaultLanguage;
	}

	/**
	 * Setter for defaultLanguage
	 * 
	 * @param defaultLanguage the defaultLanguage to set
	 */
	public void setDefaultLanguage(LanguageVO defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	/**
	 * Getter for code
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Setter for code
	 * 
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Getter for countryIndicative
	 * 
	 * @return the countryIndicative
	 */
	public String getCountryIndicative() {
		return countryIndicative;
	}

	/**
	 * Setter for countryIndicative
	 * 
	 * @param countryIndicative the countryIndicative to set
	 */
	public void setCountryIndicative(String countryIndicative) {
		this.countryIndicative = countryIndicative;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryVO other = (CountryVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
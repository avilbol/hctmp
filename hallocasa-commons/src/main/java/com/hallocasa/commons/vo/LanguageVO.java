package com.hallocasa.commons.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * The persistent class for the country database table.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Language", propOrder = {
		"id",
		"ietfCode",
		"languageName",
		"supportedFlag",
		"javaLocale"
		
})
public class LanguageVO implements Serializable, ValueObject {

	private static final long serialVersionUID = 1L;

	public static final String languageName_ = "languageName";

	private Long id;
	private String ietfCode;
	private String languageName;
	private Boolean supportedFlag;
	private String javaLocale;

	/**
	 * Constructor
	 */
	public LanguageVO() {
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param ietfCode
	 * @param languageName
	 * @param supportedFlag
	 * @param javaLocale
	 */
	public LanguageVO(Long id, String ietfCode, String languageName,
		Boolean supportedFlag, String javaLocale) {
		super();
		this.id = id;
		this.ietfCode = ietfCode;
		this.languageName = languageName;
		this.supportedFlag = supportedFlag;
		this.javaLocale = javaLocale;
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
	 * Getter for ietfCode
	 * 
	 * @return the ietfCode
	 */
	public String getIetfCode() {
		return ietfCode;
	}

	/**
	 * Setter for ietfCode
	 * 
	 * @param ietfCode the ietfCode to set
	 */
	public void setIetfCode(String ietfCode) {
		this.ietfCode = ietfCode;
	}

	/**
	 * Getter for languageName
	 * 
	 * @return the languageName
	 */
	public String getLanguageName() {
		return languageName;
	}

	/**
	 * Setter for languageName
	 * 
	 * @param languageName the languageName to set
	 */
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	/**
	 * Getter for supportedFlag
	 * 
	 * @return the supportedFlag
	 */
	public Boolean getSupportedFlag() {
		return supportedFlag;
	}

	/**
	 * Setter for supportedFlag
	 * 
	 * @param supportedFlag the supportedFlag to set
	 */
	public void setSupportedFlag(Boolean supportedFlag) {
		this.supportedFlag = supportedFlag;
	}

	/**
	 * Getter for javaLocale
	 * 
	 * @return the javaLocale
	 */
	public String getJavaLocale() {
		return javaLocale;
	}

	/**
	 * Setter for javaLocale
	 * 
	 * @param javaLocale the javaLocale to set
	 */
	public void setJavaLocale(String javaLocale) {
		this.javaLocale = javaLocale;
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
		LanguageVO other = (LanguageVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id.toString();
	}

}
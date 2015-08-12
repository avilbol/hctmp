package com.mobiera.hallocasa.commons.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.mobiera.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value Object for application
 * 
 * @author David Mantilla
 * @since 1.7
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "App", propOrder = {
		"id",
		"appName",
		"description",
		"mainUrl",
		"activationUrl"
		
})
public class AppVO implements ValueObject {
	/* static fields */

	/* instance variables */
	private Long id;
	private String appName;
	private String description;
	private String mainUrl;
	private String activationUrl;

	/* constructors */

	/**
	 * Default Constructor
	 */
	public AppVO() {
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 */
	public AppVO(Long id) {
		super();
		this.id = id;
	}

	/* Methods */

	/**
	 * Constructor
	 * 
	 * @param appName
	 * @param description
	 * @param mainUrl
	 * @param activationUrl
	 */
	public AppVO(String appName, String description, String mainUrl,
		String activationUrl) {
		super();
		this.appName = appName;
		this.description = description;
		this.mainUrl = mainUrl;
		this.activationUrl = activationUrl;
	}

	/* Getters & Setters */
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
	 * Getter for appName
	 * 
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * Setter for appName
	 * 
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * Getter for description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter for description
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter for mainUrl
	 * 
	 * @return the mainUrl
	 */
	public String getMainUrl() {
		return mainUrl;
	}

	/**
	 * Setter for mainUrl
	 * 
	 * @param mainUrl the mainUrl to set
	 */
	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	/**
	 * Getter for activationUrl
	 * 
	 * @return the activationUrl
	 */
	public String getActivationUrl() {
		return activationUrl;
	}

	/**
	 * Setter for activationUrl
	 * 
	 * @param activationUrl the activationUrl to set
	 */
	public void setActivationUrl(String activationUrl) {
		this.activationUrl = activationUrl;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		AppVO other = (AppVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

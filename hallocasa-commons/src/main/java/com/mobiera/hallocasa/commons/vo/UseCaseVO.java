package com.mobiera.hallocasa.commons.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.mobiera.hallocasa.commons.i18n.ValidationMessages;
import com.mobiera.hallocasa.commons.validation.NotEmpty;
import com.mobiera.hallocasa.commons.validation.ValidationPatterns;
import com.mobiera.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * UseCase value object class
 * 
 * @author David Mantilla
 *
 * @since 1.7
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UseCase", propOrder = { "id", "description", "useCaseName" })
public class UseCaseVO implements ValueObject {

	private static final long serialVersionUID = -4607470117623239723L;
	public static final String useCaseName_ = "useCaseName";
	public static final String description_ = "description";
	public static final String appId_ = "appId";

	private Long id;

	@NotNull
	@NotEmpty
	@Pattern(regexp = ValidationPatterns.USE_CASE_NAME_PATTERN, message = "{"
		+ ValidationMessages.USE_CASE_NAME_PATTERN + "}")
	@Size(min = 0, max = 100)
	private String useCaseName;

	@Size(min = 0, max = 512)
	private String description;

	@XmlTransient
	@NotNull
	private Long appId;

	/**
	 * Constructor
	 */
	public UseCaseVO() {
	}

	/**
	 * @param id
	 */
	public UseCaseVO(Long id) {
		super();
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the useCaseName
	 */
	public String getUseCaseName() {
		return useCaseName;
	}

	/**
	 * @param useCaseName the useCaseName to set
	 */
	public void setUseCaseName(String useCaseName) {
		this.useCaseName = useCaseName;
	}

	/**
	 * Getter for appId
	 * 
	 * @return the appId
	 */
	public Long getAppId() {
		return appId;
	}

	/**
	 * Setter for appId
	 * 
	 * @param appId the appId to set
	 */
	public void setAppId(Long appId) {
		this.appId = appId;
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
		UseCaseVO other = (UseCaseVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

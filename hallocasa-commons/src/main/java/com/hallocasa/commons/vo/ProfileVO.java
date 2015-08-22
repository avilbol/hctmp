package com.hallocasa.commons.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.hallocasa.commons.i18n.ValidationMessages;
import com.hallocasa.commons.validation.NotEmpty;
import com.hallocasa.commons.validation.ValidationPatterns;
import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Profile Value Object
 * 
 * @author David Mantilla
 * @since 1.7
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Profile", propOrder = { "id", "description", "profileName",
	"appId" })
public class ProfileVO implements ValueObject {

	private static final long serialVersionUID = -2702180807923061875L;
	public static final String profileName_ = "profileName";
	public static final String appId_ = "appId";

	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 0, max = 45)
	@Pattern(regexp = ValidationPatterns.GENERAL_NAME, message = "{"
		+ ValidationMessages.GENERAL_NAME_PATTERN + "}")
	private String profileName;

	@Size(min = 0, max = 512)
	private String description;

	@NotNull
	private Long appId;

	@XmlTransient
	private List<UseCaseVO> useCases;

	@XmlTransient
	private Boolean selected;
	
	@XmlTransient
	private Boolean readOnly;

	/**
	 * Default Constructor
	 */
	public ProfileVO() {
		this.useCases = new ArrayList<>();
	}

	/**
	 * Id constructor
	 * 
	 * @param id
	 */
	public ProfileVO(Long id) {
		super();
		this.id = id;
		this.useCases = new ArrayList<>();
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param description
	 * @param profileName
	 * @param appId
	 */
	public ProfileVO(Long id, String description, String profileName, Long appId) {
		super();
		this.id = id;
		this.description = description;
		this.profileName = profileName;
		this.appId = appId;
		this.useCases = new ArrayList<>();
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
	 * @return the profileName
	 */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * @param profileName the profileName to set
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
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

	/**
	 * Getter for useCases
	 * 
	 * @return the useCases
	 */
	public List<UseCaseVO> getUseCases() {
		return useCases;
	}

	/**
	 * Setter for useCases
	 * 
	 * @param useCases the useCases to set
	 */
	public void setUseCases(List<UseCaseVO> useCases) {
		this.useCases = useCases;
	}

	/**
	 * @return selected
	 */
	public Boolean getSelected() {
		return selected;
	}

	/**
	 * @param selected
	 */
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * @return
	 */
	public Boolean getReadOnly() {
		return readOnly;
	}

	/**
	 * @param readOnly
	 */
	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
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
		ProfileVO other = (ProfileVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
package com.hallocasa.commons.vo;

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
 * Value object for information group
 * 
 * @author David Mantilla
 * @since 1.7
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InformationGroup", propOrder = { "id", "description",
	"informationGroupName", "appId" })
public class InformationGroupVO implements ValueObject {

	private static final long serialVersionUID = -2968948237293062074L;

	public static final String appId_ = "appId";
	public static final String informationGroupName_ = "informationGroupName";
	public static final String description_ = "description";

	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 0, max = 45)
	@Pattern(regexp = ValidationPatterns.GENERAL_NAME, message = "{"
		+ ValidationMessages.GENERAL_NAME_PATTERN + "}")
	private String informationGroupName;

	@Size(min = 0, max = 512)
	private String description;

	@NotNull
	private Long appId;
	
	@XmlTransient
	private Boolean selected;
	
	@XmlTransient
	private Boolean readOnly;

	/**
	 * Default Constructor
	 */
	public InformationGroupVO() {
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 */
	public InformationGroupVO(Long id) {
		super();
		this.id = id;
	}

	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */

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
	 * Getter for informationGroupName
	 * 
	 * @return the informationGroupName
	 */
	public String getInformationGroupName() {
		return informationGroupName;
	}

	/**
	 * Setter for informationGroupName
	 * 
	 * @param informationGroupName the informationGroupName to set
	 */
	public void setInformationGroupName(String informationGroupName) {
		this.informationGroupName = informationGroupName;
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
	 * Getter for selected
	 * @return
	 */
	public Boolean getSelected() {
		return selected;
	}

	/**
	 * Setter for selected
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
		InformationGroupVO other = (InformationGroupVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

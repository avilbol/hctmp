package com.mobiera.hallocasa.commons.vo;

import java.util.ArrayList;
import java.util.List;

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
 * Value object for partner information
 * 
 * @author David Mantilla
 * @since 1.7
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Partner", propOrder = { "id", "commercialName", "url",
	"logoFile", "creatorAccountId" })
public class PartnerVO implements ValueObject {

	/* static fields */
	private static final long serialVersionUID = 5564633982389823385L;
	public static final String whiteIps_ = "whiteIps";

	/* instance variables */

	private Long id;

	@Size(min = 0, max = 80)
	@NotEmpty
	@NotNull
	@Pattern(regexp = ValidationPatterns.GENERAL_NAME, message = "{"
		+ ValidationMessages.GENERAL_NAME_PATTERN + "}")
	private String commercialName;

	@Size(min = 0, max = 255)
	@Pattern(regexp = ValidationPatterns.URL_PATTERN, message = "{"
		+ ValidationMessages.URL_PATTERN + "}")
	private String url;

	private FileVO logoFile;

	private Long creatorAccountId;

	@XmlTransient
	private List<String> whiteIps;

	/* constructors */

	/**
	 * Default Constructor
	 */
	public PartnerVO() {
		this.whiteIps = new ArrayList<String>();
	}

	/**
	 * Create a new instance with same values than parentVO passed by parameter
	 * Constructor
	 * 
	 * @param partnerVO
	 */
	public PartnerVO(PartnerVO partnerVO) {
		this.commercialName = partnerVO.commercialName;
		this.creatorAccountId = partnerVO.creatorAccountId;
		this.id = partnerVO.id;
		if (partnerVO.logoFile != null) {
			this.logoFile = new FileVO(partnerVO.logoFile);
		}
		this.url = partnerVO.url;
		this.whiteIps = new ArrayList<String>();
		this.whiteIps.addAll(partnerVO.getWhiteIps());
	}

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
	 * Getter for commercialName
	 * 
	 * @return the commercialName
	 */
	public String getCommercialName() {
		return commercialName;
	}

	/**
	 * Setter for commercialName
	 * 
	 * @param commercialName the commercialName to set
	 */
	public void setCommercialName(String commercialName) {
		this.commercialName = commercialName;
	}

	/**
	 * Getter for URL
	 * 
	 * @return the URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Setter for URL
	 * 
	 * @param url the URL to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Getter for logoFile
	 * 
	 * @return the logoFile
	 */
	public FileVO getLogoFile() {
		return logoFile;
	}

	/**
	 * Setter for logoFile
	 * 
	 * @param logoFile the logoFile to set
	 */
	public void setLogoFile(FileVO logoFile) {
		this.logoFile = logoFile;
	}

	/**
	 * Getter for creatorAccountId
	 * 
	 * @return the creatorAccountId
	 */
	public Long getCreatorAccountId() {
		return creatorAccountId;
	}

	/**
	 * Setter for creatorAccountId
	 * 
	 * @param creatorAccountId the creatorAccountId to set
	 */
	public void setCreatorAccountId(Long creatorAccountId) {
		this.creatorAccountId = creatorAccountId;
	}

	/**
	 * Getter for whiteIps
	 * 
	 * @return the whiteIps
	 */
	public List<String> getWhiteIps() {
		return whiteIps;
	}

	/**
	 * Setter for whiteIps
	 * 
	 * @param whiteIps the whiteIps to set
	 */
	public void setWhiteIps(List<String> whiteIps) {
		this.whiteIps = whiteIps;
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
		PartnerVO other = (PartnerVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

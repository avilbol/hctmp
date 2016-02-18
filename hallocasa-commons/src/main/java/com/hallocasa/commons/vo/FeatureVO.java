package com.hallocasa.commons.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.hallocasa.commons.i18n.ValidationMessages;
import com.hallocasa.commons.validation.NotEmpty;
import com.hallocasa.commons.validation.ValidationPatterns;
import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Feature VO
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class FeatureVO implements ValueObject {

	private static final long serialVersionUID = -6786557752082302333L;

	/* static fields */
	public static final String appId_ = "appId";
	public static final String profiles_ = "profiles";
	public static final String useCases_ = "useCases";
	public static final String informationGroups_ = "informationGroups";

	/* instance variables */
	private Long id;

	@NotNull
	@NotEmpty
	@Pattern(regexp = ValidationPatterns.GENERAL_NAME, message = "{"
		+ ValidationMessages.GENERAL_NAME_PATTERN + "}")
	@Size(min = 0, max = 80)
	private String featureName;

	@Size(min = 0, max = 512)
	private String description;

	@NotNull
	private Long appId;

	private List<ProfileVO> profiles;

	private List<InformationGroupVO> informationGroups;

	/* constructors */

	/**
	 * Default Constructor
	 */
	public FeatureVO() {
		this.profiles = new ArrayList<>();
		this.informationGroups = new ArrayList<InformationGroupVO>();
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 */
	public FeatureVO(Long id) {
		super();
		this.id = id;
		this.profiles = new ArrayList<>();
		this.informationGroups = new ArrayList<InformationGroupVO>();
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param appId
	 * @param featureName
	 * @param profileVOs
	 */
	public FeatureVO(Long id, Long appId, String featureName,
		List<ProfileVO> profileVOs) {
		super();
		this.id = id;
		this.appId = appId;
		this.featureName = featureName;
		this.profiles = profileVOs;
		this.profiles = new ArrayList<>();
		this.informationGroups = new ArrayList<InformationGroupVO>();
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
	 * Getter for featureName
	 * 
	 * @return the featureName
	 */
	public String getFeatureName() {
		return featureName;
	}

	/**
	 * Setter for featureName
	 * 
	 * @param featureName the featureName to set
	 */
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	/**
	 * Getter for profileVOs
	 * 
	 * @return the profileVOs
	 */
	public List<ProfileVO> getProfiles() {
		return profiles;
	}

	/**
	 * Setter for profileVOs
	 * 
	 * @param profileVOs the profileVOs to set
	 */
	public void setProfiles(List<ProfileVO> profileVOs) {
		this.profiles = profileVOs;
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
	 * Getter for informationGroups
	 * 
	 * @return the informationGroups
	 */
	public List<InformationGroupVO> getInformationGroups() {
		return informationGroups;
	}

	/**
	 * Setter for informationGroups
	 * 
	 * @param informationGroups the informationGroups to set
	 */
	public void setInformationGroups(List<InformationGroupVO> informationGroups) {
		this.informationGroups = informationGroups;
	}

}

package com.mobiera.hallocasa.commons.vo.criteria;

/**
 * Query criteria for account retrieving
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class AccountQueryCriteria extends QueryCriteria {

	/* static fields */

	/* instance variables */
	@QueryFilterField(field = "firstName", operation = FilterOperation.LIKE)
	private String firstNameLike;

	@QueryFilterField(field = "lastName", operation = FilterOperation.LIKE)
	private String lastNameLike;

	@QueryFilterField(field = "email", operation = FilterOperation.EQUALS)
	private String emailEquals;

	@QueryFilterField(field = "email", operation = FilterOperation.LIKE)
	private String emailLike;

	@QueryFilterField(field = { "email", "lastName", "firstName", "mobile",
		"phone", "city", "state", "skype" },
		operation = FilterOperation.LIKE_LOWER_CASE)
	private String search;

	@QueryFilterField(field = "partner.id", operation = FilterOperation.EQUALS)
	private Long partnerIdEquals;

	@QueryFilterField(field = "activeFlag", operation = FilterOperation.EQUALS)
	private Boolean activeFlagEquals;

	/* constructors */

	/* Methods */

	/* Getters & Setters */

	/**
	 * Constructor
	 */
	public AccountQueryCriteria() {
		super();
		this.activeFlagEquals = true; // By default always only the active
	}

	/**
	 * Constructor
	 * 
	 * @param startIndex
	 * @param endIndex
	 */
	public AccountQueryCriteria(int startIndex, int endIndex) {
		super(startIndex, endIndex);
	}

	/**
	 * Getter for likeFirstName
	 * 
	 * @return the likeFirstName
	 */
	public String getFirstNameLike() {
		return firstNameLike;
	}

	/**
	 * Setter for likeFirstName
	 * 
	 * @param likeFirstName the likeFirstName to set
	 */
	public void setFirstNameLike(String likeFirstName) {
		this.firstNameLike = likeFirstName;
	}

	/**
	 * Getter for likeLastName
	 * 
	 * @return the likeLastName
	 */
	public String getLastNameLike() {
		return lastNameLike;
	}

	/**
	 * Setter for likeLastName
	 * 
	 * @param likeLastName the likeLastName to set
	 */
	public void setLastNameLike(String likeLastName) {
		this.lastNameLike = likeLastName;
	}

	/**
	 * Getter for exactEmail
	 * 
	 * @return the exactEmail
	 */
	public String getEmailEquals() {
		return emailEquals;
	}

	/**
	 * Setter for exactEmail
	 * 
	 * @param exactEmail the exactEmail to set
	 */
	public void setEmailEquals(String exactEmail) {
		this.emailEquals = exactEmail;
	}

	/**
	 * Getter for likeEmail
	 * 
	 * @return the likeEmail
	 */
	public String getEmailLike() {
		return emailLike;
	}

	/**
	 * Setter for likeEmail
	 * 
	 * @param likeEmail the likeEmail to set
	 */
	public void setEmailLike(String likeEmail) {
		this.emailLike = likeEmail;
	}

	/**
	 * Getter for search
	 * 
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * Setter for search
	 * 
	 * @param search the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * Getter for partnerIdEquals
	 * 
	 * @return the partnerIdEquals
	 */
	public Long getPartnerIdEquals() {
		return partnerIdEquals;
	}

	/**
	 * Setter for partnerIdEquals
	 * 
	 * @param partnerIdEquals the partnerIdEquals to set
	 */
	public void setPartnerIdEquals(Long partnerIdEquals) {
		this.partnerIdEquals = partnerIdEquals;
	}

	/**
	 * Getter for activeFlagEquals
	 * 
	 * @return the activeFlagEquals
	 */
	public Boolean getActiveFlagEquals() {
		return activeFlagEquals;
	}

	/**
	 * Setter for activeFlagEquals
	 * 
	 * @param activeFlagEquals the activeFlagEquals to set
	 */
	public void setActiveFlagEquals(Boolean activeFlagEquals) {
		this.activeFlagEquals = activeFlagEquals;
	}

}

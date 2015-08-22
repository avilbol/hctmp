package com.hallocasa.commons.vo.criteria;

/**
 * Query criteria for account app retrieving
 * 
 * @author German Quinones
 * @since 1.7
 */
public class AccountAppQueryCriteria extends QueryCriteria {

	/* static fields */

	/* instance variables */
	@QueryFilterField(field = "account.firstName", operation = FilterOperation.LIKE)
	private String firstNameLike;

	@QueryFilterField(field = "account.lastName", operation = FilterOperation.LIKE)
	private String lastNameLike;

	@QueryFilterField(field = "account.email", operation = FilterOperation.EQUALS)
	private String emailEquals;

	@QueryFilterField(field = "account.email", operation = FilterOperation.LIKE)
	private String emailLike;
	
	@QueryFilterField(field = "id.accountId", operation = FilterOperation.EQUALS)
	private Long accountId;
	
	@QueryFilterField(field = "id.partnerAppPK.appId", operation = FilterOperation.EQUALS)
	private Long appIdEquals;
	
	@QueryFilterField(field = "id.partnerAppPK.partnerId", operation = FilterOperation.EQUALS)
	private Long partnerIdEquals;

	@QueryFilterField(field = { "account.email", "account.lastName", "account.firstName"},
		operation = FilterOperation.LIKE_LOWER_CASE)
	private String search;

	/* constructors */

	/* Methods */

	/* Getters & Setters */

	/**
	 * Constructor
	 */
	public AccountAppQueryCriteria() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param startIndex
	 * @param endIndex
	 */
	public AccountAppQueryCriteria(int startIndex, int endIndex) {
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
	 * Getter for appIdEquals
	 * @return
	 */
	public Long getAppIdEquals() {
		return appIdEquals;
	}

	/**
	 * Setter for appIdEquals
	 * 
	 * @param appIdEquals
	 */
	public void setAppIdEquals(Long appIdEquals) {
		this.appIdEquals = appIdEquals;
	}

	/**
	 * Getter for partnerIdEquals
	 * 
	 * @return
	 */
	public Long getPartnerIdEquals() {
		return partnerIdEquals;
	}

	/**
	 * Setter for partnerIdEquals
	 * 
	 * @param partnerIdEquals
	 */
	public void setPartnerIdEquals(Long partnerIdEquals) {
		this.partnerIdEquals = partnerIdEquals;
	}

	/**
	 * Getter account Id
	 * @return
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * Setter account Id
	 * @param accountId
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}
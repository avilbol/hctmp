package com.hallocasa.commons.vo.criteria;

/**
 * Query criteria for account app retrieving
 * 
 * @author German Quinones
 * @since 1.7
 */
public class PartnerAppQueryCriteria extends QueryCriteria {

	/* static fields */

	/* instance variables */
	
	@QueryFilterField(field = "id.appId", operation = FilterOperation.EQUALS)
	private Long appId;
	
	@QueryFilterField(field = "id.partnerId", operation = FilterOperation.EQUALS)
	private Long partnerId;

	/* constructors */

	/* Methods */

	/* Getters & Setters */

	/**
	 * Constructor
	 */
	public PartnerAppQueryCriteria() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param startIndex
	 * @param endIndex
	 */
	public PartnerAppQueryCriteria(int startIndex, int endIndex) {
		super(startIndex, endIndex);
	}

	/**
	 * Getter for appId
	 * @return
	 */
	public Long getAppId() {
		return appId;
	}

	/**
	 * Setter for appId
	 * @param appId
	 */
	public void setAppId(Long appId) {
		this.appId = appId;
	}

	/**
	 * Getter for partnerId
	 * @return
	 */
	public Long getPartnerId() {
		return partnerId;
	}

	/**
	 * Setter for partnerId
	 * @param partnerId
	 */
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}
}

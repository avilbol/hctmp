package com.mobiera.hallocasa.commons.vo.criteria;

/**
 * Operation query criteria
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class OperationQueryCriteria extends QueryCriteria {

	/* static fields */

	/* instance variables */
	@QueryFilterField(field = "id", operation = FilterOperation.EQUALS)
	private Long idEquals;

	@QueryFilterField(field = "partner.id", operation = FilterOperation.EQUALS)
	private Long partnerIdEquals;
	
	@QueryFilterField(field = "activeFlag", operation = FilterOperation.EQUALS)
	private Boolean activeFlagEquals;

	/* constructors */

	/**
	 * Constructor
	 */
	public OperationQueryCriteria() {
		activeFlagEquals = true;
	}

	/* Methods */

	/* Getters & Setters */
	/**
	 * Getter for idEquals
	 * 
	 * @return the idEquals
	 */
	public Long getIdEquals() {
		return idEquals;
	}

	/**
	 * Setter for idEquals
	 * 
	 * @param idEquals the idEquals to set
	 */
	public void setIdEquals(Long idEquals) {
		this.idEquals = idEquals;
	}

	/**
	 * Getter for partnerId
	 * 
	 * @return the partnerId
	 */
	public Long getPartnerIdEquals() {
		return partnerIdEquals;
	}

	/**
	 * Setter for partnerId
	 * 
	 * @param partnerId the partnerId to set
	 */
	public void setPartnerIdEquals(Long partnerId) {
		this.partnerIdEquals = partnerId;
	}

	/**
	 * Getter for activeFlagEquals
	 * @return the activeFlagEquals
	 */
	public Boolean getActiveFlagEquals() {
		return activeFlagEquals;
	}

	/**
	 * Setter for activeFlagEquals
	 * @param activeFlagEquals the activeFlagEquals to set
	 */
	public void setActiveFlagEquals(Boolean activeFlagEquals) {
		this.activeFlagEquals = activeFlagEquals;
	}
}

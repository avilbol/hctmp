package com.hallocasa.commons.vo.criteria;

/**
 * Partner query criteria
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class PartnerQueryCriteria extends QueryCriteria{

	/* static fields */

	/* instance variables */
	@QueryFilterField(field = "id", operation = FilterOperation.EQUALS)
	private Long idEquals;
	
	@QueryFilterField(field = "creatorAccount", operation = FilterOperation.EQUALS)
	private Long accountIdEquals;

	/* constructors */

	/**
	 * Constructor
	 */
	public PartnerQueryCriteria() {
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
	 * Getter for accountIdEquals
	 * @return accountIdEquals
	 */
	public Long getAccountIdEquals() {
		return accountIdEquals;
	}

	/**
	 * Setter for accoundIdEquals
	 * @param accountIdEquals
	 */
	public void setAccountIdEquals(Long accountIdEquals) {
		this.accountIdEquals = accountIdEquals;
	}
}

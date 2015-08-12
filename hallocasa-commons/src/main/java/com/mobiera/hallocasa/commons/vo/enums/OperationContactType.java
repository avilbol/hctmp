package com.mobiera.hallocasa.commons.vo.enums;


/**
 * The persistent class for the operation_contact_type database table.
 * 
 */
public enum OperationContactType {

	/**
	 * Billing account contact type
	 */
	BILLING(1L, "Billing"),
	/**
	 * Accounting account contact type
	 */
	MANAGEMENT(2L, "Management"),
	/**
	 * IT account contact type
	 */
	IT(3L, "IT");

	private final Long id;
	private final String description;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param description
	 */
	private OperationContactType(Long id, String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Getter for id
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Getter for description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
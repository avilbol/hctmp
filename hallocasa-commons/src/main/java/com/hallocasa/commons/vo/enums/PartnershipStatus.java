package com.hallocasa.commons.vo.enums;

/**
 * The persistent class for the operation_contact_type database table.
 * 
 */
public enum PartnershipStatus {

	PENDING(1L, "Pending"),
	ENABLED(2L, "Enabled"),
	CANCELED(3L, "Canceled");

	private final Long id;
	private final String description;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param description
	 */
	private PartnershipStatus(Long id, String description) {
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
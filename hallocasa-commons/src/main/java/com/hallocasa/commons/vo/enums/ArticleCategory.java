package com.hallocasa.commons.vo.enums;

/**
 * The persistent class for the operation_contact_type database table.
 * 
 */
public enum ArticleCategory {

	TERMS(1L, "Terms"),
	PRIVACY(2L, "Privacy"),
	ABOUT(3L, "About"),
	MOBIERA(4L, "Mobiera");

	private final Long id;
	private final String description;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param description
	 */
	private ArticleCategory(Long id, String description) {
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
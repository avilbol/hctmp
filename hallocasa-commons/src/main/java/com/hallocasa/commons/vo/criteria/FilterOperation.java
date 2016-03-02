package com.hallocasa.commons.vo.criteria;

/**
 * This enumeration lists the possible operations of a filter for building the
 * sentence
 * 
 * @author David Mantilla
 * @since 1.7
 */
public enum FilterOperation {

	/* static fields */
	EQUALS("="),
	GREATER_THAN(">"),
	GREATER_OR_EQUALS_THAN(">="),
	LESS_THAN("<"),
	LESS_OR_EQUALS_THAN("<="),
	LIKE("LIKE"),
	LIKE_LOWER_CASE("LIKE");

	/* instance variables */
	private final String operatorSymbol;

	/**
	 * Constructor
	 * 
	 * @param operatorSymbol
	 */
	private FilterOperation(String operatorSymbol) {
		this.operatorSymbol = operatorSymbol;
	}

	/* constructors */

	/* Methods */

	/* Getters & Setters */
	/**
	 * Getter for operatorSymbol
	 * 
	 * @return the operatorSymbol
	 */
	public String getOperatorSymbol() {
		return operatorSymbol;
	}

}

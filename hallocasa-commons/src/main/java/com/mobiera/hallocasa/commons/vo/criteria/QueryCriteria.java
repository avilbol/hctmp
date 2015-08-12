package com.mobiera.hallocasa.commons.vo.criteria;

/**
 * General Query criteria
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class QueryCriteria {

	/* static fields */

	/* instance variables */
	private Integer startIndex;
	private Integer endIndex;

	/* constructors */
	/**
	 * Constructor
	 */
	public QueryCriteria() {
	}

	/**
	 * Constructor
	 * 
	 * @param startIndex
	 * @param endIndex
	 */
	public QueryCriteria(Integer startIndex, Integer endIndex) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	/* Methods */

	/* Getters & Setters */
	/**
	 * Getter for startIndex
	 * 
	 * @return the startIndex
	 */
	public Integer getStartIndex() {
		return startIndex;
	}

	/**
	 * Setter for startIndex
	 * 
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * Getter for endIndex
	 * 
	 * @return the endIndex
	 */
	public Integer getEndIndex() {
		return endIndex;
	}

	/**
	 * Setter for endIndex
	 * 
	 * @param endIndex the endIndex to set
	 */
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

}

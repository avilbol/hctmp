package com.hallocasa.commons.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This value object represents the result of query service
 * 
 * @author David Mantilla
 *
 * @param <T> The bean type
 * @since 1.7
 */
public class QueryResult<T> implements Serializable {

	/* static fields */
	private static final long serialVersionUID = 8454677643060877665L;

	/* instance variables */
	private int startIndex;
	private int endIndex;
	private int totalCount;
	private List<T> result;

	/* constructors */

	/**
	 * Constructor
	 * 
	 * @param startIndex
	 * @param endIndex
	 * @param totalCount
	 * @param result
	 */
	public QueryResult(Integer startIndex, Integer endIndex, int totalCount,
		List<T> result) {
		super();
		this.startIndex = startIndex == null ? 0 : startIndex;
		this.endIndex = endIndex == null ? totalCount : this.endIndex;
		this.totalCount = totalCount;
		this.result = result;

		if (this.endIndex > this.totalCount) {
			this.endIndex = this.totalCount;
		}
	}

	/**
	 * Constructor
	 * 
	 * @param startIndex
	 * @param endIndex
	 * @param totalCount
	 */
	public QueryResult(Integer startIndex, Integer endIndex, int totalCount) {
		super();
		this.startIndex = startIndex == null ? 0 : startIndex;
		this.endIndex = endIndex == null ? totalCount : this.endIndex;
		this.totalCount = totalCount;

		if (this.endIndex > this.totalCount) {
			this.endIndex = this.totalCount;
		}
		this.result = new ArrayList<T>();
	}

	/* Methods */

	/* Getters & Setters */

	/**
	 * Getter for startIndex
	 * 
	 * @return the startIndex
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * Setter for startIndex
	 * 
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * Getter for totalCount
	 * 
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * Setter for totalCount
	 * 
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * Getter for result
	 * 
	 * @return the result
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * Setter for result
	 * 
	 * @param result the result to set
	 */
	public void setResult(List<T> result) {
		this.result = result;
	}

	/**
	 * Getter for endIndex
	 * 
	 * @return the endIndex
	 */
	public int getEndIndex() {
		return endIndex;
	}

	/**
	 * Setter for endIndex
	 * 
	 * @param endIndex the endIndex to set
	 */
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}

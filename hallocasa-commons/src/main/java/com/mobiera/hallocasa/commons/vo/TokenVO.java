package com.mobiera.hallocasa.commons.vo;

import java.sql.Time;

import com.mobiera.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value Object of the token
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class TokenVO implements ValueObject {

	/* static fields */

	/* instance variables */
	private Long id;
	private String accessToken;
	private Long accountId;
	private String appToUrl;
	private Time expirationDate;
	private Time requestDate;
	private Long appFromId;
	private Long appToId;

	/* constructors */

	/**
	 * Default Constructor
	 */
	public TokenVO() {
	}

	/* Methods */

	/* Getters & Setters */
	/**
	 * Getter for id
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for id
	 * 
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for accountId
	 * 
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * Setter for accountId
	 * 
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * Getter for appToUrl
	 * 
	 * @return the appToUrl
	 */
	public String getAppToUrl() {
		return appToUrl;
	}

	/**
	 * Setter for appToUrl
	 * 
	 * @param appToUrl the appToUrl to set
	 */
	public void setAppToUrl(String appToUrl) {
		this.appToUrl = appToUrl;
	}

	/**
	 * Getter for expirationDate
	 * 
	 * @return the expirationDate
	 */
	public Time getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Setter for expirationDate
	 * 
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Time expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Getter for requestDate
	 * 
	 * @return the requestDate
	 */
	public Time getRequestDate() {
		return requestDate;
	}

	/**
	 * Setter for requestDate
	 * 
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(Time requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * Getter for appFromId
	 * 
	 * @return the appFromId
	 */
	public Long getAppFromId() {
		return appFromId;
	}

	/**
	 * Setter for appFromId
	 * 
	 * @param appFromId the appFromId to set
	 */
	public void setAppFromId(Long appFromId) {
		this.appFromId = appFromId;
	}

	/**
	 * Getter for toFromId
	 * 
	 * @return the toFromId
	 */
	public Long getAppToId() {
		return appToId;
	}

	/**
	 * Setter for toFromId
	 * 
	 * @param toFromId the toFromId to set
	 */
	public void setAppToId(Long toFromId) {
		this.appToId = toFromId;
	}

	/**
	 * Getter for accessToken
	 * 
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Setter for accessToken
	 * 
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}

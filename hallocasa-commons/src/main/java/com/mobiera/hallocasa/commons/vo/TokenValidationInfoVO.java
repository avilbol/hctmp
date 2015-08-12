package com.mobiera.hallocasa.commons.vo;

import com.mobiera.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Token validation info value object
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class TokenValidationInfoVO implements ValueObject {
	/* static fields */

	/* instance variables */
	private AuthInfoVO authInfo;
	private AppAccessInfoVO appAccessInfo;
	private TokenVO token;

	/* constructors */
	/**
	 * Default constructor Constructor
	 */
	public TokenValidationInfoVO() {
	}

	/* Methods */

	/* Getters & Setters */
	/**
	 * Getter for authInfo
	 * 
	 * @return the authInfo
	 */
	public AuthInfoVO getAuthInfo() {
		return authInfo;
	}

	/**
	 * Setter for authInfo
	 * 
	 * @param authInfo the authInfo to set
	 */
	public void setAuthInfo(AuthInfoVO authInfo) {
		this.authInfo = authInfo;
	}

	/**
	 * Getter for appAccessInfo
	 * 
	 * @return the appAccessInfo
	 */
	public AppAccessInfoVO getAppAccessInfo() {
		return appAccessInfo;
	}

	/**
	 * Setter for appAccessInfo
	 * 
	 * @param appAccessInfo the appAccessInfo to set
	 */
	public void setAppAccessInfo(AppAccessInfoVO appAccessInfo) {
		this.appAccessInfo = appAccessInfo;
	}

	/**
	 * Getter for token
	 * 
	 * @return the token
	 */
	public TokenVO getToken() {
		return token;
	}

	/**
	 * Setter for token
	 * 
	 * @param token the token to set
	 */
	public void setToken(TokenVO token) {
		this.token = token;
	}

}

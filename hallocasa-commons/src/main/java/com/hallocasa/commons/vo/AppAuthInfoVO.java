package com.hallocasa.commons.vo;

import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value object with information of the authentication and application info
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class AppAuthInfoVO implements ValueObject {
	/* static fields */

	/* instance variables */

	private AuthInfoVO authInfo;
	private AppAccessInfoVO appAccessInfo;

	/* constructors */
	/**
	 * Default constructor
	 */
	public AppAuthInfoVO() {
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

}

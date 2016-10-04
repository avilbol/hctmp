package com.hallocasa.vo.security;

import java.util.Date;

/**
 * VO that represents authorization code as result from authorization process
 */
public class SecurityToken {
	
	private String tokenValue;
	private Date registered;
	private int expiresIn;

	public String getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}

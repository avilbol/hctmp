package com.hallocasa.vo.security;

import java.io.Serializable;
import java.util.Date;

import com.hallocasa.vo.i.ValueObject;

/**
 * VO that represents authorization code as result from authorization process
 */
public class SecurityToken implements ValueObject, Serializable {
	
	private static final long serialVersionUID = 2295064447379766811L;
	private String tokenValue;
	private Date registered;
	private long expiresIn;

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

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}
}

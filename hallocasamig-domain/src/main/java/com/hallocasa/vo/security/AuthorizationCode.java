package com.hallocasa.vo.security;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * VO that represents authorization code as result from authorization process
 */
public class AuthorizationCode implements Serializable, ValueObject {

	private static final long serialVersionUID = 8407535833766005281L;
	private int id;
	private String clientId;
	private String authCode;
	
	public AuthorizationCode() {
		super();
	}

	public AuthorizationCode(String clientId, String authCode) {
		super();
		this.clientId = clientId;
		this.authCode = authCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
}

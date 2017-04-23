package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class PasswordRecoveryRequest implements ValueObject, Serializable{

	private static final long serialVersionUID = 2627096342272051404L;
	
	private String newPassword;
	
	private PasswordRecoveryToken passwordRecoveryToken;
	
	public PasswordRecoveryRequest() {
		super();
	}
	
	public PasswordRecoveryRequest(PasswordRecoveryToken passwordRecoveryToken) {
		super();
		this.passwordRecoveryToken = passwordRecoveryToken;
	}

	public PasswordRecoveryRequest(String newPassword, PasswordRecoveryToken passwordRecoveryToken) {
		super();
		this.newPassword = newPassword;
		this.passwordRecoveryToken = passwordRecoveryToken;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public PasswordRecoveryToken getPasswordRecoveryToken() {
		return passwordRecoveryToken;
	}

	public void setPasswordRecoveryToken(PasswordRecoveryToken paswordRecoveryToken) {
		this.passwordRecoveryToken = paswordRecoveryToken;
	}
}

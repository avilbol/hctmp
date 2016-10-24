package com.hallocasa.vo.security;

import java.io.Serializable;

import com.hallocasa.vo.User;
import com.hallocasa.vo.i.ValueObject;

/**
 * Value Object for Authentication information
 */
public class AuthInfo implements ValueObject, Serializable {

    private static final long serialVersionUID = -1370266485971582595L;

    /* static fields */
    private User user;
    private SecurityToken securityToken;

    /* instance variables */

    /* constructors */
    public AuthInfo(User user, SecurityToken securityToken) {
        this.user = user;
        this.securityToken = securityToken;
    }
    
    public AuthInfo() {
		super();
	}

	/* Methods */

    /* Getters & Setters */
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SecurityToken getSecurityToken() {
		return securityToken;
	}

	public void setSecurityToken(SecurityToken securityToken) {
		this.securityToken = securityToken;
	}
}

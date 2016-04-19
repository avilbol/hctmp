package com.hallocasa.commons.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.hallocasa.commons.i18n.ValidationMessages;
import com.hallocasa.commons.validation.NotEmpty;
import com.hallocasa.commons.validation.ValidationPatterns;
import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * This class represents the login information value object
 *
 * @author David Mantilla
 *
 * @since 1.7
 */
public class CredentialVO implements ValueObject {
    /* static fields */

    private static final long serialVersionUID = -1439496983785050222L;

    /* instance variables */
    @Pattern(regexp = ValidationPatterns.EMAIL_PATTERN, message = "{"
            + ValidationMessages.EMAIL_PATTERN + "}")
    @NotNull
    @NotEmpty
    @Size(min = 0, max = 80)
    private String email;
    @NotNull
    @NotEmpty
    @Size(min = 0, max = 80)
    private String password;

    /* constructors */
    /**
     * Default Constructor
     */
    public CredentialVO() {
    }

    /**
     * Constructor
     *
     * @param email
     * @param password
     */
    public CredentialVO(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor creating a copy from another credentialVO object
     *
     * @param credentialVO
     */
    public CredentialVO(CredentialVO credentialVO) {
        super();
        this.email = credentialVO.email;
        this.password = credentialVO.password;
    }

    /* Methods */

    /* Getters & Setters */
    /**
     * Getter for email
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for password
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result
                + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CredentialVO other = (CredentialVO) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        return true;
    }

}

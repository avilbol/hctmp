/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.commons.vo;

import com.hallocasa.commons.i18n.ValidationMessages;
import com.hallocasa.commons.validation.NotEmpty;
import com.hallocasa.commons.validation.ValidationPatterns;
import com.hallocasa.commons.vo.interfaces.ValueObject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Value object for represents the data transfer object for register user
 * service
 *
 * @author david
 */
public class RegisterUserVO implements ValueObject {

    @Pattern(regexp = ValidationPatterns.EMAIL_PATTERN, message = "{"
            + ValidationMessages.EMAIL_PATTERN + "}")
    @NotNull
    @NotEmpty
    @Size(min = 0, max = 80)
    private String email;
    @NotNull
    @NotEmpty
    @Size(min = 0, max = 80)
    @Pattern(regexp = ValidationPatterns.PASSWORD_PATTERN, message = "{"
            + ValidationMessages.PASSWORD_PATTERN + "}")
    private String password;

    /**
     * Default constructor
     */
    public RegisterUserVO() {
    }

    /**
     * Constructor with object values
     *
     * @param email
     * @param password
     */
    public RegisterUserVO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}

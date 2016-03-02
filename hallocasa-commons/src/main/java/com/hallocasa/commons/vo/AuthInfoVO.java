package com.hallocasa.commons.vo;

import java.util.ArrayList;
import java.util.List;

import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value Object for Authentication information
 *
 * @author David Mantilla
 * @since 1.7
 */
public class AuthInfoVO implements ValueObject {

    private static final long serialVersionUID = -1370266485971582595L;

    /* static fields */
    private UserVO user;
    private List<UseCaseVO> useCases;
    private List<ProfileVO> profiles;

    /* instance variables */

    /* constructors */
    /**
     * Default Constructor
     */
    public AuthInfoVO() {
        this.useCases = new ArrayList<>();
        this.profiles = new ArrayList<>();
    }

    /* Methods */

    /* Getters & Setters */
    /**
     * Getter for account
     *
     * @return the account
     */
    public UserVO getUser() {
        return user;
    }

    /**
     * Setter for account
     *
     * @param user the account to set
     */
    public void setUser(UserVO user) {
        this.user = user;
    }

    /* Methods */

    /* Getters & Setters */
    /**
     * Getter for useCases
     *
     * @return the useCases
     */
    public List<UseCaseVO> getUseCases() {
        return useCases;
    }

    /**
     * Setter for useCases
     *
     * @param useCases the useCases to set
     */
    public void setUseCases(List<UseCaseVO> useCases) {
        this.useCases = useCases;
    }

    /**
     * Getter for profiles
     *
     * @return
     */
    public List<ProfileVO> getProfiles() {
        return profiles;
    }

    /**
     * Setter for profiles
     *
     * @param profiles
     */
    public void setProfiles(List<ProfileVO> profiles) {
        this.profiles = profiles;
    }

}

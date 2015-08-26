package com.hallocasa.commons.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.hallocasa.commons.vo.interfaces.ValueObject;

/**
 * Value Object with access information for a given application
 *
 * @author David Mantilla
 * @since 1.7
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppAccessInfo", propOrder = {"useCases", "operations",
    "supervisedOperations", "profiles", "informationGroups"})
public class AppAccessInfoVO implements ValueObject {

    /* static fields */

    /* instance variables */
    private List<UseCaseVO> useCases;
    private List<ProfileVO> profiles;

    /* constructors */
    /**
     * Default Constructor
     */
    public AppAccessInfoVO() {
        useCases = new ArrayList<>();
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
     * @return 
     */
    public List<ProfileVO> getProfiles() {
        return profiles;
    }

    /**
     * Setter for profiles
     * @param profiles 
     */
    public void setProfiles(List<ProfileVO> profiles) {
        this.profiles = profiles;
    }

}

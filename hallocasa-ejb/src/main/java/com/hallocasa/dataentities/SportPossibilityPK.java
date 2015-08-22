/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.dataentities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David Mantilla
 */
@Embeddable
public class SportPossibilityPK implements Serializable {

	private static final long serialVersionUID = -3264137388310351902L;
	@Basic(optional = false)
    @NotNull
    @Column(name = "property_environtment_id")
    private int propertyEnvirontmentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sport_id")
    private int sportId;

    public SportPossibilityPK() {
    }

    public SportPossibilityPK(int propertyEnvirontmentId, int sportId) {
        this.propertyEnvirontmentId = propertyEnvirontmentId;
        this.sportId = sportId;
    }

    public int getPropertyEnvirontmentId() {
        return propertyEnvirontmentId;
    }

    public void setPropertyEnvirontmentId(int propertyEnvirontmentId) {
        this.propertyEnvirontmentId = propertyEnvirontmentId;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) propertyEnvirontmentId;
        hash += (int) sportId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SportPossibilityPK)) {
            return false;
        }
        SportPossibilityPK other = (SportPossibilityPK) object;
        if (this.propertyEnvirontmentId != other.propertyEnvirontmentId) {
            return false;
        }
        if (this.sportId != other.sportId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.SportPossibilityPK[ propertyEnvirontmentId=" + propertyEnvirontmentId + ", sportId=" + sportId + " ]";
    }

     /**************************************************************************
     * Constanst
     ***************************************************************************
     */

    /***************************************************************************
     * Instance variable
     ***************************************************************************
     */

    /***************************************************************************
     * Constructor
     ***************************************************************************
     */

    /***************************************************************************
     * Methods
     **************************************************************************
     */

    /**
     * *************************************************************************
     * Getters y Setters
     * *************************************************************************
     */

}

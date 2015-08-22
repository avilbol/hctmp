/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "sport_possibility")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SportPossibility.findAll", query = "SELECT s FROM SportPossibility s"),
    @NamedQuery(name = "SportPossibility.findByPropertyEnvirontmentId", query = "SELECT s FROM SportPossibility s WHERE s.sportPossibilityPK.propertyEnvirontmentId = :propertyEnvirontmentId"),
    @NamedQuery(name = "SportPossibility.findBySportId", query = "SELECT s FROM SportPossibility s WHERE s.sportPossibilityPK.sportId = :sportId"),
    @NamedQuery(name = "SportPossibility.findByInsidePrivateAreaFlag", query = "SELECT s FROM SportPossibility s WHERE s.insidePrivateAreaFlag = :insidePrivateAreaFlag")})
public class SportPossibility implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SportPossibilityPK sportPossibilityPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inside_private_area_flag")
    private boolean insidePrivateAreaFlag;
    @JoinColumn(name = "sport_id", referencedColumnName = "sport_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sport sport;
    @JoinColumn(name = "property_environtment_id", referencedColumnName = "property_environtment_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PropertyEnvirontment propertyEnvirontment;

    public SportPossibility() {
    }

    public SportPossibility(SportPossibilityPK sportPossibilityPK) {
        this.sportPossibilityPK = sportPossibilityPK;
    }

    public SportPossibility(SportPossibilityPK sportPossibilityPK, boolean insidePrivateAreaFlag) {
        this.sportPossibilityPK = sportPossibilityPK;
        this.insidePrivateAreaFlag = insidePrivateAreaFlag;
    }

    public SportPossibility(int propertyEnvirontmentId, int sportId) {
        this.sportPossibilityPK = new SportPossibilityPK(propertyEnvirontmentId, sportId);
    }

    public SportPossibilityPK getSportPossibilityPK() {
        return sportPossibilityPK;
    }

    public void setSportPossibilityPK(SportPossibilityPK sportPossibilityPK) {
        this.sportPossibilityPK = sportPossibilityPK;
    }

    public boolean getInsidePrivateAreaFlag() {
        return insidePrivateAreaFlag;
    }

    public void setInsidePrivateAreaFlag(boolean insidePrivateAreaFlag) {
        this.insidePrivateAreaFlag = insidePrivateAreaFlag;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public PropertyEnvirontment getPropertyEnvirontment() {
        return propertyEnvirontment;
    }

    public void setPropertyEnvirontment(PropertyEnvirontment propertyEnvirontment) {
        this.propertyEnvirontment = propertyEnvirontment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sportPossibilityPK != null ? sportPossibilityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof SportPossibility)) {
            return false;
        }
        SportPossibility other = (SportPossibility) object;
        if ((this.sportPossibilityPK == null && other.sportPossibilityPK != null) || (this.sportPossibilityPK != null && !this.sportPossibilityPK.equals(other.sportPossibilityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.SportPossibility[ sportPossibilityPK=" + sportPossibilityPK + " ]";
    }

    /**
     * ************************************************************************
     * Constanst
     * **************************************************************************
     */
    /**
     * *************************************************************************
     * Instance variable
     * **************************************************************************
     */
    /**
     * *************************************************************************
     * Constructor
     * **************************************************************************
     */
    /**
     * *************************************************************************
     * Methods
     * *************************************************************************
     */
    /**
     * *************************************************************************
     * Getters y Setters
     * *************************************************************************
     */
}

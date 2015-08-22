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
@Table(name = "property_access")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PropertyAccess.findAll", query = "SELECT p FROM PropertyAccess p"),
    @NamedQuery(name = "PropertyAccess.findByPropertyId", query = "SELECT p FROM PropertyAccess p WHERE p.propertyAccessPK.propertyId = :propertyId"),
    @NamedQuery(name = "PropertyAccess.findByAccessTypeId", query = "SELECT p FROM PropertyAccess p WHERE p.propertyAccessPK.accessTypeId = :accessTypeId"),
    @NamedQuery(name = "PropertyAccess.findByDistance", query = "SELECT p FROM PropertyAccess p WHERE p.distance = :distance")})
public class PropertyAccess implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PropertyAccessPK propertyAccessPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "distance")
    private double distance;
    @JoinColumn(name = "property_id", referencedColumnName = "property_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Property property;
    @JoinColumn(name = "access_type_id", referencedColumnName = "access_type_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AccessType accessType;

    public PropertyAccess() {
    }

    public PropertyAccess(PropertyAccessPK propertyAccessPK) {
        this.propertyAccessPK = propertyAccessPK;
    }

    public PropertyAccess(PropertyAccessPK propertyAccessPK, double distance) {
        this.propertyAccessPK = propertyAccessPK;
        this.distance = distance;
    }

    public PropertyAccess(int propertyId, int accessTypeId) {
        this.propertyAccessPK = new PropertyAccessPK(propertyId, accessTypeId);
    }

    public PropertyAccessPK getPropertyAccessPK() {
        return propertyAccessPK;
    }

    public void setPropertyAccessPK(PropertyAccessPK propertyAccessPK) {
        this.propertyAccessPK = propertyAccessPK;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (propertyAccessPK != null ? propertyAccessPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof PropertyAccess)) {
            return false;
        }
        PropertyAccess other = (PropertyAccess) object;
        if ((this.propertyAccessPK == null && other.propertyAccessPK != null) || (this.propertyAccessPK != null && !this.propertyAccessPK.equals(other.propertyAccessPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.PropertyAccess[ propertyAccessPK=" + propertyAccessPK + " ]";
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

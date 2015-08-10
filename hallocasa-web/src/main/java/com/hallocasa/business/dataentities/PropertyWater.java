/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.business.dataentities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "property_water")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PropertyWater.findAll", query = "SELECT p FROM PropertyWater p"),
    @NamedQuery(name = "PropertyWater.findByPropertyId", query = "SELECT p FROM PropertyWater p WHERE p.propertyWaterPK.propertyId = :propertyId"),
    @NamedQuery(name = "PropertyWater.findByWaterTypeId", query = "SELECT p FROM PropertyWater p WHERE p.propertyWaterPK.waterTypeId = :waterTypeId")})
public class PropertyWater implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PropertyWaterPK propertyWaterPK;
    @JoinColumn(name = "water_time_availability_id", referencedColumnName = "water_time_availability_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WaterTimeAvailability waterTimeAvailability;
    @JoinColumn(name = "water_type_id", referencedColumnName = "water_type_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WaterType waterType;
    @JoinColumn(name = "property_id", referencedColumnName = "property_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Property property;

    public PropertyWater() {
    }

    public PropertyWater(PropertyWaterPK propertyWaterPK) {
        this.propertyWaterPK = propertyWaterPK;
    }

    public PropertyWater(int propertyId, int waterTypeId) {
        this.propertyWaterPK = new PropertyWaterPK(propertyId, waterTypeId);
    }

    public PropertyWaterPK getPropertyWaterPK() {
        return propertyWaterPK;
    }

    public void setPropertyWaterPK(PropertyWaterPK propertyWaterPK) {
        this.propertyWaterPK = propertyWaterPK;
    }

    public WaterTimeAvailability getWaterTimeAvailability() {
        return waterTimeAvailability;
    }

    public void setWaterTimeAvailability(WaterTimeAvailability waterTimeAvailability) {
        this.waterTimeAvailability = waterTimeAvailability;
    }

    public WaterType getWaterType() {
        return waterType;
    }

    public void setWaterType(WaterType waterType) {
        this.waterType = waterType;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (propertyWaterPK != null ? propertyWaterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof PropertyWater)) {
            return false;
        }
        PropertyWater other = (PropertyWater) object;
        if ((this.propertyWaterPK == null && other.propertyWaterPK != null) || (this.propertyWaterPK != null && !this.propertyWaterPK.equals(other.propertyWaterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.PropertyWater[ propertyWaterPK=" + propertyWaterPK + " ]";
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

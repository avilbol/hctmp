/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.business.dataentities;

import com.hallocasa.business.dataentities.interfaces.TypeInterface;
import com.hallocasa.commons.Language;

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
@Table(name = "property_topography")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PropertyTopography.findAll", query = "SELECT p FROM PropertyTopography p"),
    @NamedQuery(name = "PropertyTopography.findByPropertyId", query = "SELECT p FROM PropertyTopography p WHERE p.propertyTopographyPK.propertyId = :propertyId"),
    @NamedQuery(name = "PropertyTopography.findByTopographyId", query = "SELECT p FROM PropertyTopography p WHERE p.propertyTopographyPK.topographyId = :topographyId"),
    @NamedQuery(name = "PropertyTopography.findByPercent", query = "SELECT p FROM PropertyTopography p WHERE p.percent = :percent")})
public class PropertyTopography implements Serializable, TypeInterface {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PropertyTopographyPK propertyTopographyPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "percent")
    private double percent;
    @JoinColumn(name = "topography_id", referencedColumnName = "topography_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Topography topography;
    @JoinColumn(name = "property_id", referencedColumnName = "property_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Property property;

    public PropertyTopography() {
    }

    public PropertyTopography(PropertyTopographyPK propertyTopographyPK) {
        this.propertyTopographyPK = propertyTopographyPK;
    }

    public PropertyTopography(PropertyTopographyPK propertyTopographyPK, double percent) {
        this.propertyTopographyPK = propertyTopographyPK;
        this.percent = percent;
    }

    public PropertyTopography(int propertyId, int topographyId) {
        this.propertyTopographyPK = new PropertyTopographyPK(propertyId, topographyId);
    }

    public PropertyTopographyPK getPropertyTopographyPK() {
        return propertyTopographyPK;
    }

    public void setPropertyTopographyPK(PropertyTopographyPK propertyTopographyPK) {
        this.propertyTopographyPK = propertyTopographyPK;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public Topography getTopography() {
        return topography;
    }

    public void setTopography(Topography topography) {
        this.topography = topography;
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
        hash += (propertyTopographyPK != null ? propertyTopographyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof PropertyTopography)) {
            return false;
        }
        PropertyTopography other = (PropertyTopography) object;
        if ((this.propertyTopographyPK == null && other.propertyTopographyPK != null) || (this.propertyTopographyPK != null && !this.propertyTopographyPK.equals(other.propertyTopographyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.PropertyTopography[ propertyTopographyPK=" + propertyTopographyPK + " ]";
    }

    @Override
    public TranslationInterface getTranslationName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLabel(Language language) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

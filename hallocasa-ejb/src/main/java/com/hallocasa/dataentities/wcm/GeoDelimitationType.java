/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.dataentities.wcm;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "geo_delimitation_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeoDelimitationType.findAll", query = "SELECT g FROM GeoDelimitationType g"),
    @NamedQuery(name = "GeoDelimitationType.findByGeoDelimitationTypeId", query = "SELECT g FROM GeoDelimitationType g WHERE g.geoDelimitationTypeId = :geoDelimitationTypeId"),
    @NamedQuery(name = "GeoDelimitationType.findByGeoDelimitationName", query = "SELECT g FROM GeoDelimitationType g WHERE g.geoDelimitationName = :geoDelimitationName")})
public class GeoDelimitationType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "geo_delimitation_type_id")
    private Integer geoDelimitationTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "geo_delimitation_name")
    private String geoDelimitationName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geoDelimitationType", fetch = FetchType.LAZY)
    private List<GeoDelimitation> geoDelimitationList;

    public GeoDelimitationType() {
    }

    public GeoDelimitationType(Integer geoDelimitationTypeId) {
        this.geoDelimitationTypeId = geoDelimitationTypeId;
    }

    public GeoDelimitationType(Integer geoDelimitationTypeId, String geoDelimitationName) {
        this.geoDelimitationTypeId = geoDelimitationTypeId;
        this.geoDelimitationName = geoDelimitationName;
    }

    public Integer getGeoDelimitationTypeId() {
        return geoDelimitationTypeId;
    }

    public void setGeoDelimitationTypeId(Integer geoDelimitationTypeId) {
        this.geoDelimitationTypeId = geoDelimitationTypeId;
    }

    public String getGeoDelimitationName() {
        return geoDelimitationName;
    }

    public void setGeoDelimitationName(String geoDelimitationName) {
        this.geoDelimitationName = geoDelimitationName;
    }

    @XmlTransient
    public List<GeoDelimitation> getGeoDelimitationList() {
        return geoDelimitationList;
    }

    public void setGeoDelimitationList(List<GeoDelimitation> geoDelimitationList) {
        this.geoDelimitationList = geoDelimitationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (geoDelimitationTypeId != null ? geoDelimitationTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof GeoDelimitationType)) {
            return false;
        }
        GeoDelimitationType other = (GeoDelimitationType) object;
        if ((this.geoDelimitationTypeId == null && other.geoDelimitationTypeId != null) || (this.geoDelimitationTypeId != null && !this.geoDelimitationTypeId.equals(other.geoDelimitationTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.GeoDelimitationType[ geoDelimitationTypeId=" + geoDelimitationTypeId + " ]";
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

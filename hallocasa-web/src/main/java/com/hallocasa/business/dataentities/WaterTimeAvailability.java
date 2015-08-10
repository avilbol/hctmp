/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.business.dataentities;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "water_time_availability")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WaterTimeAvailability.findAll", query = "SELECT w FROM WaterTimeAvailability w"),
    @NamedQuery(name = "WaterTimeAvailability.findByWaterTimeAvailabilityId", query = "SELECT w FROM WaterTimeAvailability w WHERE w.waterTimeAvailabilityId = :waterTimeAvailabilityId"),
    @NamedQuery(name = "WaterTimeAvailability.findByName", query = "SELECT w FROM WaterTimeAvailability w WHERE w.name = :name")})
public class WaterTimeAvailability implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "water_time_availability_id")
    private Integer waterTimeAvailabilityId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "waterTimeAvailability", fetch = FetchType.LAZY)
    private List<PropertyWater> propertyWaterList;

    public WaterTimeAvailability() {
    }

    public WaterTimeAvailability(Integer waterTimeAvailabilityId) {
        this.waterTimeAvailabilityId = waterTimeAvailabilityId;
    }

    public WaterTimeAvailability(Integer waterTimeAvailabilityId, String name) {
        this.waterTimeAvailabilityId = waterTimeAvailabilityId;
        this.name = name;
    }

    public Integer getWaterTimeAvailabilityId() {
        return waterTimeAvailabilityId;
    }

    public void setWaterTimeAvailabilityId(Integer waterTimeAvailabilityId) {
        this.waterTimeAvailabilityId = waterTimeAvailabilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }

    @XmlTransient
    public List<PropertyWater> getPropertyWaterList() {
        return propertyWaterList;
    }

    public void setPropertyWaterList(List<PropertyWater> propertyWaterList) {
        this.propertyWaterList = propertyWaterList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (waterTimeAvailabilityId != null ? waterTimeAvailabilityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof WaterTimeAvailability)) {
            return false;
        }
        WaterTimeAvailability other = (WaterTimeAvailability) object;
        if ((this.waterTimeAvailabilityId == null && other.waterTimeAvailabilityId != null) || (this.waterTimeAvailabilityId != null && !this.waterTimeAvailabilityId.equals(other.waterTimeAvailabilityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.WaterTimeAvailability[ waterTimeAvailabilityId=" + waterTimeAvailabilityId + " ]";
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

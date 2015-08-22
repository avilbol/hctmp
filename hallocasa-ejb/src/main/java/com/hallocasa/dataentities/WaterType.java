/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.dataentities;

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
@Table(name = "water_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WaterType.findAll", query = "SELECT w FROM WaterType w"),
    @NamedQuery(name = "WaterType.findByWaterTypeId", query = "SELECT w FROM WaterType w WHERE w.waterTypeId = :waterTypeId"),
    @NamedQuery(name = "WaterType.findByName", query = "SELECT w FROM WaterType w WHERE w.name = :name")})
public class WaterType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "water_type_id")
    private Integer waterTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "waterType", fetch = FetchType.LAZY)
    private List<PropertyWater> propertyWaterList;

    public WaterType() {
    }

    public WaterType(Integer waterTypeId) {
        this.waterTypeId = waterTypeId;
    }

    public WaterType(Integer waterTypeId, String name) {
        this.waterTypeId = waterTypeId;
        this.name = name;
    }

    public Integer getWaterTypeId() {
        return waterTypeId;
    }

    public void setWaterTypeId(Integer waterTypeId) {
        this.waterTypeId = waterTypeId;
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
        hash += (waterTypeId != null ? waterTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof WaterType)) {
            return false;
        }
        WaterType other = (WaterType) object;
        if ((this.waterTypeId == null && other.waterTypeId != null) || (this.waterTypeId != null && !this.waterTypeId.equals(other.waterTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.WaterType[ waterTypeId=" + waterTypeId + " ]";
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

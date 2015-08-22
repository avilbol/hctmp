/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities;

import com.hallocasa.dataentities.interfaces.TypeInterface;
import com.hallocasa.commons.Language;

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
@Table(name = "region_growth_rate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegionGrowthRate.findAll", query = "SELECT r FROM RegionGrowthRate r"),
    @NamedQuery(name = "RegionGrowthRate.findByRegionGrowthRateId", query = "SELECT r FROM RegionGrowthRate r WHERE r.regionGrowthRateId = :regionGrowthRateId"),
    @NamedQuery(name = "RegionGrowthRate.findByName", query = "SELECT r FROM RegionGrowthRate r WHERE r.name = :name")})
public class RegionGrowthRate implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "region_growth_rate_id")
    private Integer regionGrowthRateId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionGrowthRate", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public RegionGrowthRate() {
    }

    public RegionGrowthRate(Integer regionGrowthRateId) {
        this.regionGrowthRateId = regionGrowthRateId;
    }

    public RegionGrowthRate(Integer regionGrowthRateId, String name) {
        this.regionGrowthRateId = regionGrowthRateId;
        this.name = name;
    }

    public Integer getRegionGrowthRateId() {
        return regionGrowthRateId;
    }

    public void setRegionGrowthRateId(Integer regionGrowthRateId) {
        this.regionGrowthRateId = regionGrowthRateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList() {
        return propertyEnvirontmentList;
    }

    public void setPropertyEnvirontmentList(List<PropertyEnvirontment> propertyEnvirontmentList) {
        this.propertyEnvirontmentList = propertyEnvirontmentList;
    }

    public Translation getTranslation() {
        return translationName;
    }

    public void setTranslation(Translation translation) {
        this.translationName = translation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (regionGrowthRateId != null ? regionGrowthRateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof RegionGrowthRate)) {
            return false;
        }
        RegionGrowthRate other = (RegionGrowthRate) object;
        if ((this.regionGrowthRateId == null && other.regionGrowthRateId != null) || (this.regionGrowthRateId != null && !this.regionGrowthRateId.equals(other.regionGrowthRateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.RegionGrowthRate[ regionGrowthRateId=" + regionGrowthRateId + " ]";
    }

    @Override
    public TranslationInterface getTranslationName() {
        return translationName;
    }

    @Override
    public String getLabel(Language language) {
        return getTranslationName().getText(language);
    }

    @Override
    public Integer getId() {
        return getRegionGrowthRateId();
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

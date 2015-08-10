/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.business.dataentities;

import com.hallocasa.business.dataentities.interfaces.TypeInterface;
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
@Table(name = "rain_intensity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RainIntensity.findAll", query = "SELECT r FROM RainIntensity r"),
    @NamedQuery(name = "RainIntensity.findByRainIntensityId", query = "SELECT r FROM RainIntensity r WHERE r.rainIntensityId = :rainIntensityId"),
    @NamedQuery(name = "RainIntensity.findByName", query = "SELECT r FROM RainIntensity r WHERE r.name = :name")})
public class RainIntensity implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rain_intensity_id")
    private Integer rainIntensityId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rainIntensity", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;

    public RainIntensity() {
    }

    public RainIntensity(Integer rainIntensityId) {
        this.rainIntensityId = rainIntensityId;
    }

    public RainIntensity(Integer rainIntensityId, String name) {
        this.rainIntensityId = rainIntensityId;
        this.name = name;
    }

    public Integer getRainIntensityId() {
        return rainIntensityId;
    }

    public void setRainIntensityId(Integer rainIntensityId) {
        this.rainIntensityId = rainIntensityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Translation getTranslation() {
        return translationName;
    }

    public void setTranslation(Translation translation) {
        this.translationName = translation;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList() {
        return propertyEnvirontmentList;
    }

    public void setPropertyEnvirontmentList(List<PropertyEnvirontment> propertyEnvirontmentList) {
        this.propertyEnvirontmentList = propertyEnvirontmentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rainIntensityId != null ? rainIntensityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof RainIntensity)) {
            return false;
        }
        RainIntensity other = (RainIntensity) object;
        if ((this.rainIntensityId == null && other.rainIntensityId != null) || (this.rainIntensityId != null && !this.rainIntensityId.equals(other.rainIntensityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.RainIntensity[ rainIntensityId=" + rainIntensityId + " ]";
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
        return getRainIntensityId();
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

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
@Table(name = "population_density")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PopulationDensity.findAll", query = "SELECT p FROM PopulationDensity p"),
    @NamedQuery(name = "PopulationDensity.findByPopulationDensityId", query = "SELECT p FROM PopulationDensity p WHERE p.populationDensityId = :populationDensityId"),
    @NamedQuery(name = "PopulationDensity.findByName", query = "SELECT p FROM PopulationDensity p WHERE p.name = :name")})
public class PopulationDensity implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "population_density_id")
    private Integer populationDensityId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "populationDensity", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;

    public PopulationDensity() {
    }

    public PopulationDensity(Integer populationDensityId) {
        this.populationDensityId = populationDensityId;
    }

    public PopulationDensity(Integer populationDensityId, String name) {
        this.populationDensityId = populationDensityId;
        this.name = name;
    }

    public Integer getPopulationDensityId() {
        return populationDensityId;
    }

    public void setPopulationDensityId(Integer populationDensityId) {
        this.populationDensityId = populationDensityId;
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
        hash += (populationDensityId != null ? populationDensityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof PopulationDensity)) {
            return false;
        }
        PopulationDensity other = (PopulationDensity) object;
        if ((this.populationDensityId == null && other.populationDensityId != null) || (this.populationDensityId != null && !this.populationDensityId.equals(other.populationDensityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.PopulationDensity[ populationDensityId=" + populationDensityId + " ]";
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
        return getPopulationDensityId();
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

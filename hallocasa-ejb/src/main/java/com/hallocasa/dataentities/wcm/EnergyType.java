/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.wcm;

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
@Table(name = "energy_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnergyType.findAll", query = "SELECT e FROM EnergyType e"),
    @NamedQuery(name = "EnergyType.findByEnergyTypeId", query = "SELECT e FROM EnergyType e WHERE e.energyTypeId = :energyTypeId"),
    @NamedQuery(name = "EnergyType.findByName", query = "SELECT e FROM EnergyType e WHERE e.name = :name")})
public class EnergyType implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "energy_type_id")
    private Integer energyTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "energyType", fetch = FetchType.LAZY)
    private List<Property> propertyList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public EnergyType() {
    }

    public EnergyType(Integer energyTypeId) {
        this.energyTypeId = energyTypeId;
    }

    public EnergyType(Integer energyTypeId, String name) {
        this.energyTypeId = energyTypeId;
        this.name = name;
    }

    public Integer getEnergyTypeId() {
        return energyTypeId;
    }

    public void setEnergyTypeId(Integer energyTypeId) {
        this.energyTypeId = energyTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
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
        hash += (energyTypeId != null ? energyTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof EnergyType)) {
            return false;
        }
        EnergyType other = (EnergyType) object;
        if ((this.energyTypeId == null && other.energyTypeId != null) || (this.energyTypeId != null && !this.energyTypeId.equals(other.energyTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.EnergyType[ energyTypeId=" + energyTypeId + " ]";
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
        return getEnergyTypeId();
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

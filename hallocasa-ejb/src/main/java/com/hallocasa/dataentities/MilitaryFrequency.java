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
@Table(name = "military_frequency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MilitaryFrequency.findAll", query = "SELECT m FROM MilitaryFrequency m"),
    @NamedQuery(name = "MilitaryFrequency.findByMilitaryFrequencyId", query = "SELECT m FROM MilitaryFrequency m WHERE m.militaryFrequencyId = :militaryFrequencyId"),
    @NamedQuery(name = "MilitaryFrequency.findByName", query = "SELECT m FROM MilitaryFrequency m WHERE m.name = :name")})
public class MilitaryFrequency implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "military_frequency_id")
    private Integer militaryFrequencyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "militaryFrequency", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public MilitaryFrequency() {
    }

    public MilitaryFrequency(Integer militaryFrequencyId) {
        this.militaryFrequencyId = militaryFrequencyId;
    }

    public MilitaryFrequency(Integer militaryFrequencyId, String name) {
        this.militaryFrequencyId = militaryFrequencyId;
        this.name = name;
    }

    public Integer getMilitaryFrequencyId() {
        return militaryFrequencyId;
    }

    public void setMilitaryFrequencyId(Integer militaryFrequencyId) {
        this.militaryFrequencyId = militaryFrequencyId;
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
        hash += (militaryFrequencyId != null ? militaryFrequencyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof MilitaryFrequency)) {
            return false;
        }
        MilitaryFrequency other = (MilitaryFrequency) object;
        if ((this.militaryFrequencyId == null && other.militaryFrequencyId != null) || (this.militaryFrequencyId != null && !this.militaryFrequencyId.equals(other.militaryFrequencyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.MilitaryFrequency[ militaryFrequencyId=" + militaryFrequencyId + " ]";
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
        return getMilitaryFrequencyId();
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

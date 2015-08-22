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
@Table(name = "paramilitary_frequency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParamilitaryFrequency.findAll", query = "SELECT p FROM ParamilitaryFrequency p"),
    @NamedQuery(name = "ParamilitaryFrequency.findByParamilitaryFrequencyId", query = "SELECT p FROM ParamilitaryFrequency p WHERE p.paramilitaryFrequencyId = :paramilitaryFrequencyId"),
    @NamedQuery(name = "ParamilitaryFrequency.findByName", query = "SELECT p FROM ParamilitaryFrequency p WHERE p.name = :name")})
public class ParamilitaryFrequency implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "paramilitary_frequency_id")
    private Integer paramilitaryFrequencyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paramilitaryFrequency", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;

    public ParamilitaryFrequency() {
    }

    public ParamilitaryFrequency(Integer paramilitaryFrequencyId) {
        this.paramilitaryFrequencyId = paramilitaryFrequencyId;
    }

    public ParamilitaryFrequency(Integer paramilitaryFrequencyId, String name) {
        this.paramilitaryFrequencyId = paramilitaryFrequencyId;
        this.name = name;
    }

    public Integer getParamilitaryFrequencyId() {
        return paramilitaryFrequencyId;
    }

    public void setParamilitaryFrequencyId(Integer paramilitaryFrequencyId) {
        this.paramilitaryFrequencyId = paramilitaryFrequencyId;
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
        hash += (paramilitaryFrequencyId != null ? paramilitaryFrequencyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ParamilitaryFrequency)) {
            return false;
        }
        ParamilitaryFrequency other = (ParamilitaryFrequency) object;
        if ((this.paramilitaryFrequencyId == null && other.paramilitaryFrequencyId != null) || (this.paramilitaryFrequencyId != null && !this.paramilitaryFrequencyId.equals(other.paramilitaryFrequencyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.ParamilitaryFrequency[ paramilitaryFrequencyId=" + paramilitaryFrequencyId + " ]";
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
        return getParamilitaryFrequencyId();
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

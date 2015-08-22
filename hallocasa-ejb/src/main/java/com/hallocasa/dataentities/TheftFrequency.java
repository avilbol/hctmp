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
@Table(name = "theft_frequency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TheftFrequency.findAll", query = "SELECT t FROM TheftFrequency t"),
    @NamedQuery(name = "TheftFrequency.findByTheftFrequencyId", query = "SELECT t FROM TheftFrequency t WHERE t.theftFrequencyId = :theftFrequencyId"),
    @NamedQuery(name = "TheftFrequency.findByName", query = "SELECT t FROM TheftFrequency t WHERE t.name = :name")})
public class TheftFrequency implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "theft_frequency_id")
    private Integer theftFrequencyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "theftFrequency", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public TheftFrequency() {
    }

    public TheftFrequency(Integer theftFrequencyId) {
        this.theftFrequencyId = theftFrequencyId;
    }

    public TheftFrequency(Integer theftFrequencyId, String name) {
        this.theftFrequencyId = theftFrequencyId;
        this.name = name;
    }

    public Integer getTheftFrequencyId() {
        return theftFrequencyId;
    }

    public void setTheftFrequencyId(Integer theftFrequencyId) {
        this.theftFrequencyId = theftFrequencyId;
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
        hash += (theftFrequencyId != null ? theftFrequencyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof TheftFrequency)) {
            return false;
        }
        TheftFrequency other = (TheftFrequency) object;
        if ((this.theftFrequencyId == null && other.theftFrequencyId != null) || (this.theftFrequencyId != null && !this.theftFrequencyId.equals(other.theftFrequencyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.TheftFrequency[ theftFrequencyId=" + theftFrequencyId + " ]";
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
        return getTheftFrequencyId();
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
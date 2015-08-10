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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "property_use")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PropertyUse.findAll", query = "SELECT p FROM PropertyUse p"),
    @NamedQuery(name = "PropertyUse.findByPropertyUseId", query = "SELECT p FROM PropertyUse p WHERE p.propertyUseId = :propertyUseId"),
    @NamedQuery(name = "PropertyUse.findByName", query = "SELECT p FROM PropertyUse p WHERE p.name = :name")})
public class PropertyUse implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "property_use_id")
    private Integer propertyUseId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "propertyUseList", fetch = FetchType.LAZY)
    private List<Property> propertyList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public PropertyUse() {
    }

    public PropertyUse(Integer propertyUseId) {
        this.propertyUseId = propertyUseId;
    }

    public PropertyUse(Integer propertyUseId, String name) {
        this.propertyUseId = propertyUseId;
        this.name = name;
    }

    public Integer getPropertyUseId() {
        return propertyUseId;
    }

    public void setPropertyUseId(Integer propertyUseId) {
        this.propertyUseId = propertyUseId;
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
        hash += (propertyUseId != null ? propertyUseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof PropertyUse)) {
            return false;
        }
        PropertyUse other = (PropertyUse) object;
        if ((this.propertyUseId == null && other.propertyUseId != null) || (this.propertyUseId != null && !this.propertyUseId.equals(other.propertyUseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.PropertyUse[ propertyUseId=" + propertyUseId + " ]";
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
        return getPropertyUseId();
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

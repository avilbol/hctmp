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
@Table(name = "property_condition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PropertyCondition.findAll", query = "SELECT p FROM PropertyCondition p"),
    @NamedQuery(name = "PropertyCondition.findByPropertyConditionId", query = "SELECT p FROM PropertyCondition p WHERE p.propertyConditionId = :propertyConditionId"),
    @NamedQuery(name = "PropertyCondition.findByName", query = "SELECT p FROM PropertyCondition p WHERE p.name = :name")})
public class PropertyCondition implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "property_condition_id")
    private Integer propertyConditionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propertyCondition", fetch = FetchType.LAZY)
    private List<Property> propertyList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public PropertyCondition() {
    }

    public PropertyCondition(Integer propertyConditionId) {
        this.propertyConditionId = propertyConditionId;
    }

    public PropertyCondition(Integer propertyConditionId, String name) {
        this.propertyConditionId = propertyConditionId;
        this.name = name;
    }

    public Integer getPropertyConditionId() {
        return propertyConditionId;
    }

    public void setPropertyConditionId(Integer propertyConditionId) {
        this.propertyConditionId = propertyConditionId;
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
        hash += (propertyConditionId != null ? propertyConditionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof PropertyCondition)) {
            return false;
        }
        PropertyCondition other = (PropertyCondition) object;
        if ((this.propertyConditionId == null && other.propertyConditionId != null) || (this.propertyConditionId != null && !this.propertyConditionId.equals(other.propertyConditionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.PropertyCondition[ propertyConditionId=" + propertyConditionId + " ]";
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
        return getPropertyConditionId();
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
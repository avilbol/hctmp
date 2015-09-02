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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "property_characteristic_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PropertyCharacteristicType.findAll", query = "SELECT p FROM PropertyCharacteristicType p"),
    @NamedQuery(name = "PropertyCharacteristicType.findByPropertyCharacteristicTypeId", query = "SELECT p FROM PropertyCharacteristicType p WHERE p.propertyCharacteristicTypeId = :propertyCharacteristicTypeId"),
    @NamedQuery(name = "PropertyCharacteristicType.findByName", query = "SELECT p FROM PropertyCharacteristicType p WHERE p.name = :name")})
public class PropertyCharacteristicType implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "property_characteristic_type_id")
    private Integer propertyCharacteristicTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "property_charasteristic", joinColumns = {
        @JoinColumn(name = "property_charasteristic_type_id", referencedColumnName = "property_characteristic_type_id")}, inverseJoinColumns = {
        @JoinColumn(name = "property_charasteristic_id", referencedColumnName = "property_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Property> propertyList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public PropertyCharacteristicType() {
    }

    public PropertyCharacteristicType(Integer propertyCharacteristicTypeId) {
        this.propertyCharacteristicTypeId = propertyCharacteristicTypeId;
    }

    public PropertyCharacteristicType(Integer propertyCharacteristicTypeId, String name) {
        this.propertyCharacteristicTypeId = propertyCharacteristicTypeId;
        this.name = name;
    }

    public Integer getPropertyCharacteristicTypeId() {
        return propertyCharacteristicTypeId;
    }

    public void setPropertyCharacteristicTypeId(Integer propertyCharacteristicTypeId) {
        this.propertyCharacteristicTypeId = propertyCharacteristicTypeId;
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
        hash += (propertyCharacteristicTypeId != null ? propertyCharacteristicTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof PropertyCharacteristicType)) {
            return false;
        }
        PropertyCharacteristicType other = (PropertyCharacteristicType) object;
        if ((this.propertyCharacteristicTypeId == null && other.propertyCharacteristicTypeId != null) || (this.propertyCharacteristicTypeId != null && !this.propertyCharacteristicTypeId.equals(other.propertyCharacteristicTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.PropertyCharacteristicType[ propertyCharacteristicTypeId=" + propertyCharacteristicTypeId + " ]";
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
        return getPropertyCharacteristicTypeId();
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

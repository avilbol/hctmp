/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.wcm;

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

import com.hallocasa.dataentities.interfaces.TypeInterface;
import com.hallocasa.commons.Language;

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "forniture_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FornitureType.findAll", query = "SELECT f FROM FornitureType f"),
    @NamedQuery(name = "FornitureType.findByFornitureTypeId", query = "SELECT f FROM FornitureType f WHERE f.fornitureTypeId = :fornitureTypeId"),
    @NamedQuery(name = "FornitureType.findByName", query = "SELECT f FROM FornitureType f WHERE f.name = :name")})
public class FornitureType implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "forniture_type_id")
    private Integer fornitureTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornitureType", fetch = FetchType.LAZY)
    private List<Property> propertyList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public FornitureType() {
    }

    public FornitureType(Integer fornitureTypeId) {
        this.fornitureTypeId = fornitureTypeId;
    }

    public FornitureType(Integer fornitureTypeId, String name) {
        this.fornitureTypeId = fornitureTypeId;
        this.name = name;
    }

    public Integer getFornitureTypeId() {
        return fornitureTypeId;
    }

    public void setFornitureTypeId(Integer fornitureTypeId) {
        this.fornitureTypeId = fornitureTypeId;
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

//    public Translation getTranslation() {
//        return translation;
//    }

    public void setTranslation(Translation translation) {
        this.translationName = translation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fornitureTypeId != null ? fornitureTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof FornitureType)) {
            return false;
        }
        FornitureType other = (FornitureType) object;
        if ((this.fornitureTypeId == null && other.fornitureTypeId != null) || (this.fornitureTypeId != null && !this.fornitureTypeId.equals(other.fornitureTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.FornitureType[ fornitureTypeId=" + fornitureTypeId + " ]";
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
        return getFornitureTypeId();
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

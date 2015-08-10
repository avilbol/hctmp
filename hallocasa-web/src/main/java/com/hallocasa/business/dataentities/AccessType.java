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
@Table(name = "access_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccessType.findAll", query = "SELECT a FROM AccessType a"),
    @NamedQuery(name = "AccessType.findByAccessTypeId", query = "SELECT a FROM AccessType a WHERE a.accessTypeId = :accessTypeId"),
    @NamedQuery(name = "AccessType.findByName", query = "SELECT a FROM AccessType a WHERE a.name = :name")})
public class AccessType implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "access_type_id")
    private Integer accessTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accessType", fetch = FetchType.LAZY)
    private List<PropertyAccess> propertyAccessList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public AccessType() {
    }

    public AccessType(Integer accessTypeId) {
        this.accessTypeId = accessTypeId;
    }

    public AccessType(Integer accessTypeId, String name) {
        this.accessTypeId = accessTypeId;
        this.name = name;
    }

    public Integer getAccessTypeId() {
        return accessTypeId;
    }

    public void setAccessTypeId(Integer accessTypeId) {
        this.accessTypeId = accessTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<PropertyAccess> getPropertyAccessList() {
        return propertyAccessList;
    }

    public void setPropertyAccessList(List<PropertyAccess> propertyAccessList) {
        this.propertyAccessList = propertyAccessList;
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
        hash += (accessTypeId != null ? accessTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof AccessType)) {
            return false;
        }
        AccessType other = (AccessType) object;
        if ((this.accessTypeId == null && other.accessTypeId != null) || (this.accessTypeId != null && !this.accessTypeId.equals(other.accessTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.AccessType[ accessTypeId=" + accessTypeId + " ]";
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
        return accessTypeId;
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

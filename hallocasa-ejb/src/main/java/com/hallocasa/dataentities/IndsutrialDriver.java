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
@Table(name = "indsutrial_driver")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IndsutrialDriver.findAll", query = "SELECT i FROM IndsutrialDriver i"),
    @NamedQuery(name = "IndsutrialDriver.findByIndsutrialDriverId", query = "SELECT i FROM IndsutrialDriver i WHERE i.indsutrialDriverId = :indsutrialDriverId"),
    @NamedQuery(name = "IndsutrialDriver.findByName", query = "SELECT i FROM IndsutrialDriver i WHERE i.name = :name")})
public class IndsutrialDriver implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "indsutrial_driver_id")
    private Integer indsutrialDriverId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "indsutrialDriver", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;

    public IndsutrialDriver() {
    }

    public IndsutrialDriver(Integer indsutrialDriverId) {
        this.indsutrialDriverId = indsutrialDriverId;
    }

    public IndsutrialDriver(Integer indsutrialDriverId, String name) {
        this.indsutrialDriverId = indsutrialDriverId;
        this.name = name;
    }

    public Integer getIndsutrialDriverId() {
        return indsutrialDriverId;
    }

    public void setIndsutrialDriverId(Integer indsutrialDriverId) {
        this.indsutrialDriverId = indsutrialDriverId;
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
        hash += (indsutrialDriverId != null ? indsutrialDriverId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof IndsutrialDriver)) {
            return false;
        }
        IndsutrialDriver other = (IndsutrialDriver) object;
        if ((this.indsutrialDriverId == null && other.indsutrialDriverId != null) || (this.indsutrialDriverId != null && !this.indsutrialDriverId.equals(other.indsutrialDriverId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.IndsutrialDriver[ indsutrialDriverId=" + indsutrialDriverId + " ]";
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
        return getIndsutrialDriverId();
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

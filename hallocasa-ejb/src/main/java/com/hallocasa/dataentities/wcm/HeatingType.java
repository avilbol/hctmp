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
@Table(name = "heating_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HeatingType.findAll", query = "SELECT h FROM HeatingType h"),
    @NamedQuery(name = "HeatingType.findByHeatingTypeId", query = "SELECT h FROM HeatingType h WHERE h.heatingTypeId = :heatingTypeId"),
    @NamedQuery(name = "HeatingType.findByName", query = "SELECT h FROM HeatingType h WHERE h.name = :name")})
public class HeatingType implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "heating_type_id")
    private Integer heatingTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "heatingType", fetch = FetchType.LAZY)
    private List<Property> propertyList;

    public HeatingType() {
    }

    public HeatingType(Integer heatingTypeId) {
        this.heatingTypeId = heatingTypeId;
    }

    public HeatingType(Integer heatingTypeId, String name) {
        this.heatingTypeId = heatingTypeId;
        this.name = name;
    }

    public Integer getHeatingTypeId() {
        return heatingTypeId;
    }

    public void setHeatingTypeId(Integer heatingTypeId) {
        this.heatingTypeId = heatingTypeId;
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
    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (heatingTypeId != null ? heatingTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof HeatingType)) {
            return false;
        }
        HeatingType other = (HeatingType) object;
        if ((this.heatingTypeId == null && other.heatingTypeId != null) || (this.heatingTypeId != null && !this.heatingTypeId.equals(other.heatingTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.HeatingType[ heatingTypeId=" + heatingTypeId + " ]";
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
        return getHeatingTypeId();
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

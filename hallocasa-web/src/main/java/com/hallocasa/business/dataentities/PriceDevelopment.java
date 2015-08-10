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
@Table(name = "price_development")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PriceDevelopment.findAll", query = "SELECT p FROM PriceDevelopment p"),
    @NamedQuery(name = "PriceDevelopment.findByPriceDevelopmentId", query = "SELECT p FROM PriceDevelopment p WHERE p.priceDevelopmentId = :priceDevelopmentId"),
    @NamedQuery(name = "PriceDevelopment.findByName", query = "SELECT p FROM PriceDevelopment p WHERE p.name = :name")})
public class PriceDevelopment implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "price_development_id")
    private Integer priceDevelopmentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceDevelopment", fetch = FetchType.LAZY)
    private List<Property> propertyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceDevelopment1", fetch = FetchType.LAZY)
    private List<Property> propertyList1;

    public PriceDevelopment() {
    }

    public PriceDevelopment(Integer priceDevelopmentId) {
        this.priceDevelopmentId = priceDevelopmentId;
    }

    public PriceDevelopment(Integer priceDevelopmentId, String name) {
        this.priceDevelopmentId = priceDevelopmentId;
        this.name = name;
    }

    public Integer getPriceDevelopmentId() {
        return priceDevelopmentId;
    }

    public void setPriceDevelopmentId(Integer priceDevelopmentId) {
        this.priceDevelopmentId = priceDevelopmentId;
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

    @XmlTransient
    public List<Property> getPropertyList1() {
        return propertyList1;
    }

    public void setPropertyList1(List<Property> propertyList1) {
        this.propertyList1 = propertyList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (priceDevelopmentId != null ? priceDevelopmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof PriceDevelopment)) {
            return false;
        }
        PriceDevelopment other = (PriceDevelopment) object;
        if ((this.priceDevelopmentId == null && other.priceDevelopmentId != null) || (this.priceDevelopmentId != null && !this.priceDevelopmentId.equals(other.priceDevelopmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.PriceDevelopment[ priceDevelopmentId=" + priceDevelopmentId + " ]";
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
        return getPriceDevelopmentId();
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

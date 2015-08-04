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
@Table(name = "shopping_way")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShoppingWay.findAll", query = "SELECT s FROM ShoppingWay s"),
    @NamedQuery(name = "ShoppingWay.findByShoppingWayId", query = "SELECT s FROM ShoppingWay s WHERE s.shoppingWayId = :shoppingWayId"),
    @NamedQuery(name = "ShoppingWay.findByName", query = "SELECT s FROM ShoppingWay s WHERE s.name = :name")})
public class ShoppingWay implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "shopping_way_id")
    private Integer shoppingWayId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "shoppingWayList", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public ShoppingWay() {
    }

    public ShoppingWay(Integer shoppingWayId) {
        this.shoppingWayId = shoppingWayId;
    }

    public ShoppingWay(Integer shoppingWayId, String name) {
        this.shoppingWayId = shoppingWayId;
        this.name = name;
    }

    public Integer getShoppingWayId() {
        return shoppingWayId;
    }

    public void setShoppingWayId(Integer shoppingWayId) {
        this.shoppingWayId = shoppingWayId;
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
        hash += (shoppingWayId != null ? shoppingWayId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ShoppingWay)) {
            return false;
        }
        ShoppingWay other = (ShoppingWay) object;
        if ((this.shoppingWayId == null && other.shoppingWayId != null) || (this.shoppingWayId != null && !this.shoppingWayId.equals(other.shoppingWayId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.ShoppingWay[ shoppingWayId=" + shoppingWayId + " ]";
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
        return getShoppingWayId();
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

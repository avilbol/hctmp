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
@Table(name = "restaurant_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RestaurantType.findAll", query = "SELECT r FROM RestaurantType r"),
    @NamedQuery(name = "RestaurantType.findByRestaurantTypeId", query = "SELECT r FROM RestaurantType r WHERE r.restaurantTypeId = :restaurantTypeId"),
    @NamedQuery(name = "RestaurantType.findByName", query = "SELECT r FROM RestaurantType r WHERE r.name = :name")})
public class RestaurantType implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "restaurant_type_id")
    private Integer restaurantTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "restaurantTypeList", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public RestaurantType() {
    }

    public RestaurantType(Integer restaurantTypeId) {
        this.restaurantTypeId = restaurantTypeId;
    }

    public RestaurantType(Integer restaurantTypeId, String name) {
        this.restaurantTypeId = restaurantTypeId;
        this.name = name;
    }

    public Integer getRestaurantTypeId() {
        return restaurantTypeId;
    }

    public void setRestaurantTypeId(Integer restaurantTypeId) {
        this.restaurantTypeId = restaurantTypeId;
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
        hash += (restaurantTypeId != null ? restaurantTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof RestaurantType)) {
            return false;
        }
        RestaurantType other = (RestaurantType) object;
        if ((this.restaurantTypeId == null && other.restaurantTypeId != null) || (this.restaurantTypeId != null && !this.restaurantTypeId.equals(other.restaurantTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.RestaurantType[ restaurantTypeId=" + restaurantTypeId + " ]";
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
        return getRestaurantTypeId();
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

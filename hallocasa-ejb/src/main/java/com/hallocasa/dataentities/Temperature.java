/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.dataentities;

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
@Table(name = "temperature")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Temperature.findAll", query = "SELECT t FROM Temperature t"),
    @NamedQuery(name = "Temperature.findByTemperatureId", query = "SELECT t FROM Temperature t WHERE t.temperatureId = :temperatureId"),
    @NamedQuery(name = "Temperature.findByName", query = "SELECT t FROM Temperature t WHERE t.name = :name")})
public class Temperature implements Serializable, TypeInterface {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "temperature_id")
    private Integer temperatureId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "temperature", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "temperature1", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "temperature2", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "temperature3", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList3;

    public Temperature() {
    }

    public Temperature(Integer temperatureId) {
        this.temperatureId = temperatureId;
    }

    public Temperature(Integer temperatureId, String name) {
        this.temperatureId = temperatureId;
        this.name = name;
    }

    public Integer getTemperatureId() {
        return temperatureId;
    }

    public void setTemperatureId(Integer temperatureId) {
        this.temperatureId = temperatureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList() {
        return propertyEnvirontmentList;
    }

    public void setPropertyEnvirontmentList(List<PropertyEnvirontment> propertyEnvirontmentList) {
        this.propertyEnvirontmentList = propertyEnvirontmentList;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList1() {
        return propertyEnvirontmentList1;
    }

    public void setPropertyEnvirontmentList1(List<PropertyEnvirontment> propertyEnvirontmentList1) {
        this.propertyEnvirontmentList1 = propertyEnvirontmentList1;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList2() {
        return propertyEnvirontmentList2;
    }

    public void setPropertyEnvirontmentList2(List<PropertyEnvirontment> propertyEnvirontmentList2) {
        this.propertyEnvirontmentList2 = propertyEnvirontmentList2;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList3() {
        return propertyEnvirontmentList3;
    }

    public void setPropertyEnvirontmentList3(List<PropertyEnvirontment> propertyEnvirontmentList3) {
        this.propertyEnvirontmentList3 = propertyEnvirontmentList3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (temperatureId != null ? temperatureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Temperature)) {
            return false;
        }
        Temperature other = (Temperature) object;
        if ((this.temperatureId == null && other.temperatureId != null) || (this.temperatureId != null && !this.temperatureId.equals(other.temperatureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.Temperature[ temperatureId=" + temperatureId + " ]";
    }

    @Override
    public TranslationInterface getTranslationName() {
        return translation;
    }

    @Override
    public String getLabel(Language language) {
        return getTranslationName().getText(language);
    }

    @Override
    public Integer getId() {
        return getTemperatureId();
    }

     /**************************************************************************
     * Constanst
     ***************************************************************************
     */

    /***************************************************************************
     * Instance variable
     ***************************************************************************
     */

    /***************************************************************************
     * Constructor
     ***************************************************************************
     */

    /***************************************************************************
     * Methods
     **************************************************************************
     */

    /**
     * *************************************************************************
     * Getters y Setters
     * *************************************************************************
     */

}

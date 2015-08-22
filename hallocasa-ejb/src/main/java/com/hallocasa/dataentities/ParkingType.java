/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.dataentities;

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
@Table(name = "parking_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParkingType.findAll", query = "SELECT p FROM ParkingType p"),
    @NamedQuery(name = "ParkingType.findByParkingTypeId", query = "SELECT p FROM ParkingType p WHERE p.parkingTypeId = :parkingTypeId"),
    @NamedQuery(name = "ParkingType.findByName", query = "SELECT p FROM ParkingType p WHERE p.name = :name")})
public class ParkingType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "parking_type_id")
    private Integer parkingTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "property_parking", joinColumns = {
        @JoinColumn(name = "parking_type_id", referencedColumnName = "parking_type_id")}, inverseJoinColumns = {
        @JoinColumn(name = "property_id", referencedColumnName = "property_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Property> propertyList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translation;

    public ParkingType() {
    }

    public ParkingType(Integer parkingTypeId) {
        this.parkingTypeId = parkingTypeId;
    }

    public ParkingType(Integer parkingTypeId, String name) {
        this.parkingTypeId = parkingTypeId;
        this.name = name;
    }

    public Integer getParkingTypeId() {
        return parkingTypeId;
    }

    public void setParkingTypeId(Integer parkingTypeId) {
        this.parkingTypeId = parkingTypeId;
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
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parkingTypeId != null ? parkingTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof ParkingType)) {
            return false;
        }
        ParkingType other = (ParkingType) object;
        if ((this.parkingTypeId == null && other.parkingTypeId != null) || (this.parkingTypeId != null && !this.parkingTypeId.equals(other.parkingTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.ParkingType[ parkingTypeId=" + parkingTypeId + " ]";
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

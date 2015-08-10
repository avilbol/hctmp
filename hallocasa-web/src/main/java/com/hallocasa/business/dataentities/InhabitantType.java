/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.business.dataentities;

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
@Table(name = "inhabitant_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InhabitantType.findAll", query = "SELECT i FROM InhabitantType i"),
    @NamedQuery(name = "InhabitantType.findByInhabitantTypeId", query = "SELECT i FROM InhabitantType i WHERE i.inhabitantTypeId = :inhabitantTypeId"),
    @NamedQuery(name = "InhabitantType.findByName", query = "SELECT i FROM InhabitantType i WHERE i.name = :name")})
public class InhabitantType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "inhabitant_type_id")
    private Integer inhabitantTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "suitable_for", joinColumns = {
        @JoinColumn(name = "inhabitant_type_id", referencedColumnName = "inhabitant_type_id")}, inverseJoinColumns = {
        @JoinColumn(name = "property_id", referencedColumnName = "property_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Property> propertyList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translation;

    public InhabitantType() {
    }

    public InhabitantType(Integer inhabitantTypeId) {
        this.inhabitantTypeId = inhabitantTypeId;
    }

    public InhabitantType(Integer inhabitantTypeId, String name) {
        this.inhabitantTypeId = inhabitantTypeId;
        this.name = name;
    }

    public Integer getInhabitantTypeId() {
        return inhabitantTypeId;
    }

    public void setInhabitantTypeId(Integer inhabitantTypeId) {
        this.inhabitantTypeId = inhabitantTypeId;
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
        hash += (inhabitantTypeId != null ? inhabitantTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof InhabitantType)) {
            return false;
        }
        InhabitantType other = (InhabitantType) object;
        if ((this.inhabitantTypeId == null && other.inhabitantTypeId != null) || (this.inhabitantTypeId != null && !this.inhabitantTypeId.equals(other.inhabitantTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.InhabitantType[ inhabitantTypeId=" + inhabitantTypeId + " ]";
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

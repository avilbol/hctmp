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

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "topography")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Topography.findAll", query = "SELECT t FROM Topography t"),
    @NamedQuery(name = "Topography.findByTopographyId", query = "SELECT t FROM Topography t WHERE t.topographyId = :topographyId"),
    @NamedQuery(name = "Topography.findByName", query = "SELECT t FROM Topography t WHERE t.name = :name")})
public class Topography implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "topography_id")
    private Integer topographyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topography", fetch = FetchType.LAZY)
    private List<PropertyTopography> propertyTopographyList;

    public Topography() {
    }

    public Topography(Integer topographyId) {
        this.topographyId = topographyId;
    }

    public Topography(Integer topographyId, String name) {
        this.topographyId = topographyId;
        this.name = name;
    }

    public Integer getTopographyId() {
        return topographyId;
    }

    public void setTopographyId(Integer topographyId) {
        this.topographyId = topographyId;
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
    public List<PropertyTopography> getPropertyTopographyList() {
        return propertyTopographyList;
    }

    public void setPropertyTopographyList(List<PropertyTopography> propertyTopographyList) {
        this.propertyTopographyList = propertyTopographyList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (topographyId != null ? topographyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Topography)) {
            return false;
        }
        Topography other = (Topography) object;
        if ((this.topographyId == null && other.topographyId != null) || (this.topographyId != null && !this.topographyId.equals(other.topographyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.Topography[ topographyId=" + topographyId + " ]";
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

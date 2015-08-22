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
@Table(name = "neighbor_community")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NeighborCommunity.findAll", query = "SELECT n FROM NeighborCommunity n"),
    @NamedQuery(name = "NeighborCommunity.findByNeighborCommunityId", query = "SELECT n FROM NeighborCommunity n WHERE n.neighborCommunityId = :neighborCommunityId"),
    @NamedQuery(name = "NeighborCommunity.findByName", query = "SELECT n FROM NeighborCommunity n WHERE n.name = :name")})
public class NeighborCommunity implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "neighbor_community_id")
    private Integer neighborCommunityId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "neighborCommunity", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public NeighborCommunity() {
    }

    public NeighborCommunity(Integer neighborCommunityId) {
        this.neighborCommunityId = neighborCommunityId;
    }

    public NeighborCommunity(Integer neighborCommunityId, String name) {
        this.neighborCommunityId = neighborCommunityId;
        this.name = name;
    }

    public Integer getNeighborCommunityId() {
        return neighborCommunityId;
    }

    public void setNeighborCommunityId(Integer neighborCommunityId) {
        this.neighborCommunityId = neighborCommunityId;
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
        hash += (neighborCommunityId != null ? neighborCommunityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof NeighborCommunity)) {
            return false;
        }
        NeighborCommunity other = (NeighborCommunity) object;
        if ((this.neighborCommunityId == null && other.neighborCommunityId != null) || (this.neighborCommunityId != null && !this.neighborCommunityId.equals(other.neighborCommunityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.NeighborCommunity[ neighborCommunityId=" + neighborCommunityId + " ]";
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
        return getNeighborCommunityId();
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

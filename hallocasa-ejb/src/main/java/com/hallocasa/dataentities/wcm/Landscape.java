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
@Table(name = "landscape")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Landscape.findAll", query = "SELECT l FROM Landscape l"),
    @NamedQuery(name = "Landscape.findByLandscapeId", query = "SELECT l FROM Landscape l WHERE l.landscapeId = :landscapeId"),
    @NamedQuery(name = "Landscape.findByName", query = "SELECT l FROM Landscape l WHERE l.name = :name")})
public class Landscape implements Serializable, TypeInterface {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "landscape_id")
    private Integer landscapeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "landscape", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public Landscape() {
    }

    public Landscape(Integer landscapeId) {
        this.landscapeId = landscapeId;
    }

    public Landscape(Integer landscapeId, String name) {
        this.landscapeId = landscapeId;
        this.name = name;
    }

    public Integer getLandscapeId() {
        return landscapeId;
    }

    public void setLandscapeId(Integer landscapeId) {
        this.landscapeId = landscapeId;
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
        hash += (landscapeId != null ? landscapeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Landscape)) {
            return false;
        }
        Landscape other = (Landscape) object;
        if ((this.landscapeId == null && other.landscapeId != null) || (this.landscapeId != null && !this.landscapeId.equals(other.landscapeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.Landscape[ landscapeId=" + landscapeId + " ]";
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
        return getLandscapeId();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.wcm;

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
@Table(name = "sea_level")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeaLevel.findAll", query = "SELECT s FROM SeaLevel s"),
    @NamedQuery(name = "SeaLevel.findBySeaLevelId", query = "SELECT s FROM SeaLevel s WHERE s.seaLevelId = :seaLevelId"),
    @NamedQuery(name = "SeaLevel.findByName", query = "SELECT s FROM SeaLevel s WHERE s.name = :name")})
public class SeaLevel implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sea_level_id")
    private Integer seaLevelId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seaLevel", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public SeaLevel() {
    }

    public SeaLevel(Integer seaLevelId) {
        this.seaLevelId = seaLevelId;
    }

    public SeaLevel(Integer seaLevelId, String name) {
        this.seaLevelId = seaLevelId;
        this.name = name;
    }

    public Integer getSeaLevelId() {
        return seaLevelId;
    }

    public void setSeaLevelId(Integer seaLevelId) {
        this.seaLevelId = seaLevelId;
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
        hash += (seaLevelId != null ? seaLevelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof SeaLevel)) {
            return false;
        }
        SeaLevel other = (SeaLevel) object;
        if ((this.seaLevelId == null && other.seaLevelId != null) || (this.seaLevelId != null && !this.seaLevelId.equals(other.seaLevelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.SeaLevel[ seaLevelId=" + seaLevelId + " ]";
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
        return getSeaLevelId();
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

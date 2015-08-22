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
@Table(name = "construction_method")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConstructionMethod.findAll", query = "SELECT c FROM ConstructionMethod c"),
    @NamedQuery(name = "ConstructionMethod.findByConstructionMethodId", query = "SELECT c FROM ConstructionMethod c WHERE c.constructionMethodId = :constructionMethodId"),
    @NamedQuery(name = "ConstructionMethod.findByName", query = "SELECT c FROM ConstructionMethod c WHERE c.name = :name")})
public class ConstructionMethod implements Serializable, TypeInterface {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "construction_method_id")
    private Integer constructionMethodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "constructionMethod", fetch = FetchType.LAZY)
    private List<Property> propertyList;

    public ConstructionMethod() {
    }

    public ConstructionMethod(Integer constructionMethodId) {
        this.constructionMethodId = constructionMethodId;
    }

    public ConstructionMethod(Integer constructionMethodId, String name) {
        this.constructionMethodId = constructionMethodId;
        this.name = name;
    }

    public Integer getConstructionMethodId() {
        return constructionMethodId;
    }

    public void setConstructionMethodId(Integer constructionMethodId) {
        this.constructionMethodId = constructionMethodId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (constructionMethodId != null ? constructionMethodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof ConstructionMethod)) {
            return false;
        }
        ConstructionMethod other = (ConstructionMethod) object;
        if ((this.constructionMethodId == null && other.constructionMethodId != null) || (this.constructionMethodId != null && !this.constructionMethodId.equals(other.constructionMethodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.ConstructionMethod[ constructionMethodId=" + constructionMethodId + " ]";
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
        return getConstructionMethodId();
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

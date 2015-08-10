/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.business.dataentities;

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
@Table(name = "text_source_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TextSourceType.findAll", query = "SELECT t FROM TextSourceType t"),
    @NamedQuery(name = "TextSourceType.findByTextSourceTypeId", query = "SELECT t FROM TextSourceType t WHERE t.textSourceTypeId = :textSourceTypeId"),
    @NamedQuery(name = "TextSourceType.findByName", query = "SELECT t FROM TextSourceType t WHERE t.name = :name")})
public class TextSourceType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "text_source_type_id")
    private Integer textSourceTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "textSourceType", fetch = FetchType.LAZY)
    private List<Translation> translationList;

    public TextSourceType() {
    }

    public TextSourceType(Integer textSourceTypeId) {
        this.textSourceTypeId = textSourceTypeId;
    }

    public TextSourceType(Integer textSourceTypeId, String name) {
        this.textSourceTypeId = textSourceTypeId;
        this.name = name;
    }

    public Integer getTextSourceTypeId() {
        return textSourceTypeId;
    }

    public void setTextSourceTypeId(Integer textSourceTypeId) {
        this.textSourceTypeId = textSourceTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Translation> getTranslationList() {
        return translationList;
    }

    public void setTranslationList(List<Translation> translationList) {
        this.translationList = translationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (textSourceTypeId != null ? textSourceTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof TextSourceType)) {
            return false;
        }
        TextSourceType other = (TextSourceType) object;
        if ((this.textSourceTypeId == null && other.textSourceTypeId != null) || (this.textSourceTypeId != null && !this.textSourceTypeId.equals(other.textSourceTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.TextSourceType[ textSourceTypeId=" + textSourceTypeId + " ]";
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

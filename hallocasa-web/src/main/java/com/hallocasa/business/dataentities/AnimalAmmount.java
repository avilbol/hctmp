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
@Table(name = "animal_ammount")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnimalAmmount.findAll", query = "SELECT a FROM AnimalAmmount a"),
    @NamedQuery(name = "AnimalAmmount.findByAnimalAmmountId", query = "SELECT a FROM AnimalAmmount a WHERE a.animalAmmountId = :animalAmmountId"),
    @NamedQuery(name = "AnimalAmmount.findByName", query = "SELECT a FROM AnimalAmmount a WHERE a.name = :name")})
public class AnimalAmmount implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "animal_ammount_id")
    private Integer animalAmmountId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animalAmmount", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public AnimalAmmount() {
    }

    public AnimalAmmount(Integer animalAmmountId) {
        this.animalAmmountId = animalAmmountId;
    }

    public AnimalAmmount(Integer animalAmmountId, String name) {
        this.animalAmmountId = animalAmmountId;
        this.name = name;
    }

    public Integer getAnimalAmmountId() {
        return animalAmmountId;
    }

    public void setAnimalAmmountId(Integer animalAmmountId) {
        this.animalAmmountId = animalAmmountId;
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
        hash += (animalAmmountId != null ? animalAmmountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof AnimalAmmount)) {
            return false;
        }
        AnimalAmmount other = (AnimalAmmount) object;
        if ((this.animalAmmountId == null && other.animalAmmountId != null) || (this.animalAmmountId != null && !this.animalAmmountId.equals(other.animalAmmountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.AnimalAmmount[ animalAmmountId=" + animalAmmountId + " ]";
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
        return getAnimalAmmountId();
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

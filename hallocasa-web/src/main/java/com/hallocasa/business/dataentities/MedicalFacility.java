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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "medical_facility")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicalFacility.findAll", query = "SELECT m FROM MedicalFacility m"),
    @NamedQuery(name = "MedicalFacility.findByMedicalFacilityId", query = "SELECT m FROM MedicalFacility m WHERE m.medicalFacilityId = :medicalFacilityId"),
    @NamedQuery(name = "MedicalFacility.findByName", query = "SELECT m FROM MedicalFacility m WHERE m.name = :name")})
public class MedicalFacility implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "medical_facility_id")
    private Integer medicalFacilityId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "environtment_medical_facility", joinColumns = {
        @JoinColumn(name = "medical_facility_id", referencedColumnName = "medical_facility_id")}, inverseJoinColumns = {
        @JoinColumn(name = "property_environtment_id", referencedColumnName = "property_environtment_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public MedicalFacility() {
    }

    public MedicalFacility(Integer medicalFacilityId) {
        this.medicalFacilityId = medicalFacilityId;
    }

    public MedicalFacility(Integer medicalFacilityId, String name) {
        this.medicalFacilityId = medicalFacilityId;
        this.name = name;
    }

    public Integer getMedicalFacilityId() {
        return medicalFacilityId;
    }

    public void setMedicalFacilityId(Integer medicalFacilityId) {
        this.medicalFacilityId = medicalFacilityId;
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
        hash += (medicalFacilityId != null ? medicalFacilityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof MedicalFacility)) {
            return false;
        }
        MedicalFacility other = (MedicalFacility) object;
        if ((this.medicalFacilityId == null && other.medicalFacilityId != null) || (this.medicalFacilityId != null && !this.medicalFacilityId.equals(other.medicalFacilityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.MedicalFacility[ medicalFacilityId=" + medicalFacilityId + " ]";
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
        return getMedicalFacilityId();
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

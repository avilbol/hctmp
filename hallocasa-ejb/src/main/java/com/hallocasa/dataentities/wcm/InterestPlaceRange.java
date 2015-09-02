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
@Table(name = "interest_place_range")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InterestPlaceRange.findAll", query = "SELECT i FROM InterestPlaceRange i"),
    @NamedQuery(name = "InterestPlaceRange.findByInterestPlaceRangeId", query = "SELECT i FROM InterestPlaceRange i WHERE i.interestPlaceRangeId = :interestPlaceRangeId"),
    @NamedQuery(name = "InterestPlaceRange.findByName", query = "SELECT i FROM InterestPlaceRange i WHERE i.name = :name")})
public class InterestPlaceRange implements Serializable, TypeInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "interest_place_range_id")
    private Integer interestPlaceRangeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interestPlaceRange", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interestPlaceRange1", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interestPlaceRange2", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interestPlaceRange3", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interestPlaceRange4", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList4;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interestPlaceRange5", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList5;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interestPlaceRange6", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList6;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interestPlaceRange7", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList7;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interestPlaceRange8", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList8;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interestPlaceRange9", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList9;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interestPlaceRange10", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList10;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interestPlaceRange11", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList11;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interestPlaceRange12", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList12;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "interestPlaceRange13", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList13;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public InterestPlaceRange() {
    }

    public InterestPlaceRange(Integer interestPlaceRangeId) {
        this.interestPlaceRangeId = interestPlaceRangeId;
    }

    public InterestPlaceRange(Integer interestPlaceRangeId, String name) {
        this.interestPlaceRangeId = interestPlaceRangeId;
        this.name = name;
    }

    public Integer getInterestPlaceRangeId() {
        return interestPlaceRangeId;
    }

    public void setInterestPlaceRangeId(Integer interestPlaceRangeId) {
        this.interestPlaceRangeId = interestPlaceRangeId;
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

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList1() {
        return propertyEnvirontmentList1;
    }

    public void setPropertyEnvirontmentList1(List<PropertyEnvirontment> propertyEnvirontmentList1) {
        this.propertyEnvirontmentList1 = propertyEnvirontmentList1;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList2() {
        return propertyEnvirontmentList2;
    }

    public void setPropertyEnvirontmentList2(List<PropertyEnvirontment> propertyEnvirontmentList2) {
        this.propertyEnvirontmentList2 = propertyEnvirontmentList2;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList3() {
        return propertyEnvirontmentList3;
    }

    public void setPropertyEnvirontmentList3(List<PropertyEnvirontment> propertyEnvirontmentList3) {
        this.propertyEnvirontmentList3 = propertyEnvirontmentList3;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList4() {
        return propertyEnvirontmentList4;
    }

    public void setPropertyEnvirontmentList4(List<PropertyEnvirontment> propertyEnvirontmentList4) {
        this.propertyEnvirontmentList4 = propertyEnvirontmentList4;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList5() {
        return propertyEnvirontmentList5;
    }

    public void setPropertyEnvirontmentList5(List<PropertyEnvirontment> propertyEnvirontmentList5) {
        this.propertyEnvirontmentList5 = propertyEnvirontmentList5;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList6() {
        return propertyEnvirontmentList6;
    }

    public void setPropertyEnvirontmentList6(List<PropertyEnvirontment> propertyEnvirontmentList6) {
        this.propertyEnvirontmentList6 = propertyEnvirontmentList6;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList7() {
        return propertyEnvirontmentList7;
    }

    public void setPropertyEnvirontmentList7(List<PropertyEnvirontment> propertyEnvirontmentList7) {
        this.propertyEnvirontmentList7 = propertyEnvirontmentList7;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList8() {
        return propertyEnvirontmentList8;
    }

    public void setPropertyEnvirontmentList8(List<PropertyEnvirontment> propertyEnvirontmentList8) {
        this.propertyEnvirontmentList8 = propertyEnvirontmentList8;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList9() {
        return propertyEnvirontmentList9;
    }

    public void setPropertyEnvirontmentList9(List<PropertyEnvirontment> propertyEnvirontmentList9) {
        this.propertyEnvirontmentList9 = propertyEnvirontmentList9;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList10() {
        return propertyEnvirontmentList10;
    }

    public void setPropertyEnvirontmentList10(List<PropertyEnvirontment> propertyEnvirontmentList10) {
        this.propertyEnvirontmentList10 = propertyEnvirontmentList10;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList11() {
        return propertyEnvirontmentList11;
    }

    public void setPropertyEnvirontmentList11(List<PropertyEnvirontment> propertyEnvirontmentList11) {
        this.propertyEnvirontmentList11 = propertyEnvirontmentList11;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList12() {
        return propertyEnvirontmentList12;
    }

    public void setPropertyEnvirontmentList12(List<PropertyEnvirontment> propertyEnvirontmentList12) {
        this.propertyEnvirontmentList12 = propertyEnvirontmentList12;
    }

    @XmlTransient
    public List<PropertyEnvirontment> getPropertyEnvirontmentList13() {
        return propertyEnvirontmentList13;
    }

    public void setPropertyEnvirontmentList13(List<PropertyEnvirontment> propertyEnvirontmentList13) {
        this.propertyEnvirontmentList13 = propertyEnvirontmentList13;
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
        hash += (interestPlaceRangeId != null ? interestPlaceRangeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof InterestPlaceRange)) {
            return false;
        }
        InterestPlaceRange other = (InterestPlaceRange) object;
        if ((this.interestPlaceRangeId == null && other.interestPlaceRangeId != null) || (this.interestPlaceRangeId != null && !this.interestPlaceRangeId.equals(other.interestPlaceRangeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.InterestPlaceRange[ interestPlaceRangeId=" + interestPlaceRangeId + " ]";
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
        return getInterestPlaceRangeId();
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

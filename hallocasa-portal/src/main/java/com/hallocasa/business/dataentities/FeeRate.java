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
@Table(name = "fee_rate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeeRate.findAll", query = "SELECT f FROM FeeRate f"),
    @NamedQuery(name = "FeeRate.findByFeeRateId", query = "SELECT f FROM FeeRate f WHERE f.feeRateId = :feeRateId"),
    @NamedQuery(name = "FeeRate.findByName", query = "SELECT f FROM FeeRate f WHERE f.name = :name")})
public class FeeRate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fee_rate_id")
    private Integer feeRateId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feeRate", fetch = FetchType.LAZY)
    private List<Property> propertyList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translation;

    public FeeRate() {
    }

    public FeeRate(Integer feeRateId) {
        this.feeRateId = feeRateId;
    }

    public FeeRate(Integer feeRateId, String name) {
        this.feeRateId = feeRateId;
        this.name = name;
    }

    public Integer getFeeRateId() {
        return feeRateId;
    }

    public void setFeeRateId(Integer feeRateId) {
        this.feeRateId = feeRateId;
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
        hash += (feeRateId != null ? feeRateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof FeeRate)) {
            return false;
        }
        FeeRate other = (FeeRate) object;
        if ((this.feeRateId == null && other.feeRateId != null) || (this.feeRateId != null && !this.feeRateId.equals(other.feeRateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.FeeRate[ feeRateId=" + feeRateId + " ]";
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

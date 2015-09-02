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
@Table(name = "rain_period")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RainPeriod.findAll", query = "SELECT r FROM RainPeriod r"),
    @NamedQuery(name = "RainPeriod.findByRainPeriodId", query = "SELECT r FROM RainPeriod r WHERE r.rainPeriodId = :rainPeriodId"),
    @NamedQuery(name = "RainPeriod.findByName", query = "SELECT r FROM RainPeriod r WHERE r.name = :name")})
public class RainPeriod implements Serializable, TypeInterface {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rain_period_id")
    private Integer rainPeriodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rainPeriod", fetch = FetchType.LAZY)
    private List<PropertyEnvirontment> propertyEnvirontmentList;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translationName;

    public RainPeriod() {
    }

    public RainPeriod(Integer rainPeriodId) {
        this.rainPeriodId = rainPeriodId;
    }

    public RainPeriod(Integer rainPeriodId, String name) {
        this.rainPeriodId = rainPeriodId;
        this.name = name;
    }

    public Integer getRainPeriodId() {
        return rainPeriodId;
    }

    public void setRainPeriodId(Integer rainPeriodId) {
        this.rainPeriodId = rainPeriodId;
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
        hash += (rainPeriodId != null ? rainPeriodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof RainPeriod)) {
            return false;
        }
        RainPeriod other = (RainPeriod) object;
        if ((this.rainPeriodId == null && other.rainPeriodId != null) || (this.rainPeriodId != null && !this.rainPeriodId.equals(other.rainPeriodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.RainPeriod[ rainPeriodId=" + rainPeriodId + " ]";
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
        return getRainPeriodId();
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

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

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "socioeconomic_stratum")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SocioeconomicStratum.findAll", query = "SELECT s FROM SocioeconomicStratum s"),
    @NamedQuery(name = "SocioeconomicStratum.findBySocioeconomicStratumId", query = "SELECT s FROM SocioeconomicStratum s WHERE s.socioeconomicStratumId = :socioeconomicStratumId"),
    @NamedQuery(name = "SocioeconomicStratum.findByName", query = "SELECT s FROM SocioeconomicStratum s WHERE s.name = :name")})
public class SocioeconomicStratum implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "socioeconomic_stratum_id")
    private Integer socioeconomicStratumId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "name_translation_id", referencedColumnName = "translation_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Translation translation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "socioeconomicStratum", fetch = FetchType.LAZY)
    private List<Property> propertyList;

    public SocioeconomicStratum() {
    }

    public SocioeconomicStratum(Integer socioeconomicStratumId) {
        this.socioeconomicStratumId = socioeconomicStratumId;
    }

    public SocioeconomicStratum(Integer socioeconomicStratumId, String name) {
        this.socioeconomicStratumId = socioeconomicStratumId;
        this.name = name;
    }

    public Integer getSocioeconomicStratumId() {
        return socioeconomicStratumId;
    }

    public void setSocioeconomicStratumId(Integer socioeconomicStratumId) {
        this.socioeconomicStratumId = socioeconomicStratumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
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
        hash += (socioeconomicStratumId != null ? socioeconomicStratumId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SocioeconomicStratum)) {
            return false;
        }
        SocioeconomicStratum other = (SocioeconomicStratum) object;
        if ((this.socioeconomicStratumId == null && other.socioeconomicStratumId != null) || (this.socioeconomicStratumId != null && !this.socioeconomicStratumId.equals(other.socioeconomicStratumId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.SocioeconomicStratum[ socioeconomicStratumId=" + socioeconomicStratumId + " ]";
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

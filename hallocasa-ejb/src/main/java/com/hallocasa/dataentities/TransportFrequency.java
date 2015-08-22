/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.dataentities;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "transport_frequency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransportFrequency.findAll", query = "SELECT t FROM TransportFrequency t"),
    @NamedQuery(name = "TransportFrequency.findByTransoportFrequencyId", query = "SELECT t FROM TransportFrequency t WHERE t.transoportFrequencyId = :transoportFrequencyId")})
public class TransportFrequency implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "transoport_frequency_id")
    private Integer transoportFrequencyId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transportFrequency", fetch = FetchType.LAZY)
    private List<EnvirontmentPublicTransport> environtmentPublicTransportList;

    public TransportFrequency() {
    }

    public TransportFrequency(Integer transoportFrequencyId) {
        this.transoportFrequencyId = transoportFrequencyId;
    }

    public Integer getTransoportFrequencyId() {
        return transoportFrequencyId;
    }

    public void setTransoportFrequencyId(Integer transoportFrequencyId) {
        this.transoportFrequencyId = transoportFrequencyId;
    }

    @XmlTransient
    public List<EnvirontmentPublicTransport> getEnvirontmentPublicTransportList() {
        return environtmentPublicTransportList;
    }

    public void setEnvirontmentPublicTransportList(List<EnvirontmentPublicTransport> environtmentPublicTransportList) {
        this.environtmentPublicTransportList = environtmentPublicTransportList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transoportFrequencyId != null ? transoportFrequencyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof TransportFrequency)) {
            return false;
        }
        TransportFrequency other = (TransportFrequency) object;
        if ((this.transoportFrequencyId == null && other.transoportFrequencyId != null) || (this.transoportFrequencyId != null && !this.transoportFrequencyId.equals(other.transoportFrequencyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.TransportFrequency[ transoportFrequencyId=" + transoportFrequencyId + " ]";
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

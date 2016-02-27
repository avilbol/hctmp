/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.dataentities.wcm;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David Mantilla
 */
@Entity
@Table(name = "environtment_public_transport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnvirontmentPublicTransport.findAll", query = "SELECT e FROM EnvirontmentPublicTransport e"),
    @NamedQuery(name = "EnvirontmentPublicTransport.findByPropertyEnvirontmentId", query = "SELECT e FROM EnvirontmentPublicTransport e WHERE e.environtmentPublicTransportPK.propertyEnvirontmentId = :propertyEnvirontmentId"),
    @NamedQuery(name = "EnvirontmentPublicTransport.findByPublicTransportId", query = "SELECT e FROM EnvirontmentPublicTransport e WHERE e.environtmentPublicTransportPK.publicTransportId = :publicTransportId")})
public class EnvirontmentPublicTransport implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EnvirontmentPublicTransportPK environtmentPublicTransportPK;
    @JoinColumn(name = "public_transport_id", referencedColumnName = "public_transport_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PublicTransport publicTransport;
    @JoinColumn(name = "property_environtment_id", referencedColumnName = "property_environtment_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PropertyEnvirontment propertyEnvirontment;
    @JoinColumn(name = "transport_frequency_id", referencedColumnName = "transoport_frequency_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TransportFrequency transportFrequency;

    public EnvirontmentPublicTransport() {
    }

    public EnvirontmentPublicTransport(EnvirontmentPublicTransportPK environtmentPublicTransportPK) {
        this.environtmentPublicTransportPK = environtmentPublicTransportPK;
    }

    public EnvirontmentPublicTransport(int propertyEnvirontmentId, int publicTransportId) {
        this.environtmentPublicTransportPK = new EnvirontmentPublicTransportPK(propertyEnvirontmentId, publicTransportId);
    }

    public EnvirontmentPublicTransportPK getEnvirontmentPublicTransportPK() {
        return environtmentPublicTransportPK;
    }

    public void setEnvirontmentPublicTransportPK(EnvirontmentPublicTransportPK environtmentPublicTransportPK) {
        this.environtmentPublicTransportPK = environtmentPublicTransportPK;
    }

    public PublicTransport getPublicTransport() {
        return publicTransport;
    }

    public void setPublicTransport(PublicTransport publicTransport) {
        this.publicTransport = publicTransport;
    }

    public PropertyEnvirontment getPropertyEnvirontment() {
        return propertyEnvirontment;
    }

    public void setPropertyEnvirontment(PropertyEnvirontment propertyEnvirontment) {
        this.propertyEnvirontment = propertyEnvirontment;
    }

    public TransportFrequency getTransportFrequency() {
        return transportFrequency;
    }

    public void setTransportFrequency(TransportFrequency transportFrequency) {
        this.transportFrequency = transportFrequency;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (environtmentPublicTransportPK != null ? environtmentPublicTransportPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof EnvirontmentPublicTransport)) {
            return false;
        }
        EnvirontmentPublicTransport other = (EnvirontmentPublicTransport) object;
        if ((this.environtmentPublicTransportPK == null && other.environtmentPublicTransportPK != null) || (this.environtmentPublicTransportPK != null && !this.environtmentPublicTransportPK.equals(other.environtmentPublicTransportPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.EnvirontmentPublicTransport[ environtmentPublicTransportPK=" + environtmentPublicTransportPK + " ]";
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.dataentities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David Mantilla
 */
@Embeddable
public class EnvirontmentPublicTransportPK implements Serializable {

	private static final long serialVersionUID = 5551597938914611080L;
	@Basic(optional = false)
    @NotNull
    @Column(name = "property_environtment_id")
    private int propertyEnvirontmentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "public_transport_id")
    private int publicTransportId;

    public EnvirontmentPublicTransportPK() {
    }

    public EnvirontmentPublicTransportPK(int propertyEnvirontmentId, int publicTransportId) {
        this.propertyEnvirontmentId = propertyEnvirontmentId;
        this.publicTransportId = publicTransportId;
    }

    public int getPropertyEnvirontmentId() {
        return propertyEnvirontmentId;
    }

    public void setPropertyEnvirontmentId(int propertyEnvirontmentId) {
        this.propertyEnvirontmentId = propertyEnvirontmentId;
    }

    public int getPublicTransportId() {
        return publicTransportId;
    }

    public void setPublicTransportId(int publicTransportId) {
        this.publicTransportId = publicTransportId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) propertyEnvirontmentId;
        hash += (int) publicTransportId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof EnvirontmentPublicTransportPK)) {
            return false;
        }
        EnvirontmentPublicTransportPK other = (EnvirontmentPublicTransportPK) object;
        if (this.propertyEnvirontmentId != other.propertyEnvirontmentId) {
            return false;
        }
        if (this.publicTransportId != other.publicTransportId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hallocasa.model.dataentities.EnvirontmentPublicTransportPK[ propertyEnvirontmentId=" + propertyEnvirontmentId + ", publicTransportId=" + publicTransportId + " ]";
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

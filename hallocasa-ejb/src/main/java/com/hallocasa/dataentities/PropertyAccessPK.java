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
public class PropertyAccessPK implements Serializable {

	private static final long serialVersionUID = -4924816751135597570L;
	@Basic(optional = false)
	@NotNull
	@Column(name = "property_id")
	private int propertyId;
	@Basic(optional = false)
	@NotNull
	@Column(name = "access_type_id")
	private int accessTypeId;

	public PropertyAccessPK() {
	}

	public PropertyAccessPK(int propertyId, int accessTypeId) {
		this.propertyId = propertyId;
		this.accessTypeId = accessTypeId;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public int getAccessTypeId() {
		return accessTypeId;
	}

	public void setAccessTypeId(int accessTypeId) {
		this.accessTypeId = accessTypeId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) propertyId;
		hash += (int) accessTypeId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof PropertyAccessPK)) {
			return false;
		}
		PropertyAccessPK other = (PropertyAccessPK) object;
		if (this.propertyId != other.propertyId) {
			return false;
		}
		if (this.accessTypeId != other.accessTypeId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.hallocasa.model.dataentities.PropertyAccessPK[ propertyId="
				+ propertyId + ", accessTypeId=" + accessTypeId + " ]";
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.dataentities.wcm;

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
public class PropertyWaterPK implements Serializable {

	private static final long serialVersionUID = -5276507643210606166L;
	@Basic(optional = false)
	@NotNull
	@Column(name = "property_id")
	private int propertyId;
	@Basic(optional = false)
	@NotNull
	@Column(name = "water_type_id")
	private int waterTypeId;

	public PropertyWaterPK() {
	}

	public PropertyWaterPK(int propertyId, int waterTypeId) {
		this.propertyId = propertyId;
		this.waterTypeId = waterTypeId;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public int getWaterTypeId() {
		return waterTypeId;
	}

	public void setWaterTypeId(int waterTypeId) {
		this.waterTypeId = waterTypeId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) propertyId;
		hash += (int) waterTypeId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof PropertyWaterPK)) {
			return false;
		}
		PropertyWaterPK other = (PropertyWaterPK) object;
		if (this.propertyId != other.propertyId) {
			return false;
		}
		if (this.waterTypeId != other.waterTypeId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.hallocasa.model.dataentities.PropertyWaterPK[ propertyId="
				+ propertyId + ", waterTypeId=" + waterTypeId + " ]";
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

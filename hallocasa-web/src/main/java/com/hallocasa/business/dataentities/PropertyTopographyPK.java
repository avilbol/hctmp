/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hallocasa.business.dataentities;

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
public class PropertyTopographyPK implements Serializable {

	private static final long serialVersionUID = -3676459059509004834L;
	@Basic(optional = false)
	@NotNull
	@Column(name = "property_id")
	private int propertyId;
	@Basic(optional = false)
	@NotNull
	@Column(name = "topography_id")
	private int topographyId;

	public PropertyTopographyPK() {
	}

	public PropertyTopographyPK(int propertyId, int topographyId) {
		this.propertyId = propertyId;
		this.topographyId = topographyId;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public int getTopographyId() {
		return topographyId;
	}

	public void setTopographyId(int topographyId) {
		this.topographyId = topographyId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) propertyId;
		hash += (int) topographyId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof PropertyTopographyPK)) {
			return false;
		}
		PropertyTopographyPK other = (PropertyTopographyPK) object;
		if (this.propertyId != other.propertyId) {
			return false;
		}
		if (this.topographyId != other.topographyId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.hallocasa.model.dataentities.PropertyTopographyPK[ propertyId="
				+ propertyId + ", topographyId=" + topographyId + " ]";
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

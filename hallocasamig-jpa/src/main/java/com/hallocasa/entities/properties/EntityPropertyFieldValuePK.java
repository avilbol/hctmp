package com.hallocasa.entities.properties;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Composite key for entity {@link PropertyFieldValue}
 * @author avillamil
 */
@Embeddable
public class EntityPropertyFieldValuePK implements Serializable{

	private static final long serialVersionUID = -704830833445574522L;

	@Column(name = "property_field_id")
	private Integer propertyFieldId;
	
	@Column(name = "property_id")
	private String propertyId;
	
	public Integer getPropertyFieldId() {
		return propertyFieldId;
	}

	public void setPropertyFieldId(Integer propertyFieldId) {
		this.propertyFieldId = propertyFieldId;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((propertyFieldId == null) ? 0 : propertyFieldId.hashCode());
		result = prime * result + ((propertyId == null) ? 0 : propertyId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityPropertyFieldValuePK other = (EntityPropertyFieldValuePK) obj;
		if (propertyFieldId == null) {
			if (other.propertyFieldId != null)
				return false;
		} else if (!propertyFieldId.equals(other.propertyFieldId))
			return false;
		if (propertyId == null) {
			if (other.propertyId != null)
				return false;
		} else if (!propertyId.equals(other.propertyId))
			return false;
		return true;
	}
}
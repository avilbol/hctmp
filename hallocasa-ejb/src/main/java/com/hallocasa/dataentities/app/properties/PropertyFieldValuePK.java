package com.hallocasa.dataentities.app.properties;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PropertyFieldValuePK {

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
}

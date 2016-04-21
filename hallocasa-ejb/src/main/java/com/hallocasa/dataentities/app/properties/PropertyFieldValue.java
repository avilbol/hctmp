package com.hallocasa.dataentities.app.properties;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This entity represents the value of a property field in some property
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_field_value")
public class PropertyFieldValue {

	@EmbeddedId
	private PropertyFieldValuePK propertyFieldValuePK;
	
	@JoinColumn(name = "property_field_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private PropertyField propertyField;

	@JoinColumn(name = "property_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Property property;
	
	@Column(name="property_value")
	private String value;
	
	public PropertyFieldValuePK getPropertyFieldValuePK() {
		return propertyFieldValuePK;
	}

	public void setPropertyFieldValuePK(PropertyFieldValuePK propertyFieldValuePK) {
		this.propertyFieldValuePK = propertyFieldValuePK;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

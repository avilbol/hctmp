package com.hallocasa.dataentities.app.properties;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
	
	@MapsId("propertyFieldId")
	@JoinColumn(name = "property_field_id", referencedColumnName = "id")
    @ManyToOne
	private PropertyField propertyField;

	@MapsId("propertyId")
	@JoinColumn(name = "property_id", referencedColumnName = "property_id")
    @ManyToOne
	private Property property;
	
	@Column(name="property_value")
	private String value;
	
	public PropertyField getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(PropertyField propertyField) {
		this.propertyField = propertyField;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

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

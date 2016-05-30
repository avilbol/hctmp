package com.hallocasa.dataentities.app.properties;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * This entity represents the value of a property field in some property
 * 
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_field_value")
@NamedQueries({
		@NamedQuery(name = PropertyFieldValue.QUERY_DELETE_BY_PROPERTY_ID, 
				query = "delete from PropertyFieldValue pfv where pfv.property = ?1")})
public class PropertyFieldValue {

	/**
	 * Query that delete property register from the value data (table
	 * property_field_value), filtering by its property id
	 */
	public static final String QUERY_DELETE_BY_PROPERTY_ID = "PropertyFieldValue.deleteByPropertyId";

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

	@Column(name = "property_value")
	private String value;

	public static PropertyFieldValue loadInstance(String propertyId, Integer propertyFieldId, String value) {
		PropertyFieldValue pfv = new PropertyFieldValue();
		pfv.setProperty(new Property());
		pfv.getProperty().setId(propertyId);
		pfv.setPropertyField(new PropertyField());
		pfv.getPropertyField().setId(propertyFieldId);
		pfv.setPropertyFieldValuePK(new PropertyFieldValuePK());
		pfv.getPropertyFieldValuePK().setPropertyId(propertyId);
		pfv.getPropertyFieldValuePK().setPropertyFieldId(propertyFieldId);
		pfv.setValue(value);
		return pfv;
	}

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

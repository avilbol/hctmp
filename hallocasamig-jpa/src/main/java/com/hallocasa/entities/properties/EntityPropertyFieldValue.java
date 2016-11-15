package com.hallocasa.entities.properties;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

/**
 * This entity represents a type of a value of property field
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_field_type")
public class EntityPropertyFieldValue implements HallocasaEntity {

	@EmbeddedId
	private EntityPropertyFieldValuePK propertyFieldValuePK;

	@MapsId("propertyFieldId")
	@JoinColumn(name = "property_field_id", referencedColumnName = "id")
	@ManyToOne
	private EntityPropertyField propertyField;

	@MapsId("propertyId")
	@JoinColumn(name = "property_id", referencedColumnName = "property_id")
	@ManyToOne
	private EntityProperty property;
	
	@Column(name = "identifier")
	private String identifier;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "data1")
	private String data1;
	
	@Column(name = "data2")
	private String data2;
	
	@Column(name = "data3")
	private String data3;

	public EntityPropertyFieldValuePK getPropertyFieldValuePK() {
		return propertyFieldValuePK;
	}

	public void setPropertyFieldValuePK(EntityPropertyFieldValuePK propertyFieldValuePK) {
		this.propertyFieldValuePK = propertyFieldValuePK;
	}

	public EntityPropertyField getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(EntityPropertyField propertyField) {
		this.propertyField = propertyField;
	}

	public EntityProperty getProperty() {
		return property;
	}

	public void setProperty(EntityProperty property) {
		this.property = property;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public String getData3() {
		return data3;
	}

	public void setData3(String data3) {
		this.data3 = data3;
	}
}

package com.hallocasa.entities.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


import com.hallocasa.entities.i.HallocasaEntity;



/**
 * This entity represents a type of a value of property field
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_field_value")
@NamedQueries({
	@NamedQuery(name = EntityPropertyFieldValue.QUERY_FIND_BASIC_IN, query = "select pfv from "
			+ "EntityPropertyFieldValue pfv where pfv.property.id IN ?1 AND "
			+ "pfv.propertyField.basic = TRUE")})
public class EntityPropertyFieldValue implements HallocasaEntity {

	public static final String QUERY_FIND_BASIC_IN = "EntityPropertyFieldValue.QueryFindBasicIn";
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JoinColumn(name = "property_field_id", referencedColumnName = "id")
	@ManyToOne
	private EntityPropertyField propertyField;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "EntityPropertyFieldValue [identifier=" + identifier + ", text=" + text + ", data1=" + data1 + ", data2="
				+ data2 + ", data3=" + data3 + "]";
	}
}

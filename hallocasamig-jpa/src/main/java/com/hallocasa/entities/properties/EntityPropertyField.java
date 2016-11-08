package com.hallocasa.entities.properties;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hallocasa.persistence.converters.HcBooleanConverter;

/**
 * This entity represents a field of a property
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_field")
public class EntityPropertyField {

	/**
	 * Property field identifier
	 */
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;

	@Convert(converter = HcBooleanConverter.class)
	@Column(name="basic")
	private Boolean basic;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getBasic() {
		return basic;
	}

	public void setBasic(Boolean basic) {
		this.basic = basic;
	}
}

package com.hallocasa.dataentities.app.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This entity represents a set of common property types
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_field")
public class PropertyTypeGroup {

	/**
	 * Property type group identifier
	 */
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name = "name")
	private String name;

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
}

package com.hallocasa.dataentities.app.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This entity represents a possible location of a property
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_location")
public class PropertyLocation {

	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
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

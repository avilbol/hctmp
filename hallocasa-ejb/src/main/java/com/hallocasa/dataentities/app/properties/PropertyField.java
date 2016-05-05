package com.hallocasa.dataentities.app.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This entity represents a field of a property
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_field")
public class PropertyField {

	/**
	 * Property field identifier
	 */
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;

	@Column(name="basic")
	private Integer basic;
	
	
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

	public Integer getBasic() {
		return basic;
	}

	public void setBasic(Integer basic) {
		this.basic = basic;
	}
}

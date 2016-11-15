package com.hallocasa.entities.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

/**
 * This entity represents a group for a number of property types
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_type_group")
public class EntityPropertyTypeGroup implements HallocasaEntity {

	/**
	 * Property type group identifier
	 */
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
}

package com.hallocasa.entities.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

/**
 * This entity represents a type property
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_type")
public class EntityPropertyType implements HallocasaEntity {

	/**
	 * Property type identifier
	 */
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="lang")
	private String lang;
}

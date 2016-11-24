package com.hallocasa.entities.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

/**
 * This entity represents a location of a property
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_location")
@NamedQueries({
	@NamedQuery(name = EntityPropertyLocation.QUERY_FIND_ALL, 
			query = "select pl from EntityPropertyLocation pl") })
public class EntityPropertyLocation implements HallocasaEntity {

	public static final String QUERY_FIND_ALL = "EntityPropertyLocation.findAll";
	
	/**
	 * Property proposal identifier
	 */
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="lang")
	private String lang;

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

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
}

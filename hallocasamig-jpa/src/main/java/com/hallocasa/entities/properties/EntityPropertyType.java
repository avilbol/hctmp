package com.hallocasa.entities.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

/**
 * This entity represents a type property
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_type")
@NamedQueries({
	@NamedQuery(name = EntityPropertyType.QUERY_FIND_ALL, 
			query = "select pt from EntityPropertyType pt") })
public class EntityPropertyType implements HallocasaEntity {

	public static final String QUERY_FIND_ALL = "EntityPropertyType.findAll";
	
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
	
	@JoinColumn(name = "group_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityPropertyTypeGroup group;

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

	public EntityPropertyTypeGroup getGroup() {
		return group;
	}

	public void setGroup(EntityPropertyTypeGroup group) {
		this.group = group;
	}
}

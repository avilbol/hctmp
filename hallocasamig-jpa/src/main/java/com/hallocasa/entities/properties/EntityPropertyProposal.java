package com.hallocasa.entities.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

/**
 * This entity represents a proposal of a property
 * @author Alexander Villamil
 */
@Entity
@Table(name = "property_proposal")
@NamedQueries({
	@NamedQuery(name = EntityPropertyProposal.QUERY_FIND_ALL, 
			query = "select pp from EntityPropertyProposal pp") })
public class EntityPropertyProposal implements HallocasaEntity {

	public static final String QUERY_FIND_ALL = "EntityPropertyProposal.findAll";
	
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

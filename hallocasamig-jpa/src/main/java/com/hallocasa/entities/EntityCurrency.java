package com.hallocasa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

/**
 * This entity represents a currency
 * @author avillamil
 */
@Entity
@Table(name = "currency")
@NamedQueries({
	@NamedQuery(name = EntityCurrency.QUERY_FIND_ALL, 
			query = "select c from EntityCurrency c") })
public class EntityCurrency implements HallocasaEntity {

	public static final String QUERY_FIND_ALL = "EntityCurrency.findAll";
	
	/**
	 * Country identifier
	 */
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="lang")
	private String lang;
	
	@Column(name="abbreviation")
	private String abbreviation;
	
	@Column(name="prefix")
	private String prefix;

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

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}

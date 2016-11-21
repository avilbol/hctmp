package com.hallocasa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

/**
 * Entity for telephone prefix (for countries)
 * 
 * @author Alexander Villamil
 */
@Entity
@Table(name = "telephone_prefix")
@NamedQueries({ @NamedQuery(name = EntityCountryTelephonePrefix.QUERY_FIND_ALL, 
	query = "select t from EntityCountryTelephonePrefix t") })
public class EntityCountryTelephonePrefix implements Serializable, HallocasaEntity {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -5551016884071020421L;

	public static final String QUERY_FIND_ALL = "CountryTelephonePrefix.findAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "prefix")
	private Integer prefix;

	@Column(name = "description")
	private String name;

	@Column(name = "lang")
	private String lang;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrefix() {
		return prefix;
	}

	public void setPrefix(Integer prefix) {
		this.prefix = prefix;
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

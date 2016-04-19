package com.hallocasa.dataentities.app;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.commons.vo.interfaces.HallocasaEntity;

/**
 * Entity for telephone prefix (for countries)
 * 
 * @author Alexander Villamil
 */
@Entity
@Table(name = "telephone_prefix")
@NamedQueries({ @NamedQuery(name = CountryTelephonePrefix.QUERY_FIND_ALL, 
	query = "select t from CountryTelephonePrefix t") })
public class CountryTelephonePrefix implements Serializable, HallocasaEntity {

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
}

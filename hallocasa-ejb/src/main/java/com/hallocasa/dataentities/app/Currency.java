package com.hallocasa.dataentities.app;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.commons.vo.interfaces.HallocasaEntity;


/**
 * This entity represents a currency type element
 * @author Alexander Villamil
 */
@Entity
@Table(name = "currency")
@NamedQueries({
    @NamedQuery(name= Currency.QUERY_FIND_ALL,
            query = "select c from Currency c")
})
public class Currency implements Serializable, HallocasaEntity{

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -1829637939247687877L;


	/**
	 * Query for search every property type
	 */
	public static final String QUERY_FIND_ALL = "Currency.findAll";
	
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "abbreviation")
	private String abbreviation;
	
	@Column(name = "prefix")
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

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}

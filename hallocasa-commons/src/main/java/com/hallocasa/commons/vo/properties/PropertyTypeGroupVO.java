package com.hallocasa.commons.vo.properties;

import java.io.Serializable;

/**
 * Value object for groups that surround several property types
 * @author avillamil
 *
 */
public class PropertyTypeGroupVO implements Serializable {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = 4120605687753609094L;

	/**
	 * Property type group identifier
	 */
	private Integer id;

	private String name;

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
	
}

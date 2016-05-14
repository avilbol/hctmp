package com.hallocasa.commons.vo;

import java.io.Serializable;

/**
 * Value Object for currency representation
 * @author avillamil
 *
 */
public class CurrencyVO  implements Serializable{

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = -5728762206380375694L;

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

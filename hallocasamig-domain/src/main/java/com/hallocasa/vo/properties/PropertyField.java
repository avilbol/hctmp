package com.hallocasa.vo.properties;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * This vo represents a field of a property
 * @author Alexander Villamil
 */
public class PropertyField implements ValueObject, Serializable {
	
	private static final long serialVersionUID = -4573427797265653083L;
	private Integer id;
	private String name;
	private Integer basic;
	
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

	public Integer getBasic() {
		return basic;
	}

	public void setBasic(Integer basic) {
		this.basic = basic;
	}
}
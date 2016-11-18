package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class State implements ValueObject, Serializable {

	private static final long serialVersionUID = -1562717547540000520L;

	private Integer id;
	private String name;
	private Country country;
	
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
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
}

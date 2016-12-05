package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * Vo representing an application language allowed by interface
 * @author avillamil
 */
public class Language implements ValueObject, Serializable {

	private static final long serialVersionUID = 4030905280780064192L;
	
	private Integer id;
	
	private String name;

	private String locale;
	
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

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
}

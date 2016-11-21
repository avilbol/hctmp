package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

/**
 * Value object representing a currency
 * @author avillamil
 */
public class Currency implements ValueObject, Serializable{

	private static final long serialVersionUID = 9011446229724391179L;

	private Integer id;
	
	private String name;
	
	private String lang;
	
	private String abbreviation;
	
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

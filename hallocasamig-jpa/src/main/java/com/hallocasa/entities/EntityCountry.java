package com.hallocasa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

/**
 * This entity represents a country
 * @author Alexander Villamil
 */
@Entity
@Table(name = "country")
public class EntityCountry implements HallocasaEntity {

	/**
	 * Country identifier
	 */
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="country_name")
	private String name;
	
	@Column(name="java_code")
	private String javaCode;
	
	@Column(name="lang")
	private String lang;

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

	public String getJavaCode() {
		return javaCode;
	}

	public void setJavaCode(String javaCode) {
		this.javaCode = javaCode;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
}

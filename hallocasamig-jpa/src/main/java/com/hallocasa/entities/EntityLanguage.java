package com.hallocasa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

/**
 * Entity of a language
 * @author avillamil
 */
@Entity
@Table(name = "lang")
@NamedQueries({
	@NamedQuery(name = EntityLanguage.QUERY_FIND_ALL, 
			query = "select l from EntityLanguage l")})
public class EntityLanguage implements HallocasaEntity{

	public static final String QUERY_FIND_ALL = "EntityLanguage.QueryFindAll";
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="locale")
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

	@Override
	public String toString() {
		return "EntityLanguage [id=" + id + ", name=" + name + ", locale=" + locale + "]";
	}
}

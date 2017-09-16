package com.hallocasa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.composedkeys.EntityLocaleEntryPK;
import com.hallocasa.entities.i.HallocasaEntity;

/**
 * Entity of a language
 * @author avillamil
 */
@Entity
@Table(name = "locale_entry")
@IdClass(EntityLocaleEntryPK.class)
@NamedQueries({
	@NamedQuery(name = EntityLocaleEntry.QUERY_FIND_BY_LOCALE, 
			query = "select l from EntityLocaleEntry l WHERE l.locale = ?1"),
	@NamedQuery(name = EntityLocaleEntry.QUERY_FIND_BY_PNEMONIC, 
			query = "select l from EntityLocaleEntry l WHERE l.pnemonic = ?1"),
	@NamedQuery(name = EntityLocaleEntry.QUERY_FIND_ALL, 
			query = "select l from EntityLocaleEntry l"),
	@NamedQuery(name = EntityLocaleEntry.QUERY_DELETE_BY_PNEMONIC_ITEM, 
			query = "delete from EntityLocaleEntry l WHERE l.pnemonic = ?1")})
public class EntityLocaleEntry implements HallocasaEntity{

	public static final String QUERY_FIND_BY_LOCALE = "EntityLocaleEntry.QueryFindByLocale";
	public static final String QUERY_FIND_BY_PNEMONIC = "EntityLocaleEntry.QueryFindByPnemonic";
	public static final String QUERY_DELETE_BY_PNEMONIC_ITEM = "EntityLocaleEntry.QueryDeleteByPnemonicItem";
	public static final String QUERY_FIND_ALL = "EntityLocaleEntry.QueryFindAll";
	
	@Id
	@Column(name="pnemonic")
	private String pnemonic;
	
	@Id
	@Column(name="locale")
	private String locale;
	
	@Column(name="description")
	private String description;
	
	@Column(name="lang_value")
	private String langValue;
	
	public String getPnemonic() {
		return pnemonic;
	}
	
	public void setPnemonic(String pnemonic) {
		this.pnemonic = pnemonic;
	}
	
	public String getLocale() {
		return locale;
	}
	
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLangValue() {
		return langValue;
	}
	
	public void setLangValue(String langValue) {
		this.langValue = langValue;
	}

	@Override
	public String toString() {
		return "EntityLocaleEntry [pnemonic=" + pnemonic + ", locale=" + locale + ", description=" + description
				+ ", langValue=" + langValue + "]";
	}
}

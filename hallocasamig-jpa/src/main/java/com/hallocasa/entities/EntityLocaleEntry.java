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
	@NamedQuery(name = EntityLocaleEntry.QUERY_FIND_BY_LOCALE, 
			query = "select l from EntityLocaleEntry l WHERE l.locale = ?1"),
	@NamedQuery(name = EntityLocaleEntry.QUERY_FIND_BY_LOCALE_AND_PNEMONIC_LIST, 
			query = "select l from EntityLocaleEntry l WHERE l.locale = ?1 	AND l.pnemonic IN ?2"),
	@NamedQuery(name = EntityLocaleEntry.QUERY_FIND_BY_LOCALE_AND_PNEMONIC_ITEM, 
		query = "select l from EntityLocaleEntry l WHERE l.locale = ?1 	AND l.pnemonic = ?2")})
public class EntityLocaleEntry implements HallocasaEntity{

	public static final String QUERY_FIND_BY_LOCALE = "EntityLocaleEntry.QueryFindByLocale";
	public static final String QUERY_FIND_BY_LOCALE_AND_PNEMONIC_LIST = "EntityLocaleEntry.QueryFindByLocaleAndPnemonicList";
	public static final String QUERY_FIND_BY_LOCALE_AND_PNEMONIC_ITEM = "EntityLocaleEntry.QueryFindByLocaleAndPnemonicItem";
	
	@Id
	@Column(name="pnemonic")
	private String pnemonic;
	
	@Column(name="locale")
	private String locale;
	
	@Column(name="lang_key")
	private String langKey;
	
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
	
	public String getLangKey() {
		return langKey;
	}
	
	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}
	
	public String getLangValue() {
		return langValue;
	}
	
	public void setLangValue(String langValue) {
		this.langValue = langValue;
	}

	@Override
	public String toString() {
		return "EntityLocaleEntry [pnemonic=" + pnemonic + ", locale=" + locale + ", langKey=" + langKey
				+ ", langValue=" + langValue + "]";
	}
}

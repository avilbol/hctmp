package com.hallocasa.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.composedkeys.EntityUserLanguagePK;
import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.entities.properties.EntityLanguage;
import com.hallocasa.persistence.converters.HcBooleanConverter;

/**
 * Entity used to fill all data registered by user in some language choosed by
 */
@Entity
@Table(name = "user_language")
@NamedQueries({
		@NamedQuery(name = EntityUserLanguage.QUERY_FIND_BY_ID_LIST, 
				query = "select u from EntityUserLanguage u WHERE u.user.id IN ?1") })
public class EntityUserLanguage implements HallocasaEntity{

	public static final String QUERY_FIND_BY_ID_LIST = "EntityUserLanguage.FindByIdList";
	
	@EmbeddedId
	private EntityUserLanguagePK entityUserLanguagePK;

	@MapsId("userId")
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne
	private EntityUser user;

	@MapsId("languageId")
	@JoinColumn(name = "language_id", referencedColumnName = "id")
	@ManyToOne
	private EntityLanguage language;

	@Convert(converter = HcBooleanConverter.class)
	@Column(name = "is_main_language")
	private Boolean isMainLanguage;

	public EntityUser getUser() {
		return user;
	}

	public void setUser(EntityUser user) {
		this.user = user;
	}

	public EntityLanguage getLanguage() {
		return language;
	}

	public void setLanguage(EntityLanguage language) {
		this.language = language;
	}

	public Boolean getIsMainLanguage() {
		return isMainLanguage;
	}

	public void setIsMainLanguage(Boolean isMainLanguage) {
		this.isMainLanguage = isMainLanguage;
	}

	public EntityUserLanguagePK getEntityUserLanguagePK() {
		return entityUserLanguagePK;
	}

	public void setEntityUserLanguagePK(EntityUserLanguagePK entityUserLanguagePK) {
		this.entityUserLanguagePK = entityUserLanguagePK;
	}
}

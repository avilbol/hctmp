package com.hallocasa.entities;

import javax.persistence.Column;
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

/**
 * Entity used to fill user descriptions registered by user in some language
 * choosed by
 */
@Entity
@Table(name = "user_description")
@NamedQueries({
		@NamedQuery(name = EntityUserDescription.QUERY_FIND_BY_ID_LIST, 
				query = "select udesc from EntityUserDescription udesc,"
						+ " EntityUserLanguage ulang"
						+ " WHERE ulang.language.id = udesc.language.id"
						+ " AND ulang.user.id = udesc.user.id"
						+ " AND udesc.user.id IN ?1"
						+ " AND ulang.isMainLanguage = TRUE") })
public class EntityUserDescription implements HallocasaEntity {

	public static final String QUERY_FIND_BY_ID_LIST = "EntityUserDescription.FindByIdList";

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

	@Column(name = "value")
	private String value;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public EntityUserLanguagePK getEntityUserLanguagePK() {
		return entityUserLanguagePK;
	}

	public void setEntityUserLanguagePK(EntityUserLanguagePK entityUserLanguagePK) {
		this.entityUserLanguagePK = entityUserLanguagePK;
	}
}

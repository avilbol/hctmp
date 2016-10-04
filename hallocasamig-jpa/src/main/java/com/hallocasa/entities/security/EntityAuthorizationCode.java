package com.hallocasa.entities.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.utils.constants.Tables;

/**
 * Table entity "authorization_code"
 */
@Entity
@Table(name = Tables.AUTHORIZATION_CODE)
@NamedQueries({
		@NamedQuery(name = EntityAuthorizationCode.QUERY_FIND_BY_AUTHCODE, 
				query = "select a from EntityAuthorizationCode a where a.authCode = ?1"),
		@NamedQuery(name = EntityAuthorizationCode.QUERY_FIND_BY_IDENTIFIER, 
				query = "select a from EntityAuthorizationCode a where a.authCode = ?1 and a.clientId = ?2"),
		@NamedQuery(name = EntityAuthorizationCode.QUERY_FIND_BY_CLIENT_ID, 
				query = "select a from EntityAuthorizationCode a where a.clientId = ?1") })
public class EntityAuthorizationCode implements HallocasaEntity {

	public static final String QUERY_FIND_BY_AUTHCODE = "EntityAuthorizationCode.findByAuthCode";

	public static final String QUERY_FIND_BY_IDENTIFIER = "EntityAuthorizationCode.findByIdentifier";

	public static final String QUERY_FIND_BY_CLIENT_ID = "EntityAuthorizationCode.findByClientId";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "client_id")
	private String clientId;

	@Column(name = "auth_code")
	private String authCode;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
}

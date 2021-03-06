package com.hallocasa.entities.security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hallocasa.entities.i.HallocasaEntity;
import com.hallocasa.utils.constants.Tables;

/**
 * Table entity "authorization_code"
 */
@Entity
@Table(name = Tables.SECURITY_TOKEN)
@NamedQueries({
		@NamedQuery(name = EntitySecurityToken.QUERY_FIND_BY_TOKEN_VALUE, 
				query = "select s from EntitySecurityToken s where s.tokenValue = ?1"),
		@NamedQuery(name = EntitySecurityToken.QUERY_DELETE_BY_TOKEN_VALUE, 
				query = "delete from EntitySecurityToken s where s.tokenValue = ?1")})
public class EntitySecurityToken implements HallocasaEntity{

	public static final String QUERY_FIND_BY_TOKEN_VALUE = "EntitySecurityToken.queryFindByTokenValue";
	public static final String QUERY_DELETE_BY_TOKEN_VALUE = "EntitySecurityToken.queryDeleteByTokenValue";
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "token_value")
	private String tokenValue;
	
	@Column(name = "registered")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date registered;
	
	@Column(name = "expires_in")
	private long expiresIn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}
}

package com.hallocasa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

@Entity
@Table(name = "state")
@NamedQueries({
	@NamedQuery(name = EntityState.QUERY_FIND_BY_COUNTRY_ID, query = "select s from EntityState s where s.country.id = ?1"),
	@NamedQuery(name = EntityState.QUERY_FIND_BY_COUNTRIES_ID, query = "select s from EntityState s where s.country.id IN ?1") })
public class EntityState implements HallocasaEntity {

	public static final String QUERY_FIND_BY_COUNTRY_ID = "EntityCity.findByCountryId";

	public static final String QUERY_FIND_BY_COUNTRIES_ID = "EntityCity.findByCountriesId";
	
	/**
	 * State identifier
	 */
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="state_name")
	private String name;
	
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityCountry country;

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

	public EntityCountry getCountry() {
		return country;
	}

	public void setCountry(EntityCountry country) {
		this.country = country;
	}
}

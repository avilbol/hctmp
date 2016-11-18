package com.hallocasa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hallocasa.entities.i.HallocasaEntity;

@Entity
@Table(name = "state")
public class EntityState implements HallocasaEntity {

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

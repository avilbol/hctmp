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
@Table(name = "city")
@NamedQueries({
		@NamedQuery(name = EntityCity.QUERY_FIND_BY_STATE_ID, query = "select c from EntityCity c where c.state.id = ?1"),
		@NamedQuery(name = EntityCity.QUERY_FIND_BY_STATES_ID, query = "select c from EntityCity c where c.state.id IN ?1 ORDER BY c.name") })
public class EntityCity implements HallocasaEntity {

	public static final String QUERY_FIND_BY_STATE_ID = "EntityCity.findByStateId";

	public static final String QUERY_FIND_BY_STATES_ID = "EntityCity.findByStatesId";

	/**
	 * City identifier
	 */
	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "city_name")
	private String name;

	@JoinColumn(name = "state_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private EntityState state;

	@Column(name = "default_lat_coordinate")
	private Double defaultLatCoordinate;

	@Column(name = "default_lng_coordinate")
	private Double defaultLngCoordinate;

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

	public EntityState getState() {
		return state;
	}

	public void setState(EntityState state) {
		this.state = state;
	}

	public Double getDefaultLatCoordinate() {
		return defaultLatCoordinate;
	}

	public void setDefaultLatCoordinate(Double defaultLatCoordinate) {
		this.defaultLatCoordinate = defaultLatCoordinate;
	}

	public Double getDefaultLngCoordinate() {
		return defaultLngCoordinate;
	}

	public void setDefaultLngCoordinate(Double defaultLngCoordinate) {
		this.defaultLngCoordinate = defaultLngCoordinate;
	}
}

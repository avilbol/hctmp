package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class City implements ValueObject, Serializable {

	private Integer id;
	private String name;
	private Double defaultLatCoordinate;
	private Double defaultLngCoordinate;
	private Integer defaultZoom;
	private Integer stateId;
	private Integer countryId;
	
	private static final long serialVersionUID = -4114890974708798918L;

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

	public Integer getDefaultZoom() {
		return defaultZoom;
	}

	public void setDefaultZoom(Integer defaultZoom) {
		this.defaultZoom = defaultZoom;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
}

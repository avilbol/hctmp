package com.hallocasa.vo;

import java.io.Serializable;

import com.hallocasa.vo.i.ValueObject;

public class City implements ValueObject, Serializable {

	private Integer id;
	private String name;
	private State state;
	private Double defaultLatCoordinate;
	private Double defaultLngCoordinate;
	
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
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

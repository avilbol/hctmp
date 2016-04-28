package com.hallocasa.commons.vo.properties;

import com.hallocasa.commons.vo.CityVO;
import com.hallocasa.commons.vo.Coordinate;
import com.hallocasa.commons.vo.StateVO;

public class PropertyLocationInfo {

	private StateVO state;
	
	private CityVO city;
	
	private String address;
	
	private Coordinate coordinate;

	public StateVO getState() {
		return state;
	}

	public void setState(StateVO state) {
		this.state = state;
	}

	public CityVO getCity() {
		return city;
	}

	public void setCity(CityVO city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
}

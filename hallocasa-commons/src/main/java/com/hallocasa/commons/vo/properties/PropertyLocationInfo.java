package com.hallocasa.commons.vo.properties;

import com.hallocasa.commons.annotations.PropertyFieldValueParser;
import com.hallocasa.commons.vo.CityVO;
import com.hallocasa.commons.vo.Coordinate;
import com.hallocasa.commons.vo.StateVO;
import static com.hallocasa.commons.constants.PropertyConstants.*;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PropertyLocationInfo implements Serializable {

	@PropertyFieldValueParser(id = STATE_FIELD, methodToExecute = "parseToState")
	private StateVO state;
	
	@PropertyFieldValueParser(id = CITY_FIELD, methodToExecute = "parseToCity")
	private CityVO city;
	
	@PropertyFieldValueParser(id = ADDRESS_FIELD, methodToExecute = "parseToSingleText")
	private String address;
	
	@PropertyFieldValueParser(id = LONGITUDE_FIELD, methodToExecute = "parseToCoordinate")
	private Coordinate longitude;
	
	@PropertyFieldValueParser(id = LATITUDE_FIELD, methodToExecute = "parseToCoordinate")
	private Coordinate latitude;

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

}

package com.hallocasa.vo.hcfilter.properties;

import java.io.Serializable;

import com.hallocasa.vo.Country;
import com.hallocasa.vo.i.ValueObject;

public class PropertyKey implements ValueObject, Serializable {

	private static final long serialVersionUID = -8762506905849463829L;

	private PropertyType propertyType;
	
	private PropertyLocation propertyLocation;
	
	private PropertyProposal propertyProposal;
	
	private Country country;
	
	private String name;

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public PropertyLocation getPropertyLocation() {
		return propertyLocation;
	}

	public void setPropertyLocation(PropertyLocation propertyLocation) {
		this.propertyLocation = propertyLocation;
	}

	public PropertyProposal getPropertyProposal() {
		return propertyProposal;
	}

	public void setPropertyProposal(PropertyProposal propertyProposal) {
		this.propertyProposal = propertyProposal;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

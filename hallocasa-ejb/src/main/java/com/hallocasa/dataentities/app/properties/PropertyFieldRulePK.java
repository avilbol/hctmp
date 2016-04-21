package com.hallocasa.dataentities.app.properties;

import com.hallocasa.dataentities.app.Country;

public class PropertyFieldRulePK {

	PropertyTypeGroup propertyTypeGroup;
	
	PropertyLocation propertyLocation;
	
	PropertyProposal propertyProposal;
	
	Country country;

	public PropertyTypeGroup getPropertyTypeGroup() {
		return propertyTypeGroup;
	}

	public void setPropertyTypeGroup(PropertyTypeGroup propertyTypeGroup) {
		this.propertyTypeGroup = propertyTypeGroup;
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
}

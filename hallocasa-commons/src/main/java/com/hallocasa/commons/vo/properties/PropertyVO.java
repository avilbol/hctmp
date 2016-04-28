package com.hallocasa.commons.vo.properties;

import java.io.Serializable;

import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.interfaces.ValueObject;

public class PropertyVO implements Serializable, ValueObject  {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = 9217502376856053881L;

	private PropertyTypeVO propertyType;
	
	private PropertyProposalVO propertyProposal;
	
	private PropertyLocationVO propertyLocation;
	
	private CountryVO country;
	
	
	private PropertyBasicInfo propertyBasicInfo;
	
	private PropertyLocationInfo propertyLocationInfo;
	
	private PropertyImageInfo propertyImageInfo;

	public PropertyTypeVO getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyTypeVO propertyType) {
		this.propertyType = propertyType;
	}

	public PropertyProposalVO getPropertyProposal() {
		return propertyProposal;
	}

	public void setPropertyProposal(PropertyProposalVO propertyProposal) {
		this.propertyProposal = propertyProposal;
	}

	public PropertyLocationVO getPropertyLocation() {
		return propertyLocation;
	}

	public void setPropertyLocation(PropertyLocationVO propertyLocation) {
		this.propertyLocation = propertyLocation;
	}

	public CountryVO getCountry() {
		return country;
	}

	public void setCountry(CountryVO country) {
		this.country = country;
	}

	public PropertyBasicInfo getPropertyBasicInfo() {
		return propertyBasicInfo;
	}

	public void setPropertyBasicInfo(PropertyBasicInfo propertyBasicInfo) {
		this.propertyBasicInfo = propertyBasicInfo;
	}

	public PropertyLocationInfo getPropertyLocationInfo() {
		return propertyLocationInfo;
	}

	public void setPropertyLocationInfo(PropertyLocationInfo propertyLocationInfo) {
		this.propertyLocationInfo = propertyLocationInfo;
	}

	public PropertyImageInfo getPropertyImageInfo() {
		return propertyImageInfo;
	}

	public void setPropertyImageInfo(PropertyImageInfo propertyImageInfo) {
		this.propertyImageInfo = propertyImageInfo;
	}
}

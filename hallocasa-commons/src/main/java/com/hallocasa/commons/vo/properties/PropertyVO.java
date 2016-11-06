package com.hallocasa.commons.vo.properties;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.commons.vo.interfaces.ValueObject;

public class PropertyVO implements Serializable, ValueObject  {

	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = 9217502376856053881L;
	
	private String id;

	@NotNull
	private PropertyTypeVO propertyType;
	
	@NotNull
	private PropertyProposalVO propertyProposal;
	
	@NotNull
	private PropertyLocationVO propertyLocation;
	
	@NotNull
	private CountryVO country;
	
	private UserVO user;
	
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
		if(propertyBasicInfo == null){
			propertyBasicInfo = new PropertyBasicInfo();
		}
		return propertyBasicInfo;
	}

	public void setPropertyBasicInfo(PropertyBasicInfo propertyBasicInfo) {
		this.propertyBasicInfo = propertyBasicInfo;
	}

	public PropertyLocationInfo getPropertyLocationInfo() {
		if(propertyLocationInfo == null){
			propertyLocationInfo = new PropertyLocationInfo();
		}
		return propertyLocationInfo;
	}

	public void setPropertyLocationInfo(PropertyLocationInfo propertyLocationInfo) {
		this.propertyLocationInfo = propertyLocationInfo;
	}

	public PropertyImageInfo getPropertyImageInfo() {
		if(propertyImageInfo == null){
			propertyImageInfo = new PropertyImageInfo();
		}
		return propertyImageInfo;
	}

	public void setPropertyImageInfo(PropertyImageInfo propertyImageInfo) {
		this.propertyImageInfo = propertyImageInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}
}

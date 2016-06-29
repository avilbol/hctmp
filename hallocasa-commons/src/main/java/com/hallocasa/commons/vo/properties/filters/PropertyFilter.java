package com.hallocasa.commons.vo.properties.filters;

import java.util.List;

import com.hallocasa.commons.vo.properties.PropertyLocationVO;
import com.hallocasa.commons.vo.properties.PropertyProposalVO;
import com.hallocasa.commons.vo.properties.PropertyTypeVO;

/**
 * Value Object to represent a filter to use in functionality that filter property list
 * @author Alexander Villamil
 *
 */
public class PropertyFilter {

	/**
	 * Type of property
	 */
	private PropertyTypeVO propertyType;
	
	/**
	 * Proposal of property
	 */
	private PropertyProposalVO propertyProposal;
	
	/**
	 * Location of property
	 */
	private PropertyLocationVO propertyLocation;
	
	/**
	 * Specific filters by property field value
	 */
	private List<PropertyFieldFilter> propertyFieldFilters;

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

	public List<PropertyFieldFilter> getPropertyFieldFilters() {
		return propertyFieldFilters;
	}

	public void setPropertyFieldFilters(
			List<PropertyFieldFilter> propertyFieldFilters) {
		this.propertyFieldFilters = propertyFieldFilters;
	}
}

package com.hallocasa.filters.converters;

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
	 * Property types
	 */
	private List<PropertyTypeVO> propertyTypeList;
	
	/**
	 * Property proposals
	 */
	private List<PropertyProposalVO> propertyProposalList;
	
	/**
	 * Property locations
	 */
	private List<PropertyLocationVO> propertyLocationList;
	
	/**
	 * Specific filters by property field value
	 */
	private List<PropertyFieldFilter> propertyFieldFilters;


	public List<PropertyTypeVO> getPropertyTypeList() {
		return propertyTypeList;
	}

	public void setPropertyTypeList(List<PropertyTypeVO> propertyTypeList) {
		this.propertyTypeList = propertyTypeList;
	}

	public List<PropertyProposalVO> getPropertyProposalList() {
		return propertyProposalList;
	}

	public void setPropertyProposalList(List<PropertyProposalVO> propertyProposalList) {
		this.propertyProposalList = propertyProposalList;
	}

	public List<PropertyLocationVO> getPropertyLocationList() {
		return propertyLocationList;
	}

	public void setPropertyLocationList(List<PropertyLocationVO> propertyLocationList) {
		this.propertyLocationList = propertyLocationList;
	}

	public List<PropertyFieldFilter> getPropertyFieldFilters() {
		return propertyFieldFilters;
	}

	public void setPropertyFieldFilters(
			List<PropertyFieldFilter> propertyFieldFilters) {
		this.propertyFieldFilters = propertyFieldFilters;
	}
	
	public PropertyFieldFilter getPropertyFieldFilter(Integer propertyFieldId){
		if(propertyFieldFilters == null)
			return null;
		for(PropertyFieldFilter filter : propertyFieldFilters)
			if(filter.getPropertyField().getId().equals(propertyFieldId))
				return filter;
		return null;
	}
}

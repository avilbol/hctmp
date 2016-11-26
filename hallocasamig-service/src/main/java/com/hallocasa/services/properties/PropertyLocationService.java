package com.hallocasa.services.properties;

import java.util.List;

import com.hallocasa.vo.hcfilter.properties.PropertyLocation;

/**
 * Contract service for class {@link PropertyLocation}
 * @author avillamil
 */
public interface PropertyLocationService {

	/**
	 * Find all property locations available on system
	 */
	public List<PropertyLocation> find();
}

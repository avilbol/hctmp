package com.hallocasa.services.interfaces;

import java.util.List;

import com.hallocasa.commons.vo.properties.PropertyVO;
import com.hallocasa.dataentities.app.User;
import com.hallocasa.dataentities.app.properties.Property;

/**
 * Interface for the service related with properties
 * @author Alexander Villamil
 */
public interface PropertyServices {

	/**
	 * Finds property list by user owner
	 * 
	 * @param email
	 * @return
	 */
	public List<PropertyVO> find(User user);
	
}

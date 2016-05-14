package com.hallocasa.services.interfaces;

import java.util.List;

import com.hallocasa.commons.vo.UserVO;
import com.hallocasa.commons.vo.properties.PropertyVO;

/**
 * Interface for the service related with properties
 * @author Alexander Villamil
 */
public interface PropertyServices {

	/**
	 * Finds property list by user owner
	 * 
	 * @param user
	 * @return
	 */
	public List<PropertyVO> find(UserVO user);
	
	/**
	 * Save property
	 * @param property
	 */
	public void save(PropertyVO property);
	
}

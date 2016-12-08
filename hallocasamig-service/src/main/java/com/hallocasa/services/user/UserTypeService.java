package com.hallocasa.services.user;

import java.util.List;

import com.hallocasa.vo.UserType;

public interface UserTypeService {

	/**
	 * Find and return the user type list available in system
	 */
	public List<UserType> find();
	
}

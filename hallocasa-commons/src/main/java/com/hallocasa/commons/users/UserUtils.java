package com.hallocasa.commons.users;

import com.hallocasa.commons.vo.UserVO;

/**
 * Utilities for manage user attributes and present them adequately
 * 
 * @author Alexander Villamil
 * @since 1.7
 */
public class UserUtils {

	/**
	 * Obtains the username of user in email presentation, when name is not setted yet,
	 * otherwise using the combination of first and last name
	 * @param userVO
	 * @return
	 */
	public static String getFullUsername(UserVO userVO) {
		if (userVO == null) {
			return null;
		}
		if (userVO.getFirstName() == null) {
			return userVO.getEmail();
		}
		return userVO.getFullName();
	}
	
	/**
	 * Obtains the username of user in email presentation, when name is not setted yet,
	 * otherwise using the first name
	 * @param userVO
	 * @return
	 */
	public static String getUsername(UserVO userVO) {
		if (userVO == null) {
			return null;
		}
		if (userVO.getFirstName() == null) {
			return userVO.getEmail();
		}
		return userVO.getFirstName();
	}
}

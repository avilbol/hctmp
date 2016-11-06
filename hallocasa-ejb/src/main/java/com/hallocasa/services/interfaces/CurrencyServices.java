package com.hallocasa.services.interfaces;

import com.hallocasa.commons.vo.UserVO;

/**
 * Service for provide currency functionality
 * @author avillamil
 *
 */
public interface CurrencyServices {

	/**
	 * Finds currency list
	 */
	public UserVO find();
	
}

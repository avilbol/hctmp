package com.hallocasa.commons.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * 
 * @author avillamil
 */
public class FormatUtils {

	public static String randomStrId() {
		return new BigInteger(40, new SecureRandom()).toString(32);
	}
	
}

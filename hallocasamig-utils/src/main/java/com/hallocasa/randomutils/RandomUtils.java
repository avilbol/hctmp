package com.hallocasa.randomutils;

import org.apache.commons.lang.RandomStringUtils;

public class RandomUtils {

	public static String alphanumericRandom(Integer length){
		return RandomStringUtils.randomAlphanumeric(length);
	}
	
}

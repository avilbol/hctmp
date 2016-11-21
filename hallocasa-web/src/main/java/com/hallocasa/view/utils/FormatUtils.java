/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * 
 * @author avillamil
 */
public class FormatUtils {

	private static final String HTTP_PREFIX = "http://";

	private static final String HTTPS_PREFIX = "https://";

	private static final String POINTS_COMPLEMENT_TRUNC = "...";

	public static boolean isNumeric(String value) {
		try {
			Integer.parseInt(value);
			Double.parseDouble(value);
			Long.parseLong(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isLongNumeric(String value) {
		try {
			Double.parseDouble(value);
			Long.parseLong(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static String getDefensiveLabel(String value) {
		if (value == null || value.equals("")) {
			return JSFUtils.getViewBundleString("Common.Label.NotSpecified");
		}
		return value;
	}

	public static String truncateWithPoints(String value, int maxCharacterNumber) {
		if (value == null) {
			return null;
		}
		if (value.length() <= maxCharacterNumber) {
			return value;
		}
		return value.substring(0, maxCharacterNumber) + POINTS_COMPLEMENT_TRUNC;
	}

	public static boolean isEmptyValue(String value) {
		return value == null || value.equals("");
	}

	public static String randomStrId() {
		return new BigInteger(30, new SecureRandom()).toString(32);
	}

	public static String buildWebString(String webStr, boolean https) {
		if (webStr == null || webStr.equals("")) {
			return JSFUtils.getViewBundleString("Common.Label.NotSpecified");
		}
		String prefix = https ? HTTPS_PREFIX : HTTP_PREFIX;
		if (!webStr.startsWith(prefix)) {
			return (https ? HTTPS_PREFIX : HTTP_PREFIX) + webStr;
		}
		return webStr;
	}
}
